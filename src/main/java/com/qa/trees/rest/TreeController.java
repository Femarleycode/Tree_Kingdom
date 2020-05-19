package com.qa.trees.rest;

import com.qa.trees.domain.Trees;
import com.qa.trees.dto.TreeDTO;
import com.qa.trees.dto.UserDTO;
import com.qa.trees.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/tree")
public class TreeController {

    private TreeService service;

    @Autowired
    public TreeController(TreeService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<TreeDTO> createTree(@RequestBody Trees tree) {
        return new ResponseEntity<>(this.service.createTree(tree), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTree(@PathVariable Long id) {
        return this.service.deleteTree(id) ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TreeDTO> getTree(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findTreeById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TreeDTO>> getAllTrees() {
        return ResponseEntity.ok(this.service.readTrees());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TreeDTO> updateTree(@PathParam("id") Long id, @RequestBody Trees tree) {
        return new ResponseEntity<>(this.service.updateTree(id, tree), HttpStatus.ACCEPTED);
    }

}
