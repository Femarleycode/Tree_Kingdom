package com.qa.trees.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.trees.domain.Trees;
import com.qa.trees.dto.TreeDTO;
import com.qa.trees.repo.TreeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TreesControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private TreeRepository repo;

	@Autowired
	private ModelMapper modelMapper;

	private ObjectMapper mapper = new ObjectMapper();

	private Long id;

	private Trees testTrees;

	private Trees testTreesWithId;

	private TreeDTO treeDTO;
	
	private TreeDTO mapToDTO(Trees trees) {
		return this.modelMapper.map(trees, TreeDTO.class);
	}

	@Before
	public void init() {
		this.repo.deleteAll();
		this.testTrees = new Trees(1L, "Oak", "Fagales");
		this.testTreesWithId = this.repo.save(this.testTrees);
		this.id = this.testTreesWithId.getId();
		this.treeDTO = this.mapToDTO(testTreesWithId);
	}


	@Test
	public void testCreateTree() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/trees/createTree").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testTrees)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(treeDTO), result);
	}

	@Test
	public void testDeleteTree() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/trees/deleteTree/" + this.id)).andExpect(status().isNoContent());
	}

	@Test
	public void testGetAllTrees() throws Exception {
		List<TreeDTO> treeList = new ArrayList<>();
		treeList.add(this.treeDTO);

		String content = this.mock.perform(request(HttpMethod.GET, "/trees/getAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(treeList), content);
	}

	@Test
	public void testUpdateTree() throws Exception {
		Trees newTrees = new Trees(1L, "Oak", "Fagales");
		Trees updatedTrees = new Trees(newTrees.getTreeName(), newTrees.getOrderName());
		updatedTrees.setId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/trees/updateTree/?id=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newTrees)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(this.mapToDTO(updatedTrees)), result);
	}


}
