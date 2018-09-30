package com.ks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.model.Comment;
import com.ks.model.Post;
import com.ks.model.User;
import com.ks.service.CommentService;
import com.ks.service.PostService;
import com.ks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class TestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);
	}

	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private PostService postService;


	@Bean
	CommandLineRunner runner(){
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
			try {
				List<User> users = mapper.readValue(inputStream,typeReference);
				userService.save(users);
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}

			TypeReference<List<Post>> reference1 = new TypeReference<List<Post>>(){};
			InputStream resourceAsStream1 = TypeReference.class.getResourceAsStream("/json/posts.json");
			try {
				List<Post> posts = mapper.readValue(resourceAsStream1,reference1);
				postService.save(posts);
				System.out.println("Post Saved!");
			} catch (IOException e){
				System.out.println("Unable to save posts: " + e.getMessage());
			}

			TypeReference<List<Comment>> reference = new TypeReference<List<Comment>>(){};
			InputStream resourceAsStream = TypeReference.class.getResourceAsStream("/json/comments.json");
			try {
				List<Comment> comments = mapper.readValue(resourceAsStream,reference);
				commentService.save(comments);
				System.out.println("Comment Saved!");
			} catch (IOException e){
				System.out.println("Unable to save comments: " + e.getMessage());
			}
		};
	}
}
