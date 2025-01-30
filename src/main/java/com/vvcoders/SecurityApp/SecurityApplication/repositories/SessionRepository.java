package com.vvcoders.SecurityApp.SecurityApplication.repositories;

import com.vvcoders.SecurityApp.SecurityApplication.entities.Session;
import com.vvcoders.SecurityApp.SecurityApplication.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    List<Session> findByUser(Users user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
