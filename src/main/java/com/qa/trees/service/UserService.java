package com.qa.trees.service;


import com.qa.trees.domain.Trees;
import com.qa.trees.domain.Users;
import com.qa.trees.dto.UserDTO;
import com.qa.trees.exceptions.UserNotFoundException;
import com.qa.trees.repo.TreeRepository;
import com.qa.trees.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository repo;

    private TreeRepository treeRepo;

    private ModelMapper mapper;

    @Autowired
    public UserService(UserRepository repo, TreeRepository treeRepo, ModelMapper mapper) {
        this.repo = repo;
        this.treeRepo = treeRepo;
        this.mapper = mapper;
    }

    public UserDTO mapToDTO(Users user) {
        return this.mapper.map(user, UserDTO.class);
    }

    public UserDTO createUser(Users user) {
        return this.mapToDTO(this.repo.save(user));
    }

    public boolean deleteUser(Long id) {
        if (!this.repo.existsById(id)) {
            throw new UserNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

    public UserDTO findUserByID(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public List<UserDTO> readUsers() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO updateUser(Users tree, Long id) {
        Users toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
        toUpdate.setUsername(tree.getName());
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public UserDTO addTreeToUser(Long id, Trees tree) {
        Users toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
        Trees newTree = this.treeRepo.save(tree);
        toUpdate.getTrees().add(tree);
        return this.mapToDTO(this.repo.saveAndFlush(toUpdate));
    }

}}