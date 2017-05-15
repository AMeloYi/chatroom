package com.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.chatroom.controllers.HomeController;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class ChatroomApplication {

  private static Logger logger = LoggerFactory.getLogger(HomeController.class);

  public static void main(String[] args) {
    SpringApplication.run(ChatroomApplication.class, args);
    logger.info("SpringBoot Start Success");
  }
}
