package com.ks.controller;

import com.ks.exception.ResourceNotFoundException;
import com.ks.model.Comment;
import com.ks.model.User;
import com.ks.service.CommentService;
import com.ks.service.PostService;
import com.ks.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/posts/{postId}/comments")
    @ApiOperation(value="get getComment by postId")
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK"),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="Post not found")
    })
    public Page<Comment> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId,
                                                Pageable pageable) {
        return commentService.getCommentByPostId(postId, pageable);
    }

    @GetMapping("/users/{userId}/comments")
    @ApiOperation(value="get all user comments")
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK"),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="User not found")
    })
    public List<Comment> getAllCommentsByUserId(@PathVariable(value = "userId") Long userId,
                                                      Pageable pageable) {

        if(!userService.isExists(userId)) {
            throw new ResourceNotFoundException("User with id  " + userId + " not found");
        }

        return commentService.getCommentsByUserId(userId,pageable);

    }

    @GetMapping("/comments/{commentId}/user")
    @ApiOperation(value="get user by commentId")
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK"),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="Comments not found")
    })
    public User getUserByCommentId(@PathVariable Long commentId) {

        return commentService.getCommentById(commentId).map(comment -> comment.getPost().getUser())
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));

    }

    @GetMapping("/comments/count/{text}")
    @ApiOperation(value="count how many times text occurred in comment body")
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK"),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="Not found")
    })
    public Long countTextInCommentBody(@PathVariable String text) {

        return commentService.getAllComments().stream().filter(comment -> comment.getBody().contains(text)).count();

    }

}
