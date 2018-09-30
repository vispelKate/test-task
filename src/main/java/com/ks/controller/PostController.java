package com.ks.controller;

import com.ks.exception.ResourceNotFoundException;
import com.ks.model.Post;
import com.ks.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Api(value="/posts",description="Posts",produces ="application/json")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{title}")
    @ApiOperation(value="get posts by title")
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK"),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    public List<Post> getPostsByTitle(@PathVariable String title, Pageable pageable) {
        return postService.getPostsByTitle(title,pageable);
    }

    @DeleteMapping("/{postId}")
    @ApiOperation(value="delete getPost")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Post deleted"),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="Post not found")
    })
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {

        return postService.getPost(postId).map(post -> {
            postService.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
