package com.qa.trees.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.qa.trees.domain.Trees;
import com.qa.trees.repo.TreeRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.trees.dto.TreeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations= "classpath:test.properties")
public class TreesServiceIntegrationTest {

	@Autowired
	private TreeService service;

	@Autowired
	private TreeRepository repo;

	private Trees testTrees;

	private Trees testTreesWithId;

	@Autowired
	private ModelMapper mapper;

	private TreeDTO mapToDTO(Trees trees) {
		return this.mapper.map(trees, TreeDTO.class);
	}

	@Before
	public void init() {
		this.testTrees = new Trees(1L, "Oak", "Fagales");

		this.repo.deleteAll();
		this.testTreesWithId = this.repo.save(this.testTrees);
	}

	@Ignore
	@Test
	public void testCreateTree() {
		assertEquals(this.mapToDTO(this.testTreesWithId), this.service.createTree(testTrees));
	}

	@Ignore
	@Test
	public void testDeleteTree() {
		assertThat(this.service.deleteTree(this.testTreesWithId.getId())).isFalse();
	}

	@Ignore
	@Test
	public void testFindTreeById() {
		assertThat(this.service.findTreeById(this.testTreesWithId.getId())).isEqualTo(this.mapToDTO(this.testTreesWithId));
	}

	@Ignore
	@Test
	public void testReadTrees() {
		assertThat(this.service.readTrees()).isEqualTo(Stream.of(this.mapToDTO(testTreesWithId)).collect(Collectors.toList()));
	}

	@Ignore
	@Test
	public void testUpdateTree() {
		Trees newTrees = new Trees("Oak", "Fagales");
		Trees updatedTrees = new Trees(newTrees.getTreeName(), newTrees.getOrderName());
		updatedTrees.setId(this.testTreesWithId.getId());

		assertThat(this.service.updateTree(this.testTreesWithId.getId(), newTrees)).isEqualTo(this.mapToDTO(updatedTrees));
	}

}
