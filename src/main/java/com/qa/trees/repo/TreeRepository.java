package com.qa.trees.repo;

import com.qa.trees.domain.Trees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<Trees, Long> {

    //List<Trees> findByTreeNameContaining(String TreeName);
}