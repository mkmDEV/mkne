package hu.mkne.mkne.controller;

import hu.mkne.mkne.model.Post;
import hu.mkne.mkne.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.getAllPublishedPosts();
    }

    @GetMapping("/ads")
    public List<Post> getAds() {
        return postService.getAds();
    }

    @GetMapping("/news")
    public List<Post> getAllPublishedNews() {
        return postService.getAllPublishedNews();
    }

    @PostMapping
    public Post addPost(@Valid @RequestBody Post post) {
        return postService.addPost(post);
    }

    @PutMapping("{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }

}
