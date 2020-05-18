package com.qa.trees.dto;

public class TreeDTO {

    private Long treeId;
    private String treeName;
    private String orderName;

    public TreeDTO() {
    }

    public TreeDTO(String treeName, String orderName) {
        this.treeName = treeName;
        this.orderName = orderName;
    }

    public Long getId() {
        return treeId;
    }
    public void setId(Long id) { this.treeId = treeId; }

    public String getTitle() {
        return treeName;
    }
    public void setTitle(String treeName) {
        this.treeName = treeName;
    }

    public String getDescription() {
        return orderName;
    }
    public void setDescription(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TreeDTO other = (TreeDTO) obj;
        if (orderName == null) {
            if (other.orderName != null)
                return false;
        } else if (!orderName.equals(other.orderName))
            return false;
        if (treeId == null) {
            if (other.treeId != null)
                return false;
        } else if (!treeId.equals(other.treeId))
            return false;
        if (treeName == null) {
            if (other.treeName != null)
                return false;
        } else if (!treeName.equals(other.treeName))
            return false;
        return true;
    }

}
