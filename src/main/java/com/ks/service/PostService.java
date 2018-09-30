package com.ks.service;

import com.ks.model.Post;
import com.ks.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostsByTitle(String title, Pageable pageable) {
        return postRepository.findByTitle(title, pageable).getContent();
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void save(List<Post> posts) {
        postRepository.saveAll(posts);
    }

}
