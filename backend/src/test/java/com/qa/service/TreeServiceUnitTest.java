package com.qa.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TreeServiceUnitTest {

    @InjectMocks
    private TreeService service;

    @Mock
    private TreesRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Tree> treeList;

    private Tree_testTree;

    private_long id = 1L;

    private_Tree testTreeWithId;

    private_TreeDTO treeDTO(Tree tree){
        return this.mapper.map(tree, TreeDTO.class);
    }

    @Before
    public void setUp(){
        this.treeList = new ArrayList<>();
        this.testTree = new Tree("Shopping list", "Beer and even more beer");
        this.treeList.add(testTree);
        this.testTreeWithId = new Tree(testTree.getTitle(), testTree.getDescription());
        this.testTreeWithId.setId(id);
        this.treeDTO = this.mapToDTO(testTreeWithId);
    }

    @Test
    public void getAllTreesTest(){
        when(repository.findAll()).thenReturn(this.treeList);
        when(this.mapper)
    }


}