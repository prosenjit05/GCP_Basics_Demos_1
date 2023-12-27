package com.example.GCPpubsubSBTFConsumer.pubsub;

import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.pubsub.v1.PubsubMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumeMessage {

    @Autowired
    private PubSubTemplate pubSubTemplate;

    private String subscriptionName = "my_subscription_pro";

    @PostConstruct
    public void initializeSubscription() {
        Subscriber subscription = pubSubTemplate.subscribe(subscriptionName, this::processMessage);
        // Handle potential subscription errors
    }

    private void processMessage(BasicAcknowledgeablePubsubMessage basicAcknowledgeablePubsubMessage) {
        PubsubMessage message = basicAcknowledgeablePubsubMessage.getPubsubMessage();
        // Extract message data
        byte[] data = message.getData().toByteArray();
        String messageText = new String(data);

        // Process the message (e.g., log, store in database)
        System.out.println("Received message: " + messageText);

        // Acknowledge the message
        basicAcknowledgeablePubsubMessage.ack();
    }

}
