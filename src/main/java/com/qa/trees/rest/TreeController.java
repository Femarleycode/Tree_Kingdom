package com.qa.trees.rest;

import com.qa.trees.domain.Trees;
import com.qa.trees.dto.TreeDTO;
import com.qa.trees.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TreeDTO> createTree(@RequestBody Trees trees){
        return new ResponseEntity<TreeDTO>(this.service.createTree(trees), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteTree/{treeId}")
    public ResponseEntity<?> deleteTree(@PathVariable Long treeId){
        return this.service.deleteTree(treeId)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getTreeById/{treeId}")
    public ResponseEntity<TreeDTO> getTreeById(@PathVariable Long treeId){
        return ResponseEntity.ok(this.service.findTreeById(treeId));
    }

//    @PutMapping("/updateTree/{treeId}")
//    public ResponseEntity<TreeDTO> updateTree(@PathVariable Long treeId, @RequestBody Trees trees){
//        return ResponseEntity.ok(this.service.updateTree(treeId, trees));
//    }
//
//    @PutMapping("/updateTree2")
//    public ResponseEntity<TreeDTO> updateTree2(@PathParam("treeId") Long treeId, @RequestBody Trees trees){
//        return ResponseEntity.ok(this.service.updateTree(treeId, trees));
//    }

}