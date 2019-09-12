package hu.mkne.mkne.service;

import hu.mkne.mkne.model.Post;
import hu.mkne.mkne.model.PostCategory;
import hu.mkne.mkne.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    public List<Post> getOrderedPosts() {
        return repository.getAllByOrderByPublishDateDesc();
    }

    public List<Post> getAds() {
        return repository.getPostsByCategoryAndIsPublishedTrueOrderByPublishDateDesc(PostCategory.ADVERT);
    }

    public List<Post> getAllPublishedPosts() {
        return repository.getPostsByIsPublishedTrueOrderByPublishDateDesc();
    }

    public List<Post> getAllPublishedNews() {
        return repository.getPostsByCategoryAndIsPublishedTrueOrderByPublishDateDesc(PostCategory.NEWS);
    }
}
