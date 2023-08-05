package com.insightsystem;

import com.insightsystem.notificationservice.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@SpringBootApplication
public class NotificationServiceApplication
{

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "notification-topic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent){
		//send out email notification
		log.info("Received notification for order- {}",orderPlacedEvent.getOrderNumber());
	}


}
