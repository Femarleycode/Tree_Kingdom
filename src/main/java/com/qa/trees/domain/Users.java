package com.qa.trees.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;

    @OneToMany(mappedBy = "trees")
    private List<Trees> treesList = new ArrayList<>();

    public Users() {}

    public Users(Long userId, String username) {
        this.username = username;
        this.userId = userId;
    }

    public String getName() {
        return username;
    }
    public Long getId() {
        return userId;
    }



    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public List<Trees> getTrees() {
        return treesList;
    }
    public void setTrees(List<Trees> treesList) {
        this.treesList = treesList;
    }

//    public Set<Trees> getTreeList() { return treeList; }
//    public void setTreeList(Set<Trees> treeList) { this.treeList = treeList; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId.equals(users.userId) &&
                username.equals(users.username) &&
                treesList.equals(users.treesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, treesList);
    }
}