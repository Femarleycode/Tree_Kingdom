package com.qa.trees.dto;

public class TreeDTO {

    private Long treeId;
    private String treeName;
    private String orderName;


    public Long getId() {
        return treeId;
    }
    public void setId(Long id) { this.treeId = id; }

    public String getTreeName() {
        return treeName;
    }
    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getOrderName() { return orderName; }
    public void setDescription(String orderName) {
        this.orderName = orderName;
    }


    public TreeDTO(String treeName, String orderName) {
        this.treeName = treeName;
        this.orderName = orderName; }

    public TreeDTO() {
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((treeName == null) ? 0 : treeName.hashCode());
        result = prime * result + ((orderName == null) ? 0 : orderName.hashCode());
        result = prime * result + (int) (treeId ^ (treeId >>> 32));
        result = prime * result + ((treeName == null) ? 0 : treeName.hashCode());
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

    @Override
    public String toString() {
        return "TreeDTO{" +
                "treeId=" + treeId +
                ", treeName='" + treeName + '\'' +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}
