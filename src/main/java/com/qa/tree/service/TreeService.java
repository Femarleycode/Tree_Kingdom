package com.qa.tree.service;

import com.qa.tree.domain.Tree;
import com.qa.tree.dto.TreeDTO;
import com.qa.tree.exceptions.TreeNotFoundException2;
import com.qa.tree.repo.TreeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeService {

    private final TreeRepository repo;
    private final ModelMapper mapper;
    private TreeDTO mapToDTO(Tree tree){
        return this.mapper.map(tree, TreeDTO.class);
    }

    @Autowired
    public TreeService(TreeRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<TreeDTO> readTrees(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TreeDTO createTree(Tree tree){
        Tree tempTree = this.repo.save(tree);
        return this.mapToDTO(tempTree);
    }

    public TreeDTO findTreeById(Long treeId){
        return this.mapToDTO(this.repo.findById(treeId)
                .orElseThrow(TreeNotFoundException2::new));
    }

    public TreeDTO updateTree(Long treeId, Tree tree){
        Tree update = this.repo.findById(treeId).orElseThrow(TreeNotFoundException2::new);
        update.setTreeName(tree.getTreeName());
        Tree tempTree = this.repo.save(update);
        return this.mapToDTO(tempTree);
    }

    public boolean deleteTree(Long treeId){
        if(!this.repo.existsById(treeId)){
            throw new TreeNotFoundException2();
        }
        this.repo.deleteById(treeId);
        return this.repo.existsById(treeId);
    }


}
