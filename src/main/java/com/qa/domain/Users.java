package com.qa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    //private Set<Tree> treeList;

    @ManyToMany(mappedBy = "iuserTrees", fetch = FetchType.EAGER)
    private Set<Tree> juserTree = new HashSet<>();

    public Users() {}

    public Users(Long userId, String username) {
        this.username = username;
        this.userId = userId;
    }

    public Long getTreeId() {
        return userId;
    }
    public void setId(Long treeId) {
        this.userId = treeId;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }

//    public Set<Tree> getTreeList() { return treeList; }
//    public void setTreeList(Set<Tree> treeList) { this.treeList = treeList; }

}
