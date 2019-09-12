package hu.mkne.mkne;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.model.Post;
import hu.mkne.mkne.model.PostCategory;
import hu.mkne.mkne.repository.MemberRepository;
import hu.mkne.mkne.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@RequiredArgsConstructor
public class MkneApplication {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(MkneApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Member member1 = Member.builder()
                    .email("test@test.com")
                    .username("test")
                    .password("1234")
                    .firstName("Elek")
                    .lastName("Teszt")
                    .build();

            Post post1 = Post.builder()
                    .category(PostCategory.NEWS)
                    .postBody("This is a test post. Dolorem ipsum sit dolor amet.")
                    .title("First test")
                    .author(member1)
                    .isPublished(false)
                    .build();

            Post unpublishedPost = Post.builder()
                    .category(PostCategory.NEWS)
                    .postBody("This post never published. Only for testing.")
                    .title("Unpublished post test")
                    .author(member1)
                    .isPublished(false)
                    .build();

            Post advert = Post.builder()
                    .category(PostCategory.ADVERT)
                    .postBody("This is a very new fancy advertistment.")
                    .title("Veddmegmost!")
                    .author(member1)
                    .isPublished(false)
                    .build();


            memberRepository.save(member1);
            postRepository.save(post1);
            postRepository.save(unpublishedPost);
            postRepository.save(advert);
            post1.publishPost();
            advert.publishPost();
            postRepository.saveAndFlush(post1);
            postRepository.saveAndFlush(advert);
        };
    }
}