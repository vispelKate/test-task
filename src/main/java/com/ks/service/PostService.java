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

    public Optional<Post> post(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> list() {
        return postRepository.findAll();
    }

    public List<Post> list(String title, Pageable pageable) {
        return postRepository.findByTitle(title, pageable).getContent();
    }

    public boolean isExists(Long id) {
        return postRepository.existsById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void save(List<Post> posts) {
        postRepository.saveAll(posts);
    }

}
