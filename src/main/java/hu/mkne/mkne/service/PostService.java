package hu.mkne.mkne.service;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.model.Post;
import hu.mkne.mkne.model.PostCategory;
import hu.mkne.mkne.repository.MemberRepository;
import hu.mkne.mkne.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public List<Post> getOrderedPosts() {
        return postRepository.getAllByOrderByPublishDateDesc();
    }

    public List<Post> getAds() {
        return postRepository.getPostsByCategoryAndIsPublishedTrueOrderByPublishDateDesc(PostCategory.ADVERT);
    }

    public List<Post> getAllPublishedPosts() {
        return postRepository.getPostsByIsPublishedTrueOrderByPublishDateDesc();
    }

    public List<Post> getAllPublishedNews() {
        return postRepository.getPostsByCategoryAndIsPublishedTrueOrderByPublishDateDesc(PostCategory.NEWS);
    }

    public List<Post> findAll(String q) {
        return postRepository.findByPostBodyContainingAndIsPublishedTrueOrderByPublishDateDesc(q);
    }

    public Post addPost(Post post) {
        Member author = memberRepository.findById(1L).orElse(null);
        post.setAuthor(author);
        if (post.getIsPublished()) post.publishPost();
        postRepository.save(post);
        return post;
    }

    public Post updatePost(Long id, Post post) {
        Post amendPost = postRepository.findById(id).orElse(null);
        if (amendPost != null) {
            amendPost.setTitle(post.getTitle());
            amendPost.setAuthor(post.getAuthor());
            amendPost.setCategory(post.getCategory());
            amendPost.setIsPublished(post.getIsPublished());
            amendPost.setPostBody(post.getPostBody());
            postRepository.save(amendPost);
            if (post.getIsPublished()) amendPost.publishPost();
        }

        return amendPost;
    }

    public boolean deletePost(Long id) {
        Post removePost = postRepository.findById(id).orElse(null);
        if (removePost == null) return false;
        postRepository.delete(removePost);
        return true;
    }
}
