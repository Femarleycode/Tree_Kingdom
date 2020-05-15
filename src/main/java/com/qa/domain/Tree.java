package com.qa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tree")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treeId;
    private String treeName;
    private String orderName;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "userTrees",
        joinColumns = {
            @JoinColumn(name = "treeName", referencedColumnName = "treeName",
                    nullable = false, updatable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "username", referencedColumnName = "username",
                    nullable = false, updatable = false)})
    private Set<Users> iuserTrees = new HashSet<>();

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