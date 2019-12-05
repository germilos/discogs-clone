package com.milos.imageprocessingmicroservice.integration;

import com.milos.imageprocessingmicroservice.amqp.QueueProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RabbitMQIT
{
	@Autowired
	private QueueProducer queueProducer;

	@Test
	public void rabbitTest() throws Exception
	{
		queueProducer.produce("Some message");
	}
}
