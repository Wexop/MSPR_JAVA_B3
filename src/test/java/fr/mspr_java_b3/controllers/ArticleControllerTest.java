package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.entities.Article;
import fr.mspr_java_b3.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleControllerAChanger.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArticleRepository repository;

    String token = new JwtUtilTest().getFakeToken();

    @Test
    void getArticle_by_id() throws Exception {
        int articleId = 1;
        Article article = new Article();
        article.setId(1);

        Mockito.when(repository.findById(articleId)).thenReturn(Optional.of(article));

        this.mvc.perform(get("/article_by_id/{id}", articleId).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(article.getId())));

        verify(repository).findById(articleId);
    }

    @Test
    void getAllArticle() throws Exception {
        List<Article> articles = Arrays.asList(new Article(), new Article());

        Mockito.when(repository.findAll()).thenReturn(articles);

        this.mvc.perform(get("/article/all").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());

        verify(repository).findAll();
    }

    @Test
    void postArticle() throws Exception {
        Article article = new Article();

        Mockito.when(repository.save(any(Article.class))).thenReturn(article);

        this.mvc.perform(post("/article/one").header("Authorization", "Bearer " + token)
                .content(asJsonString(article))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        verify(repository).save(any(Article.class));
    }

    @Test
    void putArticle() throws Exception {
        int articleId = 1;
        Article article = new Article();
        article.setId(articleId);

        Mockito.when(repository.getReferenceById(articleId)).thenReturn(article);
        Mockito.when(repository.save(any(Article.class))).thenAnswer(e -> e.getArgument(0));

        this.mvc.perform(put("/article/{id}", articleId).header("Authorization", "Bearer " + token)
                .content(asJsonString(article))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(articleId));

        verify(repository).getReferenceById(articleId);
        verify(repository).save(any(Article.class));
    }

    @Test
    void deleteArticle() throws Exception {
        int articleId = 1;
        doNothing().when(repository).deleteById(articleId);

        this.mvc.perform(delete("/article/{id}", articleId).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(repository).deleteById(articleId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
