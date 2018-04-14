//package com.siemens.allkindsoftest;
//
//import com.siemens.allkindsoftest.model.FirstModel;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by Simons on 2017/5/22.
// */
//
//@Configuration
//public class MybatisMain {
//
//    @Bean(name = "MybatisMain")
//    MybatisMain mybatisMain(){
//        return new MybatisMain();
//    }
//
//    @Bean(name="TestService")
//    TestService testService(){
//        return new TestService();
//    }
//
//
//
//
//    public static void main(String[] args){
//
//        ApplicationContext context = new AnnotationConfigApplicationContext(MybatisMain.class);
//
//        TestService testService =(TestService) context.getBean("TestService");
//
//        FirstModel model = testService.selectFirstModel("1");
//
//        System.out.println();
//
//
//    }
//
//
//
//}
