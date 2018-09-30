package com.ks.repository;

import com.ks.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findByUserId(Long userId, Pageable pageable);
    Page<Post> findByTitle(String title, Pageable pageable);
}
