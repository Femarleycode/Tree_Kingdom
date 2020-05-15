package com.qa.tree.domain;

import javax.persistence.*;
import java.util.*;

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

    public Tree(Long treeId, String treeName, String orderName) {
        this.treeId = treeId;
        this.treeName = treeName;
        this.orderName = orderName;
    }

    public Tree(String treeName, String orderName) {
        this.treeName = treeName;
        this.orderName = orderName;
    }

    public Long getTreeId() { return treeId; }
    public void setId(Long treeId) { this.treeId = treeId; }

    public String getTreeName() { return treeName; }
    public void setTreeName(String treeName) { this.treeName = treeName; }

    public String getOrderName() { return orderName; }
    public void setOrderName(String orderName) { this.orderName = orderName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree1 = (Tree) o;
        return treeId.equals(tree1.treeId) &&
                treeName.equals(tree1.treeName) &&
                orderName.equals(tree1.orderName) &&
                tree.equals(tree1.tree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treeId, treeName, orderName, tree);
    }
}