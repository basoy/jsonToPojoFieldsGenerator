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

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Json2PojoController controller = (Json2PojoController) context.getBean("controller");

        //WARNING: each call of methods 'generatePojoFrom...'() delete and re-create directory, so only last invoke will be shown.

        //for creating pojo from URL only from fields of json
        controller.fromUriWithFields(
                new FromUriWithFieldsReq("https://reqres.in/api/products", "Xz", "com.example", "data[name],data[color]"));

        //for creating pojo from URL with json
        /*controller.fromUri(new FromUriReq("https://reqres.in/api/products", "Xz", "com.example"));

        //for creating pojo from json text
        controller.fromJson(new FromJsonReq("{\n" +
                "    \"page\": 1,\n" +
                "    \"per_page\": 6 }", "Xz", "com.example"));

        //for creating pojo from json text only from fields of json
        controller.fromJsonWithFields(new FromJsonWithFieldsReq("{\n" +
                "    \"page\": 1,\n" +
                "    \"per_page\": 6 }", "Xz", "com.example", "page"));*/
    }
}
