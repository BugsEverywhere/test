package com.siemens.allkindsoftest.httpComponentTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class Sample {
    private final static String OPEN_API_URL = "https://dev-openapi.dmhmusic.com";

    public static final String KEY_ALGORITHM = "RSA";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;

    private final static CloseableHttpClient httpClient;
    static{
        SSLConnectionSocketFactory sslsf = null;
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        httpClientConnectionManager.setMaxTotal(300);
        httpClientConnectionManager.setDefaultMaxPerRoute(300);
        ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8).build();
        httpClientConnectionManager.setDefaultConnectionConfig(connectionConfig);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .build();
        httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(httpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    public static void main(String[] args){
        //登陆取得公钥以及身份信息，有效期内调用一次就可以了
        String publicKey = dologin();
        System.out.println("--公钥--" + publicKey);

        //推送日志前请先设置用户信息
        setUserInfo(publicKey);
        //日志加密并推送加密后的日志到服务器
        pushLog(publicKey);
    }

    /**
     * 登录并获取cookie身份以及公钥
     *    注意，有效期7天，过期再次调用这个接口进行身份验证
     */
    private static String dologin(){
        String url = OPEN_API_URL + "/OPENAPI/openApiLogin.json";
        HttpPost httpPost;
        httpPost = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        NameValuePair pair = new BasicNameValuePair("q_source", "rmpmkoxratqZj");
        pairs.add(pair);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        String response = doRequest(httpPost);
        String result = null;
        if (StringUtils.isNotBlank(response)) {
            JSONObject json = JSON.parseObject(response);
            Boolean bo = json.getBoolean("state");
            //判断调用是否成功
            if (bo) {
                //todo -- save cookie     调用成功，要自己保存好cookie，每次请求其他接口要带上这个cookie
                //todo -- save public key 调用成功，要自己保存好公钥，有要求加密解密的地方就使用这个公钥
                result = json.getString("data");
                if (StringUtils.isNotBlank(result)) {
                    //注意下面代码的替换字符处理
                    result = result.replace("-----BEGIN PUBLIC KEY-----\n", "").replace("\n-----END PUBLIC KEY-----\n", "");
                }
            }
        }
        return result;
    }
    /**
     * 设置用户信息
     */
    private static boolean setUserInfo(String publicKey){
        //设置接口参数
        Map<String, String> params = new HashMap<>();
        params.put("userId", "test123");
        params.put("telephone", "13344445555");
        params.put("vipType", "0");
        params.put("vipExpireTime", "2018-12-12 00:00:00");
        params.put("sex", "1");
        params.put("birthday", "2007-12-12");
        String result = doRequst(params, "/LOGPUSH/setSdkUserInfo.json", publicKey);
        if (StringUtils.isNotBlank(result)) {
            JSONObject json = JSON.parseObject(result);
            Boolean bo = json.getBoolean("state");
            //判断调用是否成功
            if (bo) {
                return true;
            }
        }
        return false;
    }
    /**
     * 回传日志
     */
    private static boolean pushLog(String publicKey){
        //具体的日志格式内容视具体的业务情况而定
        String log = "[{"
                + "\"assetId\": \"Ttestcallopenapi\""
                + ",\"rate\": \"320\""
                + ",\"playTime\": \"100\""
                + ",\"playType\": \"1\""
                + ",\"deviceModel\": \"xiaomi music\""
                + ",\"useTime\": \"2017-12-12 13:12:24\""
                + ",\"sourceId\": \"2\""
                + ",\"sourceType\": \"P15642353\""
                + "}]";
        //设置接口参数
        Map<String, String> params = new HashMap<>();
        params.put("log", log);
        String result = doRequst(params, "/LOGPUSH/uploadTrackPlay.json", publicKey);
        if (StringUtils.isNotBlank(result)) {
            JSONObject json = JSON.parseObject(result);
            Boolean bo = json.getBoolean("state");
            //判断调用是否成功
            if (bo) {
                return true;
            }
        }
        return false;
    }
    /**
     * 通用的接口请求加密处理
     * @param params    接口文档规定的具体参数
     * @param action     要请求的接口地址
     * @param publicKey  公钥
     */
    private static String doRequst(Map<String, String> params, String action, String publicKey){
        if (params == null) {
            params = new HashMap<>(1);
        }
        params.put("action", action);
        String q = null;
        try {
            //对参数进行加密
            String json = JSON.toJSONString(params);
            byte[] digJson = encryptByPublicKey(publicKey, json.getBytes("UTF-8"));
            //q = Base64.encodeBase64String(digJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //统一请求的地址
        String url = OPEN_API_URL + "/auth/";
        //设置加密后的参数
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        NameValuePair pair = new BasicNameValuePair("q", q);
        pairs.add(pair);
        //发起请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        String response = doRequest(httpPost);
        return response;
    }

    private static String doRequest(HttpRequestBase httpRequestBase) {
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            response = httpClient.execute(httpRequestBase);
            entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            switch (statusCode) {
                case HttpStatus.SC_OK:
                    String result = EntityUtils.toString(entity, Consts.UTF_8);
                    return result;
                default:
                    System.out.println("Http resquest failed, code=" + statusCode);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entity != null) {
                try {
                    EntityUtils.consume(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(httpRequestBase != null){
                try {
                    httpRequestBase.releaseConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 公钥加密过程
     */
    public static byte[] encryptByPublicKey(String publicKey, byte[] content) {
        if (StringUtils.isBlank(publicKey) || content == null || content.length == 0) {
            return null;
        }
        RSAPublicKey _publicKey = loadPublicKey(publicKey);
        return encryptByPublicKey(_publicKey, content);
    }
    /**
     * 公钥加密过程
     */
    public static byte[] encryptByPublicKey(RSAPublicKey publicKey, byte[] content) {
        if (publicKey == null || content == null || content.length == 0) {
            return null;
        }
        if (content.length <= MAX_ENCRYPT_BLOCK) {
            try {
                Cipher cipher = getCipher();
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] output = cipher.doFinal(content);
                return output;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return encryptByPublicKey_subsection(publicKey, content);
        }
    }
    //分段公钥加密过程
    private static byte[] encryptByPublicKey_subsection(RSAPublicKey publicKey, byte[] content) {
        try {
            Cipher cipher = getCipher();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            int inputLen = content.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            int maxLeng = MAX_ENCRYPT_BLOCK - 1;
            for(int i = 0; inputLen - offSet > 0; offSet = i * maxLeng) {
                byte[] cache;
                if(inputLen - offSet > maxLeng) {
                    cache = cipher.doFinal(content, offSet, maxLeng);
                } else {
                    cache = cipher.doFinal(content, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                ++i;
            }
            byte[] output = out.toByteArray();
            out.close();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 公钥解密过程
     */
    public static byte[] decryptByPublicKey(String publicKey, byte[] content) {
        if (StringUtils.isBlank(publicKey) || content == null || content.length == 0) {
            return null;
        }
        RSAPublicKey _publicKey = loadPublicKey(publicKey);
        return decryptByPublicKey(_publicKey, content);
    }
    /**
     * 公钥解密过程
     */
    public static byte[] decryptByPublicKey(RSAPublicKey publicKey, byte[] content) {
        if (publicKey == null || content == null || content.length == 0) {
            return null;
        }
        if (content.length <= MAX_DECRYPT_BLOCK) {
            try {
                Cipher cipher = getCipher();
                cipher.init(Cipher.DECRYPT_MODE, publicKey);
                byte[] output = cipher.doFinal(content);
                return output;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return decryptByPublicKey_subsection(publicKey, content);
        }
        return null;
    }
    //分段公钥解密过程
    private static byte[] decryptByPublicKey_subsection(RSAPublicKey publicKey, byte[] content) {
        try {
            Cipher cipher = getCipher();
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            int inputLen = content.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            for(int i = 0; inputLen - offSet > 0; offSet = i * MAX_DECRYPT_BLOCK) {
                byte[] cache;
                if(inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(content, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(content, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                ++i;
            }
            byte[] output = out.toByteArray();
            out.close();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 从字符串中加载公钥
     */
    public static RSAPublicKey loadPublicKey(String publicKeyStr) {
        if (StringUtils.isBlank(publicKeyStr)) {
            return null;
        }
        try {
            byte[] buffer = null;
                    //Base64Util.decode(publicKeyStr);
            KeyFactory keyFactory = getKeyFactory();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static KeyFactory keyFactory;
    private static KeyFactory getKeyFactory() {
        if (keyFactory == null) {
            synchronized (KEY_ALGORITHM) {
                if (keyFactory == null) {
                    try {
                        keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return keyFactory;
    }
    private static Cipher cipher;
    private static Cipher getCipher() {
        if (cipher == null) {
            synchronized (KEY_ALGORITHM) {
                if (cipher == null) {
                    try {
                        cipher = Cipher.getInstance(KEY_ALGORITHM);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cipher;
    }
}
