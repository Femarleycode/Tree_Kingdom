package com.qa.tree.repo;

import com.qa.tree.domain.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {

    //List<Tree> findByTreeNameContaining(String TreeName);
}