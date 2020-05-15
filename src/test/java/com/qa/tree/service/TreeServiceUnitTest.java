//package com.qa.service;
//
//import com.qa.domain.Tree;
//import com.qa.domain.Users;
//import com.qa.dto.TreeDTO;
//import com.qa.repo.TreeRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.modelmapper.ModelMapper;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.mockito.Mockito.*;
//
//@RunWith(SpringRunner.class)
//public class TreeServiceUnitTest {
//
//    @InjectMocks
//    private TreeService service;
//
//    @Mock
//    private TreeRepository repository;
//
//    @Mock
//    private ModelMapper mapper;
//
//    private Set<Tree> treeSet;
//
//    private Tree testTree;
//
//    private final long treeId = 1L;
//
//    private Tree testTreeWithID;
//
//    private TreeDTO treeDTO;
//
//    private TreeDTO mapToDTO(Tree tree){
//        return this.mapper.map(tree, TreeDTO.class);
//    }
//
//    @Before
//    public void setUp(){
//
//        this.treeSet = new HashSet<>();
//        this.testTree = new Tree("oak", "fagales");
//        this.treeSet.add(testTree);
//        this.testTreeWithID = new Tree(testTree.getTreeName(), testTree.getOrderName());
//        this.testTreeWithID.setId(treeId);
//        this.treeDTO = this.mapToDTO(testTreeWithID);
//    }
//
//    @Test
//    public void getAllTreesTest(){
//        when(repository.findAll()).thenReturn(this.treeList);
//        when(this.mapper.map(testTreeWithID, TreeDTO.class)).thenReturn(treeDTO);
//        assertFalse("Service returned no Trees", this.service.readTrees().isEmpty());
//        verify(repository, times(1)).findAll();
//    }
//
//    @Test
//    public void createTreeTest(){
//        when(repository.save(testTree)).thenReturn(testTreeWithID);
//        when(this.mapper.map(testTreeWithID, TreeDTO.class)).thenReturn(treeDTO);
//        assertEquals(this.service.createTree(testTree), this.treeDTO);
//        verify(repository, times(1)).save(this.testTree);
//    }
//
//    @Test
//    public void findTreeByIdTest(){
//        when(this.repository.findById(treeId)).thenReturn(java.util.Optional.ofNullable(testTreeWithID));
//        when(this.mapper.map(testTreeWithID, TreeDTO.class)).thenReturn(treeDTO);
//        assertEquals(this.service.findTreeById(this.treeId), treeDTO);
//        verify(repository, times(1)).findById(treeId);
//    }
//
//    @Test
//    public void deleteTreeByExistingId(){
//        when(this.repository.existsById(treeId)).thenReturn(true, false);
//        assertFalse(service.deleteTree(treeId));
//        verify(repository, times(1)).deleteById(treeId);
//        verify(repository, times(2)).existsById(treeId);
//    }






//    @Test(expected = TreeNotFoundException.class)
//    public void deleteTreeByNonExistingId(){
//        when(this.repository.existsById(treeId)).thenReturn(false);
//        service.deleteTree(treeId);
//        verify(repository, times(1)).existsById(treeId);
//    }

//    @Test
//    public void updateTreeTest(){
//
//        Tree newTree = new Tree("Favourite movies", "The grinch");
//        Tree updateTree = new Tree(newTree.getTitle(), newTree.getDescription());
//        updateTree.setId(id);
//
//        TreeDTO updateTreeDTO = new ModelMapper().map(updateTree, TreeDTO.class);
//
//        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testTreeWithID));
//        when(this.repository.save(updateTree)).thenReturn(updateTree);
//        when(this.mapper.map(updateTree, TreeDTO.class)).thenReturn(updateTreeDTO);
//
//        assertEquals(updateTreeDTO, this.service.updateTree(id, newTree));
//        verify(this.repository, times(1)).findById(id);
//        verify(this.repository, times(1)).save(updateTree);
//    }

//}