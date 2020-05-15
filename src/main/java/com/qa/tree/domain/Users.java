package com.qa.tree.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;


    @OneToMany(mappedBy = "iuserTrees")
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

}
