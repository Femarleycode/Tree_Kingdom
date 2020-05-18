package com.qa.trees.service;

import com.qa.trees.domain.Trees;
import com.qa.trees.dto.TreeDTO;
import com.qa.trees.repo.TreeRepository;
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
public class TreesServiceUnitTest {

	@InjectMocks
	private TreeService service;

	@Mock
	private TreeRepository repo;

	@Mock
	private ModelMapper mapper;

	private List<Trees> treesList;

	private Trees testTrees;

	private Trees testTreesWithId;

	private TreeDTO treeDTO;

	final Long id = 1L;

	@Before
	public void init() {
		this.treesList = new ArrayList<>();
		this.treesList.add(testTrees);
		this.testTrees = new Trees(1L, "Oak", "Fagales");
		this.testTreesWithId = new Trees(testTrees.getTreeName(), testTrees.getOrderName());
		this.testTreesWithId.setId(id);
		this.treeDTO = new ModelMapper().map(testTreesWithId, TreeDTO.class);
	}

	@Test
	public void createTreeTest() {
		when(this.repo.save(testTrees)).thenReturn(testTreesWithId);
		when(this.mapper.map(testTreesWithId, TreeDTO.class)).thenReturn(treeDTO);

		assertEquals(this.treeDTO, this.service.createTree(testTrees));

		verify(this.repo, times(1)).save(this.testTrees);
	}

	@Test
	public void deleteTreeTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteTree(id);

		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}

	@Test
	public void findTreeByIdTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testTreesWithId));
		when(this.mapper.map(testTreesWithId, TreeDTO.class)).thenReturn(treeDTO);

		assertEquals(this.treeDTO, this.service.findTreeById(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}

	@Test
	public void readTreesTest() {

		when(repo.findAll()).thenReturn(this.treesList);
		when(this.mapper.map(testTreesWithId, TreeDTO.class)).thenReturn(treeDTO);

		assertFalse("Controller has found no trees", this.service.readTrees().isEmpty());

		verify(repo, times(1)).findAll();
	}

	@Test
	public void updateTreesTest() {
		// given
		Trees newTrees = new Trees(1L, "Oak", "Fagales");
		
		Trees updatedTrees = new Trees(newTrees.getTreeName(), newTrees.getOrderName());
		updatedTrees.setId(this.id);
		
		TreeDTO updatedDTO = new ModelMapper().map(updatedTrees, TreeDTO.class);

		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testTreesWithId));
		when(this.mapper.map(updatedTrees, TreeDTO.class)).thenReturn(updatedDTO);

		// You NEED to configure a .equals() method in Trees.java for this to work
		when(this.repo.save(updatedTrees)).thenReturn(updatedTrees);

		assertEquals(updatedDTO, this.service.updateTree(this.id, newTrees));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedTrees);
	}

}
