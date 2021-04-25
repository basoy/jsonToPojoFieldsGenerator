package com.json2pojofieldsgenerator;

import com.json2pojofieldsgenerator.request.FromJsonReq;
import com.json2pojofieldsgenerator.request.FromJsonWithFieldsReq;
import com.json2pojofieldsgenerator.request.FromUriReq;
import com.json2pojofieldsgenerator.request.FromUriWithFieldsReq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
    }
}
