package hu.mkne.mkne.controller;

import hu.mkne.mkne.model.Post;
import hu.mkne.mkne.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public List<Post> getPosts() {
        return service.getAllPublishedPosts();
    }

    @GetMapping("/ads")
    public List<Post> getAds() {
        return service.getAds();
    }

    @GetMapping("/news")
    public List<Post> getAllPublishedNews() {
        return service.getAllPublishedNews();
    }

}
