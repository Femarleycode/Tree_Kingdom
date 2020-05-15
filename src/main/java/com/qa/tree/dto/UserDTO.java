package com.qa.tree.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;

    private String name;

    private List<TreeDTO> ducks = new ArrayList<>();

    public UserDTO(String name) {
        this.name = name;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeDTO> getTrees() {
        return ducks;
    }

    public void setTrees(List<TreeDTO> ducks) {
        this.ducks = ducks;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ducks == null) ? 0 : ducks.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDTO other = (UserDTO) obj;
        if (ducks == null) {
            if (other.ducks != null)
                return false;
        } else if (!ducks.equals(other.ducks))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
