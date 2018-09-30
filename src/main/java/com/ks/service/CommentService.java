package com.ks.service;

import com.ks.model.Comment;
import com.ks.model.Post;
import com.ks.repository.CommentRepository;
import com.ks.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public boolean isExists(Long id) {
        return commentRepository.existsById(id);
    }
    public Page<Comment> getCommentByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId,pageable);
    }

    public List<Comment> getCommentsByUserId(Long userId, Pageable pageable) {
        Page<Post> userPosts = postRepository.findByUserId(userId,pageable);
        List<Page<Comment>> comments = userPosts.stream().map(post -> getCommentByPostId(post.getId(),pageable)).collect(Collectors.toList());
        List<Comment> commentList = new ArrayList<>();
        for(Page<Comment> commentPage: comments){
            commentList.addAll(commentPage.getContent());
        }
        return commentList;
    }


    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void save(List<Comment> comments) {
        commentRepository.saveAll(comments);
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
