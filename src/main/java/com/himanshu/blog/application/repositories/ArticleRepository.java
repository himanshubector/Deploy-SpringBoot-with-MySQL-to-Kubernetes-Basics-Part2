package com.himanshu.blog.application.repositories;


import com.himanshu.blog.application.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ArticleRepository extends JpaRepository<Article, Long>
{
    // if we want to implement any custom method or query
}
