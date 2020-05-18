package com.qa.trees.rest;

import com.qa.trees.domain.Trees;
import com.qa.trees.dto.TreeDTO;
import com.qa.trees.service.TreeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TreesControllerUnitTest {

	@InjectMocks
	private TreeController controller;

	@Mock
	private TreeService service;

	private List<Trees> treesList;

	private Trees testTrees;

	private Trees testTreesWithId;
	
	private TreeDTO treeDTO;

	final Long id = 1L;
	
	private ModelMapper mapper = new ModelMapper();

	private TreeDTO mapToDTO(Trees trees) {
		return this.mapper.map(trees, TreeDTO.class);
	}
	
	
	@Before
	public void init() {
		this.treesList = new ArrayList<>();
		this.testTrees = new Trees(id, "Oak", "Fagales");
		this.treesList.add(testTrees);
		this.testTreesWithId = new Trees(testTrees.getTreeName(), testTrees.getOrderName());
		this.testTreesWithId.setId(id);
		this.treeDTO = this.mapToDTO(testTreesWithId);
	}

	@Test
	public void createTreeTest() {
		when(this.service.createTree(testTrees)).thenReturn(this.treeDTO);

		assertEquals(new ResponseEntity<TreeDTO>(this.treeDTO, HttpStatus.CREATED), this.controller.createTree(testTrees));

		verify(this.service, times(1)).createTree(this.testTrees);
	}

	@Test
	public void deleteTreeTest() {
		this.controller.deleteTree(id);

		verify(this.service, times(1)).deleteTree(id);
	}

	@Test
	public void findTreeByIdTest() {
		when(this.service.findTreeById(this.id)).thenReturn(this.treeDTO);

		assertEquals(new ResponseEntity<TreeDTO>(this.treeDTO, HttpStatus.OK), this.controller.getTree(this.id));

		verify(this.service, times(1)).findTreeById(this.id);
	}

	@Test
	public void getAllTreesTest() {

		when(service.readTrees()).thenReturn(this.treesList.stream().map(this::mapToDTO).collect(Collectors.toList()));

		assertFalse("Controller has found no trees", this.controller.getAllTrees().getBody().isEmpty());

		verify(service, times(1)).readTrees();
	}

	@Test
	public void updateTreesTest() {
		// given
		Trees newTrees = new Trees(1L, "Oak", "Fagales");
		Trees updatedTrees = new Trees(newTrees.getTreeName(), newTrees.getOrderName());
		updatedTrees.setId(this.id);

		when(this.service.updateTree(newTrees, this.id)).thenReturn(this.mapToDTO(updatedTrees));

		assertEquals(new ResponseEntity<TreeDTO>(this.mapToDTO(updatedTrees), HttpStatus.ACCEPTED), this.controller.updateTrees(this.id, newTrees));

		verify(this.service, times(1)).updateTree(newTrees, this.id);
	}

}
