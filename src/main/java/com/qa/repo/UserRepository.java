package com.qa.repo;

import com.qa.domain.Tree;
import com.qa.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    //List<Users> findByUserIdEquals(double UserId);
}
