package com.qa.trees.rest;

import com.qa.trees.domain.Trees;
import com.qa.trees.domain.Users;
import com.qa.trees.dto.UserDTO;
import com.qa.trees.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody Users user) {
        return new ResponseEntity<>(this.service.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        return this.service.deleteUser(id) ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findUserById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(this.service.readUsers());
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@PathParam("id") Long id, @RequestBody Users user) {
        return new ResponseEntity<>(this.service.updateUser(user, id), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserDTO> addTreeToUser(@PathVariable Long id, @RequestBody Trees tree) {
        return new ResponseEntity<>(this.service.addTreeToUser(id, tree), HttpStatus.ACCEPTED);
    }

}
