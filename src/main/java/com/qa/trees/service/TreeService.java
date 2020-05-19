package com.qa.trees.service;

import com.qa.trees.domain.Trees;
import com.qa.trees.dto.TreeDTO;
import com.qa.trees.exceptions.TreeNotFoundException2;
import com.qa.trees.repo.TreeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeService {

    private final TreeRepository repo;
    private final ModelMapper mapper;
    private TreeDTO mapToDTO(Trees trees){
        return this.mapper.map(trees, TreeDTO.class);
    }

    @Autowired
    public TreeService(TreeRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<TreeDTO> readTrees(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TreeDTO createTree(Trees trees){
        Trees tempTrees = this.repo.save(trees);
        return this.mapToDTO(tempTrees);
    }

    public TreeDTO findTreeById(Long treeId){
        return this.mapToDTO(this.repo.findById(treeId)
                .orElseThrow(TreeNotFoundException2::new));
    }

    public TreeDTO updateTree(Long treeId, Trees trees){
        Trees update = this.repo.findById(treeId).orElseThrow(TreeNotFoundException2::new);
        update.setTreeName(trees.getTreeName());
        update.setOrderName(trees.getOrderName());
        Trees tempTrees = this.repo.save(update);
        return this.mapToDTO(tempTrees);
    }

    public boolean deleteTree(Long treeId){
        if(!this.repo.existsById(treeId)){
            throw new TreeNotFoundException2();
        }
        this.repo.deleteById(treeId);
        return this.repo.existsById(treeId);
    }


}
