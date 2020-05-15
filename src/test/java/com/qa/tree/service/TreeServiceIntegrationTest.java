package com.qa.tree.service;

import com.qa.tree.dto.TreeDTO;
import com.qa.tree.persistence.domain.Tree;
import com.qa.tree.persistence.repo.TreeRepo;
import com.qa.tree.service.TreeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeServiceIntegrationTest {

	@Autowired
	private TreeService service;

	@Autowired
	private TreeRepo repo;

	private Tree testTree;

	private Tree testTreeWithID;
	
	@Autowired
	private ModelMapper mapper;
	
	private TreeDTO mapToDTO(Tree tree) {
		return this.mapper.map(tree, TreeDTO.class);
	}

	@Before
	public void init() {
		this.testTree = new Tree("Treetor Doom", "Grey", "Latveria");
		
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
		Tree newTree = new Tree("Sir Treeington esq.", "Blue", "Treeington Manor");
		Tree updatedTree = new Tree(newTree.getName(), newTree.getColour(), newTree.getHabitat());
		updatedTree.setId(this.testTreeWithID.getId());

		assertThat(this.service.updateTree(newTree, this.testTreeWithID.getId())).isEqualTo(this.mapToDTO(updatedTree));
	}

}
