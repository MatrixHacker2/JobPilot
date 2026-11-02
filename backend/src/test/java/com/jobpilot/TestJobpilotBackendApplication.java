package com.jobpilot;

import org.springframework.boot.SpringApplication;

public class TestJobpilotBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(JobpilotBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
