package com.example.newsarticlemanagementsystem.Service;


import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsArticleService {
        List<NewsArticle> newsArticles = new ArrayList<>();

        public List<NewsArticle> getNews(){
            return newsArticles;
        }

        public void add(NewsArticle newsArticle){
            newsArticles.add(newsArticle);
        }
        public boolean update(String id, NewsArticle newsArticle){
            for(int i = 0; i < newsArticles.size();i++){
                if(newsArticles.get(i).getId().equals(id)) {
                    newsArticles.set(i,newsArticle);
                    return true;
                }
            }
            return false;
        }

        public boolean delete(String id){
            for(int i = 0 ; i < newsArticles.size();i++){
                if(newsArticles.get(i).getId().equals(id)){
                    newsArticles.remove(i);
                    return true;
                }
            }
            return false;
        }

        public boolean publish(String id ){
            for(int i = 0; i<newsArticles.size();i++){
                if(newsArticles.get(i).getId().equals(id)&& !(newsArticles.get(i).isPublished())){
                    NewsArticle n = newsArticles.get(i);
                    n.setPublishDate(LocalDateTime.now());
                    n.setPublished(true);
                    newsArticles.set(i,n);
                    return true;
                }
            }
            return  false;
        }
        public List<NewsArticle> getAll(){
            List<NewsArticle> news = new ArrayList<>();
            for(NewsArticle n : newsArticles){
                if(n.isPublished()) news.add(n);
            }
            return news;
        }

    public List<NewsArticle> getByCategory(String category){
        List<NewsArticle> bycategory = new ArrayList<>();
        for(NewsArticle n : newsArticles){
            if(n.getCategory().equals(category)) bycategory.add(n);
        }
        return bycategory;
    }
}
