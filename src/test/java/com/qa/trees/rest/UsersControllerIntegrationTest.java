package com.qa.trees.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.trees.domain.Users;
import com.qa.trees.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations= "classpath:test.properties")
@AutoConfigureMockMvc
public class UsersControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private UserRepository repo;

	private ObjectMapper mapper = new ObjectMapper();

	private Long id;

	private Users testUsers;

	private Users testUsersWithId;

	@Before
	public void init() {
		this.repo.deleteAll();
		this.testUsers = new Users(1L, "John");
		this.testUsersWithId = this.repo.save(this.testUsers);
		this.id = this.testUsersWithId.getUserId();
	}

	@Test
	public void testCreateUsers() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/users/createUsers").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testUsers)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testUsersWithId), result);
	}

	@Test
	public void testDeleteUsers() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/users/deleteUsers/" + this.id)).andExpect(status().isNoContent());
	}

	@Test
	public void testGetUsers() throws Exception {
		String content = this.mock
				.perform(request(HttpMethod.GET, "/users/get/" + this.id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(this.testUsers), content);
	}

	@Test
	public void testGetAllUsers() throws Exception {
		List<Users> usersList = new ArrayList<>();
		usersList.add(this.testUsersWithId);

		String content = this.mock.perform(request(HttpMethod.GET, "/users/getAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(usersList), content);
	}

	@Test
	public void testUpdateUsers() throws Exception {
		Users newUsers = new Users(1L, "Sarah");
		Users updatedUsers = new Users(newUsers.getUsername());
		updatedUsers.setUserId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/users/updateUsers/?id=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newUsers)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(updatedUsers), result);
	}

}
