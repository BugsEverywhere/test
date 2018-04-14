package com.siemens.allkindsoftest.sqlScripts;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class UpdateTrackWithNode {

    public static void main(String[] args) throws IOException {

        File audioRecordFile = new File("/Users/nali/Desktop/3473条audio和track.txt");

        File transcodeFile = new File("/Users/nali/Desktop/3479条视频的所有转码.csv");

        File nodeFile = new File("/Users/nali/Desktop/3473条分表号.txt");

        BufferedReader audioRecordReader = new BufferedReader(new FileReader(audioRecordFile));

        BufferedReader transcodeReader = new BufferedReader(new FileReader(transcodeFile));

        BufferedReader nodeReader = new BufferedReader(new FileReader(nodeFile));

        Map<String,TrackAudioTranscode> objectMap = new HashMap<>();

        String singleIdLine = null;

        while(null!=(singleIdLine = audioRecordReader.readLine())){

            TrackAudioTranscode trackAudioTranscode = new TrackAudioTranscode();

            String[] singleIdLineArray = singleIdLine.split(",");

            String audioId = singleIdLineArray[0];

            String trackId = singleIdLineArray[1];

            trackAudioTranscode.setAudioId(audioId);

            trackAudioTranscode.setTrackId(trackId);

            String singleNode = nodeReader.readLine().split("Track")[1];

            trackAudioTranscode.setTrackNode(singleNode);

            objectMap.put(audioId,trackAudioTranscode);

        }

        audioRecordReader.close();

        nodeReader.close();

        String singleTranscodeLine = null;

        while(null!=(singleTranscodeLine=transcodeReader.readLine())){

            String[] singleTransLineArray = singleTranscodeLine.split(",");

            String audioId = singleTransLineArray[1];

            TrackAudioTranscode t = objectMap.get(audioId);

            if(null!=t){

                if(t.getTrackId().equals("74172045")){
                    System.out.println();
                }

                if(singleTransLineArray[14].equals("video_m3u8_sd")){

                    if(null!=singleTransLineArray[6] && !singleTransLineArray[6].equals("") && !singleTransLineArray[6].equals("null")){
                        t.setVideo_online_sd(singleTransLineArray[6]);
                    }else{
                        t.setVideo_online_sd("null");
                    }

                    if(null!=singleTransLineArray[7] && !singleTransLineArray[7].equals("") && !singleTransLineArray[7].equals("null")){
                        t.setVideo_online_sd_size(Integer.valueOf(singleTransLineArray[7]));
                    }else{
                        t.setVideo_online_sd_size(0);
                    }

                } else if(singleTransLineArray[14].equals("video_m3u8_hd")){

                    if(null!=singleTransLineArray[6] && !singleTransLineArray[6].equals("") && !singleTransLineArray[6].equals("null")){
                        t.setVideo_online_hd(singleTransLineArray[6]);
                    }else{
                        t.setVideo_online_hd("null");
                    }

                    if(null!=singleTransLineArray[7] && !singleTransLineArray[7].equals("") && !singleTransLineArray[7].equals("null")){
                        t.setVideo_online_hd_size(Integer.valueOf(singleTransLineArray[7]));
                    }else{
                        t.setVideo_online_hd_size(0);
                    }
//
//                }else if(singleTransLineArray[14].equals("video_mp4_sd")){
//
//                    if(null!=singleTransLineArray[6] && !singleTransLineArray[6].equals("") && !singleTransLineArray[6].equals("null")){
//                        t.setVideo_download_sd(singleTransLineArray[6]);
//                    }else{
//                        t.setVideo_download_sd("null");
//                    }
//
//                    if(null!=singleTransLineArray[7] && !singleTransLineArray[7].equals("") && !singleTransLineArray[7].equals("null")){
//                        t.setVideo_download_sd_size(Integer.valueOf(singleTransLineArray[7]));
//                    }else{
//                        t.setVideo_download_sd_size(0);
//                    }
//
//                }else if(singleTransLineArray[14].equals("video_mp4_hd")){
//
//                    if(null!=singleTransLineArray[6] && !singleTransLineArray[6].equals("") && !singleTransLineArray[6].equals("null")){
//                        t.setVideo_download_hd(singleTransLineArray[6]);
//                    }else{
//                        t.setVideo_download_hd("null");
//                    }
//
//                    if(null!=singleTransLineArray[7] && !singleTransLineArray[7].equals("") && !singleTransLineArray[7].equals("null")){
//                        t.setVideo_download_hd_size(Integer.valueOf(singleTransLineArray[7]));
//                    }else{
//                        t.setVideo_download_hd_size(0);
//                    }
                }
            }
        }


        File outputSqlFile = new File("/Users/nali/Desktop/outputSql3473.sql");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputSqlFile)));

        StringBuilder sb = new StringBuilder("");

        for(TrackAudioTranscode singleTr:objectMap.values()){

            sb.append("update ting_content.tb_track"+singleTr.getTrackNode()+" set " +
                    " video_online_hd = \""+singleTr.getVideo_online_hd()+"\" " +
                    ", video_online_sd = \""+singleTr.getVideo_online_sd()+"\" " +
                    ", video_online_hd_size = "+singleTr.getVideo_online_hd_size()+" " +
                    ", video_online_sd_size = "+singleTr.getVideo_online_sd_size()+" " +
                    "where id = "+singleTr.getTrackId()+";\n");

//            sb.append("update ting_content.tb_track"+singleTr.getTrackNode()+" set " +
//                    " video_online_hd = \""+singleTr.getVideo_online_hd()+"\" " +
//                    ", video_online_sd = \""+singleTr.getVideo_online_sd()+"\" " +
//                    ", video_online_hd_size = "+singleTr.getVideo_online_hd_size()+" " +
//                    ", video_online_sd_size = "+singleTr.getVideo_online_sd_size()+" " +
//                    ", video_download_hd = \""+singleTr.getVideo_download_hd()+"\" " +
//                    ", video_download_sd = \""+singleTr.getVideo_download_sd()+"\" " +
//                    ", video_download_hd_size = "+singleTr.getVideo_download_hd_size()+" " +
//                    ", video_download_sd_size = "+singleTr.getVideo_download_sd_size()+" "+
//                    "where id = "+singleTr.getTrackId()+";\n");
        }

        writer.write(sb.toString());

        writer.close();



    }



}
