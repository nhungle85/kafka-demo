package com.nle.spring.kafkaproducerconsumerbasics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class MyKafkaProducer {
	Logger log = LoggerFactory.getLogger(MyKafkaProducer.class);

	  @Value("${my.kafka.producer.topic}")
	  private String topic;

	  @Autowired
	  KafkaTemplate<String, String> kafkaTemplate;

	  public void sendDataToKafka(@RequestParam String data) {

	    ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, data);

	    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

	      @Override
	      public void onSuccess(SendResult<String, String> result) {
	        log.info(String.format("Sent data     = %s", result.getProducerRecord().value()));
	      }

	      @Override
	      public void onFailure(Throwable ex) {
	        log.error("Unable to send data to Kafka", ex);
	      }
	    });
	  }
}
