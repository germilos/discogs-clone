package com.milos.releasemicroservice.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueConsumer
{
	@Autowired
	public void receiveMessage(String message)
	{
		log.info("Received (String) " + message);
		processMessage(message);
	}

	public void receiveMessage(byte[] message)
	{
		String strMessage = new String(message);
		log.info("Received (No String) " + strMessage);
		processMessage(strMessage);
	}

	private void processMessage(String message)
	{
		try
		{
			log.info("Received message: {}", message);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
		}
	}
}