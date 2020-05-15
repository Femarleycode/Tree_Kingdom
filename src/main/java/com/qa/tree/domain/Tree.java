package com.qa.tree.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Tree")
public class Tree {

    @Id
    @GeneratedValue
    private Long treeId;
    private String treeName;
    private String orderName;

//    @Column(name = "userTrees", unique = true)
//    private String treeName;

    @ManyToOne (targetEntity = Tree.class)
    private Tree tree;


    public Tree() {
    }

    public Tree(long treeId, String treeName, String orderName) {
        this.treeId = treeId;
        this.treeName = treeName;
        this.orderName = orderName;
    }

    public Long getTreeId() { return treeId; }
    public void setId(Long treeId) { this.treeId = treeId; }

    public String getTreeName() { return treeName; }
    public void setTreeName(String treeName) { this.treeName = treeName; }

    public String getOrderName() { return orderName; }
    public void setOrderName(String orderName) { this.orderName = orderName; }

}