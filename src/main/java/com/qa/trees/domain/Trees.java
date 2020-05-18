package com.qa.trees.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Trees")
public class Trees {

    @Id
    @GeneratedValue
    private Long treeId;
    private String treeName;
    private String orderName;

    @ManyToOne (targetEntity = Trees.class)
    private Trees trees;

    public Trees() {
    }

    public Trees(Long treeId, String treeName, String orderName) {
        this.treeId = treeId;
        this.treeName = treeName;
        this.orderName = orderName;
    }

    public Trees(String treeName, String orderName) {
        this.treeName = treeName;
        this.orderName = orderName;
    }

    public Long getId() { return treeId; }
    public void setId(Long treeId) { this.treeId = treeId; }

    public String getTreeName() { return treeName; }
    public void setTreeName(String treeName) { this.treeName = treeName; }

    public String getOrderName() { return orderName; }
    public void setOrderName(String orderName) { this.orderName = orderName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trees trees1 = (Trees) o;
        return treeId.equals(trees1.treeId) &&
                treeName.equals(trees1.treeName) &&
                orderName.equals(trees1.orderName) &&
                trees.equals(trees1.trees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treeId, treeName, orderName, trees);
    }
}