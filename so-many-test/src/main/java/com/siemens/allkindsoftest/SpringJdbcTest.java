package com.siemens.allkindsoftest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Simons on 2017/5/15.
 */
public class SpringJdbcTest {

    public static void main(String[] args){

//        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(new ComboPooledDataSource("myc3p0xml"));
//
//        String sql = "SELECT tag, time, value FROM historical WHERE tag = :tag";
//
//        Map<String, String> namedParameters = new HashMap<>();
//
//        namedParameters.put("tag","AQDC_FIRST_TAG");
//
//        template.queryForObject(sql,namedParameters,new BeanPropertyRowMapper<SingleSensorData>(SingleSensorData.class));
//
//        System.out.println();

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(new ComboPooledDataSource("myc3p0xml"));

        String sql = "SELECT time, value FROM historical";

        Map<String, String> namedParameters = new HashMap<>();

        List<Map<String,Object>> singleSensorDatas = template.queryForList(sql,namedParameters);

        System.out.println();


    }

}
