package com.udc.fic.orchestrationeditor.test.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.udc.fic.orchestrationeditor.service.UserService;

@Configuration
public class TestConfiguration {

	@Bean
	@Primary
	public UserService userServiceMock() {
		return Mockito.mock(UserService.class);
	}

}
