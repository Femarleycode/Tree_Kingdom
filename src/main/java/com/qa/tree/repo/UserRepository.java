package com.qa.tree.repo;

import com.qa.tree.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    //List<Users> findByUserIdEquals(double UserId);
}
