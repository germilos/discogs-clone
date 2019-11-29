package com.milos.releasemicroservice.repo;

import com.milos.releasemicroservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
}
