package com.himanshu.blog.application.services;


import com.himanshu.blog.application.entities.Article;

public interface ArticleService
{
    // create Article
    Article createArticle(Article article);


    // get single Article
    Article getArticle(Long articleId);

}
