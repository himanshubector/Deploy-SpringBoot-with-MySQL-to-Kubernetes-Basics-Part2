package com.himanshu.blog.application.services.impl;

import com.himanshu.blog.application.entities.Article;
import com.himanshu.blog.application.repositories.ArticleRepository;
import com.himanshu.blog.application.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArticleServiceImpl implements ArticleService
{
    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public Article createArticle(Article article)
    {
        return this.articleRepository.save(article);
    }



    // When the blog application receives a request to render an article, it publishes a new message on a Kafka Topic 'blog.articles.viewed'.
    // This will specify the payload of the id of the article and, more precisely, the urn (uniform resource name — a globally unique identifier in our application).
    // The impressions service, which is subscribed to that queue, will consume the message, read the urn in the payload, and increment the associated counter.
    @Override
    public Article getArticle(Long articleId)
    {
        return this.articleRepository.findById(articleId).orElseThrow(()-> new RuntimeException("Article you are looking for not found on the server !!!!"));

    }
}
