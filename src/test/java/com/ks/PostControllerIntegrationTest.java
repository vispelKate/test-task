package com.ks;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestTaskApplication.class)
@SpringBootTest
public class PostControllerIntegrationTest {


    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp()  {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetPostsByTitle() throws Exception {
        String title = "dolorem dolore est ipsam";
        mvc.perform(MockMvcRequestBuilders.get("/posts/"+title).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].status").value(200))
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].title").exists())
                .andExpect(jsonPath("$.[0].title").value(title))
                .andDo(print());
    }

    @Test
    public void testDeletePost() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/posts/"+1).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].status").value(200))
                .andDo(print());;
    }
}
