package com.example.employee.config;


import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/aws-config.xml")
@EnableRdsInstance(databaseName = "${rds.name}",
        dbInstanceIdentifier = "${rds.instance}",
        password = "${rds.password}",
        username = "${rds.username}")
public class AwsResourceConfig {

}
//@EnableRdsInstance(databaseName = "dbname",
//        dbInstanceIdentifier = "mydbinstance",
//        password = "RDTelecos!28",
//        username = "admin")
//public class AwsResourceConfig {
//
//}