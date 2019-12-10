package com.milos.releasemicroservice.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class QueueConsumer
{
	public void receiveMessage(List<ImageDTO> message)
	{
		log.info("Received (String) " + message);
		processMessage(message);
	}

//	public void receiveMessage(byte[] message)
//	{
//		String strMessage = new String(message);
//		log.info("Received (No String) " + strMessage);
//		processMessage(strMessage);
//	}

	private void processMessage(List<ImageDTO> message)
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