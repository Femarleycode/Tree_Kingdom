package com.qa.tree.service;

import java.util.List;
import java.util.stream.Collectors;

import com.qa.tree.domain.Users;
import com.qa.tree.dto.UserDTO;
import com.qa.tree.exceptions.UserNotFoundException;
import com.qa.tree.repo.UserRepository;
import com.qa.tree.utils.MyBeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserRepository repository;

    private ModelMapper mapper;

    @Autowired
    public UserService(UserRepository repo, ModelMapper mapper) {
        this.repository = repo;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(Users user) {
        return this.mapper.map(user, UserDTO.class);
    }

    public UserDTO createUser(Users user) {
        return mapToDTO(this.repository.save(user));
    }

    public boolean deleteUser(Long id) {
        if (!this.repository.existsById(id)) {
            throw new UserNotFoundException();
        }
        this.repository.deleteById(id);
        return this.repository.existsById(id);
    }

    public UserDTO findUserByID(Long id) {
        return mapToDTO(this.repository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public List<UserDTO> readUsers() {
        return this.repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO updateTree(Users user, Long id) {
        Users toUpdate = this.repository.findById(id).orElseThrow(UserNotFoundException::new);
        MyBeanUtils.mergeNotNull(user, toUpdate);
        return this.mapToDTO(this.repository.save(toUpdate));
    }

}