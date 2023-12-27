package com.example.GCPpubsubSBTFProducer.controller;

import com.example.GCPpubsubSBTFProducer.dto.MyTopicDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/gcp")
public class ProducerController {

    @Autowired
    private PubSubTemplate pubSubTemplate;

    private String myTopic = "my_topic_pro";

    @PostMapping("/publish_message")
    public CompletableFuture<String> publishMessage(@RequestBody MyTopicDto message)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] serializedData = mapper.writeValueAsBytes(message);

        return CompletableFuture.supplyAsync(() -> {
            pubSubTemplate.publish(myTopic, serializedData);
            return "Message published successfully!";
        });
    }

}
