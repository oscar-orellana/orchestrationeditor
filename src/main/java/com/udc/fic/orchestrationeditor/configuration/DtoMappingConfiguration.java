package com.udc.fic.orchestrationeditor.configuration;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udc.fic.orchestrationeditor.dto.UserDto;
import com.udc.fic.orchestrationeditor.model.User;

@Configuration
public class DtoMappingConfiguration {

	@Bean
	public BeanMappingBuilder beanMappingBuilder() {
		return new BeanMappingBuilder() {
			@Override
			protected void configure() {
				mapping(User.class, UserDto.class);
			}
		};
	}

	@Bean
	public DozerBeanMapper beanMapper() {
		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		dozerBeanMapper.addMapping(beanMappingBuilder());
		return dozerBeanMapper;
	}
}
