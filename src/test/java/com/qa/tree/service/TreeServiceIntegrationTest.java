package com.qa.tree.service;

import com.qa.tree.dto.TreeDTO;
import com.qa.tree.domain.Tree;
import com.qa.tree.repo.TreeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeServiceIntegrationTest {

	@Autowired
	private TreeService service;

	@Autowired
	private TreeRepository repo;

	private Tree testTree;

	private Tree testTreeWithID;
	
	@Autowired
	private ModelMapper mapper;
	
	private TreeDTO mapToDTO(Tree tree) {
		return this.mapper.map(tree, TreeDTO.class);
	}

	@Before
	public void init() {
		this.testTree = new Tree(1L, "Oak", "Fagales");
		
		this.repo.deleteAll();
		//getting around auto-generated id's
		this.testTreeWithID = this.repo.save(this.testTree);
	}
	
	@Test
	public void testCreateTree() {
		assertEquals(this.mapToDTO(this.testTreeWithID), this.service.createTree(testTree));
	}

	@Test
	public void testDeleteTree() {
		assertThat(this.service.deleteTree(this.testTreeWithID.getId())).isFalse();
	}

	@Test
	public void testFindTreeByID() {
		assertThat(this.service.findTreeByID(this.testTreeWithID.getId())).isEqualTo(this.mapToDTO(this.testTreeWithID));
	}

	@Test
	public void testReadTrees() {
		assertThat(this.service.readTrees()).isEqualTo(Stream.of(this.mapToDTO(testTreeWithID)).collect(Collectors.toList()));
	}

	@Test
	public void testUpdateTree() {
		Tree newTree = new Tree("Oak", "Fagales");
		Tree updatedTree = new Tree(newTree.getTreeName(), newTree.getOrderName());
		updatedTree.setTreeId(this.testTreeWithID.getTreeId());

		assertThat(this.service.updateTree(newTree, this.testTreeWithID.getTreeId())).isEqualTo(this.mapToDTO(updatedTree));
	}

}
