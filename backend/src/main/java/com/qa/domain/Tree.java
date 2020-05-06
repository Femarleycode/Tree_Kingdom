package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long treeId;
    private String treeName;
    private Long orderId;
    private String orderName;

    public Long getTreeId() {
        return TreeId;
    }

    public void setId(Long treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return treeId.equals(tree.treeId) &&
                treeName.equals(tree.treeName) &&
                orderId.equals(tree.orderId) &&
                orderName.equals(tree.orderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treeId, treeName, orderId, orderName);
    }

}