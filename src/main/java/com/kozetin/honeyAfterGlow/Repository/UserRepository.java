package com.kozetin.honeyAfterGlow.Repository;

import com.kozetin.honeyAfterGlow.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
