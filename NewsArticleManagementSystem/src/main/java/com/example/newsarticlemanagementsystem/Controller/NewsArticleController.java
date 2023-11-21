package com.example.newsarticlemanagementsystem.Controller;

import com.example.newsarticlemanagementsystem.ApiResponse.ApiResponse;
import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import com.example.newsarticlemanagementsystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nams")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getArticles(){
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getNews());
    }
    @PostMapping("/add")
    public ResponseEntity addArticle(@RequestBody @Valid NewsArticle newsArticle , Errors errors){
        if(errors.hasErrors()) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(errors.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST.toString()));
        newsArticleService.add(newsArticle);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Article added",HttpStatus.OK.toString()));
    }
@PutMapping("/update/{id}")
    public ResponseEntity updateArticle(@PathVariable String id , @RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(errors.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST.toString()));
        if(newsArticleService.update(id,newsArticle)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Article updated",HttpStatus.OK.toString()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("not found",HttpStatus.NOT_FOUND.toString()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle(@PathVariable String id){
        if(newsArticleService.delete(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Article deleted",HttpStatus.OK.toString()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Article is not found",HttpStatus.NOT_FOUND.toString()));
    }

    @GetMapping("/change/{id}")
    public ResponseEntity publish(@PathVariable String id){
        if(newsArticleService.publish(id)) return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("change",HttpStatus.OK.toString()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Article not found or is already published",HttpStatus.NOT_FOUND.toString()));
    }

    @GetMapping("/getallpublished")
    public ResponseEntity publishedArticles(){
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getAll());
    }
    @GetMapping("/getbycategory/{category}")
    public ResponseEntity articleByCatogry(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getByCategory(category));
    }

}
