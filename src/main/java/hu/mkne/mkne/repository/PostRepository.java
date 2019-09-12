package hu.mkne.mkne.repository;

import hu.mkne.mkne.model.Post;
import hu.mkne.mkne.model.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> getAllByOrderByPublishDateDesc();

    List<Post> getPostsByCategoryAndIsPublishedTrueOrderByPublishDateDesc(PostCategory category);

    List<Post> getPostsByIsPublishedTrueOrderByPublishDateDesc();
}
