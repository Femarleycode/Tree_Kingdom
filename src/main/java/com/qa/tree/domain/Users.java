package com.qa.tree.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;


    @OneToMany(mappedBy = "tree")
    private List<Tree> treeList = new ArrayList<>();

    public Users() {}

    public Users(Long userId, String username) {
        this.username = username;
        this.userId = userId;
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

    public List<Tree> getTreeList() {
        return treeList;
    }
    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
    }

//    public Set<Tree> getTreeList() { return treeList; }
//    public void setTreeList(Set<Tree> treeList) { this.treeList = treeList; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId.equals(users.userId) &&
                username.equals(users.username) &&
                treeList.equals(users.treeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, treeList);
    }
}
