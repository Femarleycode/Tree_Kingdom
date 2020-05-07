package com.qa.repo;

import com.qa.domain.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {

    //List<Tree> findByTreeNameContaining(String TreeName);
}