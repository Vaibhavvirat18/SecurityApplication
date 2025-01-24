package com.vvcoders.SecurityApp.SecurityApplication.repositories;

import com.vvcoders.SecurityApp.SecurityApplication.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
