package com.cetin.kafka.template;

import com.cetin.kafka.model.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/*
* KafkaTemplate provides convenient methods to send messages to topics:
* */

@Component
@Data
@Slf4j
public class KafkaSenderExample {
    private final KafkaTemplate<String, User> kafkaTemplate;

    /* All we need to do is to call the sendMessage() method with the message and the topic name as parameters.
    */
    public void sendMessage(User message, String topicName){
        kafkaTemplate.send(topicName, message);
    }

    /*Spring Kafka also allows us to configure an async callback:*/

    /*
    * The send() method of KafkaTemplate returns a ListenableFuture<SendResult>.
    * We can register a ListenableFutureCallback with the listener to receive
    * the result of the send and do some work within an execution context.
    * */
    public void sendMessageWithCallback(User message, String topicName) {
        ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
            @Override
            public void onSuccess(SendResult<String, User> result) {
                log.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }
}
