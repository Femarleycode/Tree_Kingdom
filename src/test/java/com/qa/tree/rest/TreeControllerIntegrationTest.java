package com.qa.tree.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tree.domain.Tree;
import com.qa.tree.dto.TreeDTO;
import com.qa.tree.repo.TreeRepository;
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
public class TreeControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private TreeRepository repo;

	@Autowired
	private ModelMapper modelMapper;

	private ObjectMapper mapper = new ObjectMapper();

	private Long id;

	private Tree testTree;

	private Tree testTreeWithID;

	private TreeDTO treeDTO;
	
	private TreeDTO mapToDTO(Tree tree) {
		return this.modelMapper.map(tree, TreeDTO.class);
	}

	@Before
	public void init() {
		this.repo.deleteAll();
		this.testTree = new Tree(1L, "Oak", "Fagales");
		this.testTreeWithID = this.repo.save(this.testTree);
		this.id = this.testTreeWithID.getTreeId();
		this.treeDTO = this.mapToDTO(testTreeWithID);
	}


	@Test
	public void testCreateTree() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/tree/createTree").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testTree)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(treeDTO), result);
	}

	@Test
	public void testDeleteTree() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/tree/deleteTree/" + this.id)).andExpect(status().isNoContent());
	}

	@Test
	public void testGetAllTrees() throws Exception {
		List<TreeDTO> treeList = new ArrayList<>();
		treeList.add(this.treeDTO);

		String content = this.mock.perform(request(HttpMethod.GET, "/tree/getAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(treeList), content);
	}

	@Test
	public void testUpdateTree() throws Exception {
		Tree newTree = new Tree(1L, "Oak", "Fagales");
		Tree updatedTree = new Tree(newTree.getTreeName(), newTree.getOrderName());
		updatedTree.setId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/tree/updateTree/?id=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newTree)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(this.mapToDTO(updatedTree)), result);
	}


}
