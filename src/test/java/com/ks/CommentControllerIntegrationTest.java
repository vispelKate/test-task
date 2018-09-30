package com.ks;

import com.ks.controller.CommentController;
import com.ks.controller.PostController;
import com.ks.model.Comment;
import com.ks.service.CommentService;
import com.ks.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestTaskApplication.class)
@SpringBootTest
public class CommentControllerIntegrationTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private CommentService commentService;

    @Before
    public void setUp()  {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetAllCommentsByPostId() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/posts/"+1+"/comments").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].status").value(200))
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].postId").value(1))
                .andDo(print());
    }

    @Test
    public void testGetAllCommentsByUserId() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/users/"+1+"/comments").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].status").value(200))
                .andDo(print());
    }

    @Test
    public void testUserByCommentId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/comments/"+1+"/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].status").value(200))
                .andDo(print());
    }
}
