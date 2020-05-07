package com.qa.rest;

import com.qa.domain.Tree;
import com.qa.dto.TreeDTO;
import com.qa.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class TreeController {

    private final TreeService service;

    @Autowired
    public TreeController(TreeService service) {
        this.service = service;
    }

    @GetMapping("/getAllTrees")
    public ResponseEntity<List<TreeDTO>> getAllTrees(){
        return ResponseEntity.ok(this.service.readTrees());
    }

    @PostMapping("/createTree")
    public ResponseEntity<TreeDTO> createTree(@RequestBody Tree tree){
        return new ResponseEntity<TreeDTO>(this.service.createTree(tree), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteTree/{id}")
    public ResponseEntity<?> deleteTree(@PathVariable Long id){
        return this.service.deleteTree(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getTreeById/{id}")
    public ResponseEntity<TreeDTO> getTreeById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findTreeById(id));
    }

//    @PutMapping("/updateTree/{id}")
//    public ResponseEntity<TreeDTO> updateTree(@PathVariable Long id, @RequestBody Tree tree){
//        return ResponseEntity.ok(this.service.updateTree(id, tree));
//    }
//
//    @PutMapping("/updateTree2")
//    public ResponseEntity<TreeDTO> updateTree2(@PathParam("id") Long id, @RequestBody Tree tree){
//        return ResponseEntity.ok(this.service.updateTree(id, tree));
//    }

}