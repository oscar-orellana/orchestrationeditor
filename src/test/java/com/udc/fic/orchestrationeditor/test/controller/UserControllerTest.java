package com.udc.fic.orchestrationeditor.test.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.udc.fic.orchestrationeditor.configuration.WebConfiguration;
import com.udc.fic.orchestrationeditor.dto.UserDto;
import com.udc.fic.orchestrationeditor.model.User;
import com.udc.fic.orchestrationeditor.model.UserRole;
import com.udc.fic.orchestrationeditor.service.UserService;
import com.udc.fic.orchestrationeditor.test.configuration.TestConfiguration;
import com.udc.fic.orchestrationeditor.test.dto.UserDtoBuilder;
import com.udc.fic.orchestrationeditor.test.model.UserBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class, WebConfiguration.class })
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mockMvc;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

	private User user1;
	private User user2;
	
	private UserDto userDto1;
	private UserDto userDto2;

	@Autowired
	UserService userService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws ParseException {
		Mockito.reset(userService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		dateFormat.setTimeZone(timeZone);

		Date birthDate = dateFormat.parse("1987-06-20");
		
		user1 = new UserBuilder().id(1).username("john.doe").password("password").role(UserRole.ADMIN)
				.firstName("John").lastName("Doe").birthDate(birthDate).email("john.doe@domain.com")
				.phoneNumber("986365555").build();

		user2 = new UserBuilder().id(2).username("richard.roe").password("password").role(UserRole.USER)
				.firstName("Richard").lastName("Roe").birthDate(birthDate).email("richard.roe@domain.com")
				.phoneNumber("986365555").build();

		userDto1 = new UserDtoBuilder().id(1).username("john.doe").password("password").role(UserRole.ADMIN)
				.firstName("John").lastName("Doe").birthDate(birthDate).email("john.doe@domain.com")
				.phoneNumber("986365555").build();

		userDto2 = new UserDtoBuilder().id(2).username("richard.roe").password("password").role(UserRole.USER)
				.firstName("Richard").lastName("Roe").birthDate(birthDate).email("richard.roe@domain.com")
				.phoneNumber("986365555").build();
	}

	@Test
	public void getUsers() throws Exception {
		when(userService.findAllUsers()).thenReturn(Arrays.asList(user1, user2));
		
		mockMvc.perform(get("/api/users"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].username", is("john.doe")))
			.andExpect(jsonPath("$[0].password", is("password")))
			.andExpect(jsonPath("$[0].role", is("ADMIN")))
			.andExpect(jsonPath("$[0].firstName", is("John")))
			.andExpect(jsonPath("$[0].lastName", is("Doe")))
			.andExpect(jsonPath("$[0].birthDate", is("1987-06-20")))
			.andExpect(jsonPath("$[0].email", is("john.doe@domain.com")))
			.andExpect(jsonPath("$[0].phoneNumber", is("986365555")))
			.andExpect(jsonPath("$[1].id", is(2)))
			.andExpect(jsonPath("$[1].username", is("richard.roe")))
			.andExpect(jsonPath("$[1].password", is("password")))
			.andExpect(jsonPath("$[1].role", is("USER")))
			.andExpect(jsonPath("$[1].firstName", is("Richard")))
			.andExpect(jsonPath("$[1].lastName", is("Roe")))
			.andExpect(jsonPath("$[1].birthDate", is("1987-06-20")))
			.andExpect(jsonPath("$[1].email", is("richard.roe@domain.com")))
			.andExpect(jsonPath("$[1].phoneNumber", is("986365555")));
		
		verify(userService, times(1)).findAllUsers();
		verifyNoMoreInteractions(userService);
	}
}
