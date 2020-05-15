package com.qa.tree.service;

import com.qa.tree.domain.Tree;
import com.qa.tree.dto.TreeDTO;
import com.qa.tree.repo.TreeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TreeServiceUnitTest {

	@InjectMocks
	private TreeService service;

	@Mock
	private TreeRepository repo;

	@Mock
	private ModelMapper mapper;

	private List<Tree> treeList;

	private Tree testTree;

	private Tree testTreeWithID;

	private TreeDTO treeDTO;

	final Long id = 1L;

	@Before
	public void init() {
		this.treeList = new ArrayList<>();
		this.treeList.add(testTree);
		this.testTree = new Tree(1L, "Grey", "Latveria");
		this.testTreeWithID = new Tree(testTree.getTreeName(), testTree.getOrderName());
		this.testTreeWithID.setId(id);
		this.treeDTO = new ModelMapper().map(testTreeWithID, TreeDTO.class);
	}

	@Test
	public void createTreeTest() {
		when(this.repo.save(testTree)).thenReturn(testTreeWithID);
		when(this.mapper.map(testTreeWithID, TreeDTO.class)).thenReturn(treeDTO);

		assertEquals(this.treeDTO, this.service.createTree(testTree));

		verify(this.repo, times(1)).save(this.testTree);
	}

	@Test
	public void deleteTreeTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteTree(id);

		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}

	@Test
	public void findTreeByIDTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testTreeWithID));
		when(this.mapper.map(testTreeWithID, TreeDTO.class)).thenReturn(treeDTO);

		assertEquals(this.treeDTO, this.service.findTreeByID(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}

	@Test
	public void readTreesTest() {

		when(repo.findAll()).thenReturn(this.treeList);
		when(this.mapper.map(testTreeWithID, TreeDTO.class)).thenReturn(treeDTO);

		assertFalse("Controller has found no trees", this.service.readTrees().isEmpty());

		verify(repo, times(1)).findAll();
	}

	@Test
	public void updateTreesTest() {
		// given
		Tree newTree = new Tree(1L, "Oak", "Fagales");
		
		Tree updatedTree = new Tree(newTree.getTreeName(), newTree.getOrderName());
		updatedTree.setId(this.id);
		
		TreeDTO updatedDTO = new ModelMapper().map(updatedTree, TreeDTO.class);

		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testTreeWithID));
		when(this.mapper.map(updatedTree, TreeDTO.class)).thenReturn(updatedDTO);

		// You NEED to configure a .equals() method in Tree.java for this to work
		when(this.repo.save(updatedTree)).thenReturn(updatedTree);

		assertEquals(updatedDTO, this.service.updateTree(this.id, newTree));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedTree);
	}

}
