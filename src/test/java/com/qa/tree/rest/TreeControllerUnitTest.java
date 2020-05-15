package com.qa.tree.rest;

import com.qa.tree.domain.Tree;
import com.qa.tree.dto.TreeDTO;
import com.qa.tree.service.TreeService;
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
public class TreeControllerUnitTest {

	@InjectMocks
	private TreeController controller;

	@Mock
	private TreeService service;

	private List<Tree> treeList;

	private Tree testTree;

	private Tree testTreeWithID;
	
	private TreeDTO treeDTO;

	final Long id = 1L;
	
	private ModelMapper mapper = new ModelMapper();

	private TreeDTO mapToDTO(Tree tree) {
		return this.mapper.map(tree, TreeDTO.class);
	}
	
	
	@Before
	public void init() {
		this.treeList = new ArrayList<>();
		this.testTree = new Tree(id, "Oak", "Fagales");
		this.treeList.add(testTree);
		this.testTreeWithID = new Tree(testTree.getTreeName(), testTree.getOrderName());
		this.testTreeWithID.setId(id);
		this.treeDTO = this.mapToDTO(testTreeWithID);
	}

	@Test
	public void createTreeTest() {
		when(this.service.createTree(testTree)).thenReturn(this.treeDTO);

		assertEquals(new ResponseEntity<TreeDTO>(this.treeDTO, HttpStatus.CREATED), this.controller.createTree(testTree));

		verify(this.service, times(1)).createTree(this.testTree);
	}

	@Test
	public void deleteTreeTest() {
		this.controller.deleteTree(id);

		verify(this.service, times(1)).deleteTree(id);
	}

	@Test
	public void findTreeByIDTest() {
		when(this.service.findTreeByID(this.id)).thenReturn(this.treeDTO);

		assertEquals(new ResponseEntity<TreeDTO>(this.treeDTO, HttpStatus.OK), this.controller.getTree(this.id));

		verify(this.service, times(1)).findTreeByID(this.id);
	}

	@Test
	public void getAllTreesTest() {

		when(service.readTrees()).thenReturn(this.treeList.stream().map(this::mapToDTO).collect(Collectors.toList()));

		assertFalse("Controller has found no trees", this.controller.getAllTrees().getBody().isEmpty());

		verify(service, times(1)).readTrees();
	}

	@Test
	public void updateTreesTest() {
		// given
		Tree newTree = new Tree(1L, "Oak", "Fagales");
		Tree updatedTree = new Tree(newTree.getTreeName(), newTree.getOrderName());
		updatedTree.setId(this.id);

		when(this.service.updateTree(newTree, this.id)).thenReturn(this.mapToDTO(updatedTree));

		assertEquals(new ResponseEntity<TreeDTO>(this.mapToDTO(updatedTree), HttpStatus.ACCEPTED), this.controller.updateTree(this.id, newTree));

		verify(this.service, times(1)).updateTree(newTree, this.id);
	}

}
