package com.example.newsarticlemanagementsystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "id should not be empty")
    private String id;
    @NotEmpty(message = "title should not be empty")
    @Size(max = 100,message = "title must be less than 100 characters")
    private String title;
    @NotEmpty(message = "author should not be empty")
    @Size(min=4,max = 20,message = "author name should be from 4 characters to 20 characters")
    private String author;
    @NotEmpty(message = "content should not be empty")
    @Size(min = 200 , message = "content should be more than 200 characters")
    private String content;
    @NotEmpty(message = "category should not be empty")
    @Pattern(regexp = "politics|sports|technology")
    private String category;
    @NotEmpty(message = "image url should not be empty")
    private String imageUrl;
    @Value("${props.boolean.isPublished:false}")
    private boolean isPublished;
    private Date publishDate;

}
