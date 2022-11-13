package com.nle.spring.kafkaproducerconsumerbasics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MyRestController {
	MyKafkaProducer myKafkaProducer;

	@GetMapping("/send")
	public void sendDataToKafka(@RequestParam String data) {
		myKafkaProducer.sendDataToKafka(data);
	}
}
