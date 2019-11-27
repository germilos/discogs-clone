package com.milos.releasemicroservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Slf4j
@EnableFeignClients("com.milos.releasemicroservice")
public class ReleaseMicroserviceApplication implements ApplicationRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(ReleaseMicroserviceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
	}
}
