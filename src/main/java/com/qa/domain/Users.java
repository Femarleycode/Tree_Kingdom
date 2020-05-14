package com.qa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private Set<Tree> treeList;

    @ManyToMany(mappedBy = "concat", fetch = FetchType.EAGER)
    private Set<Users> users = new HashSet<>();

    public Users(String username) { this.username = username; }
    public Users() {}

    public Long getTreeId() {
        return userId;
    }
    public void setId(Long treeId) {
        this.userId = treeId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Tree> getTreeList() { return treeList; }
    public void setTreeList(Set<Tree> treeList) { this.treeList = treeList; }

}
