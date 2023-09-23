package com.himanshu.blog.application.controllers;


import com.himanshu.blog.application.entities.Article;
import com.himanshu.blog.application.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/articles")
// @PropertySource("classpath:application.yml")
public class ArticleController
{
    @Autowired
    private ArticleService articleService;


    private static final String KAFKA_TOPIC = "blog.articles.viewed";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;  // Use to publish messages to Kafka



    @PostMapping("/create")
    public ResponseEntity<Article> createArticle(@RequestBody Article article)
    {
        Article article1 = articleService.createArticle(article);

        // Publish message to Kafka for asynchronous communication
        kafkaTemplate.send(KAFKA_TOPIC, "Article viewed: " + article.getId());


        return ResponseEntity.status(HttpStatus.CREATED).body(article1);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Article> viewArticle(@PathVariable Long id)
    {
        Article article = this.articleService.getArticle(id);

        return ResponseEntity.ok(article);
    }

}
