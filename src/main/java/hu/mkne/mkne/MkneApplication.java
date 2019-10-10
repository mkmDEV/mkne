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
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RequiredArgsConstructor
@EnableSwagger2
public class MkneApplication {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(MkneApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Member member1 = Member.builder()
                    .email("test@test.com")
                    .username("admin")
                    .password("1234")
                    .firstName("Elek")
                    .lastName("Teszt")
                    .build();

            Post post1 = Post.builder()
                    .category(PostCategory.NEWS)
                    .title("First test")
                    .postBody("This is a test post. Dolorem ipsum sit dolor amet.")
                    .author(member1)
                    .isPublished(false)
                    .build();

            Post post2 = Post.builder()
                    .category(PostCategory.NEWS)
                    .title("Fenntarthatóság témaköre a magyar felsőoktatásban")
                    .postBody("A Magyar Tudományos Akadémia és az UNESCO Magyar Nemzeti Bizottság az ELTE Humánökológia mesterszak közreműködésével, 2018. november 19-én tudományos tanácskozást rendezett, amin a Magyar Környezeti Nevelési Egyesület több tagja is részt vett. \"A fenntarthatóság pedagógiája\" szekció vitaindító anyagát Victor András készítette, a vitát Varga Attila vezette. A szekciókban kialakult álláspontokat és a javaslatokat bemutató kiadvány itt letölthető, vagy innen elérhető: http://ofi.hu/hir/fenntarthatosag-temakor-es-magyar-felsooktatas")
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
                    .postBody("This is a very new fancy advertisement.")
                    .title("Vedd meg most!")
                    .author(member1)
                    .isPublished(false)
                    .build();

            Post advert2 = Post.builder()
                    .category(PostCategory.ADVERT)
                    .title("1%")
                    .postBody("\tKedves Tagtársunk, Támogatónk!\n" +
                            "Örömmel vesszük és megköszönjük,  ha adód 1%-át egyesületünknek ajánlod fel, s erre biztatod ismerőseidet is.\n" +
                            "A befolyt összeget programjaink megvalósítására fordítjuk.\n")
                    .author(member1)
                    .isPublished(false)
                    .build();


            memberRepository.save(member1);
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(unpublishedPost);
            postRepository.save(advert);
            postRepository.save(advert2);

            post1.publishPost();
            post2.publishPost();
            advert.publishPost();
            advert2.publishPost();

            postRepository.saveAndFlush(post1);
            postRepository.saveAndFlush(post2);
            postRepository.saveAndFlush(advert);
            postRepository.saveAndFlush(advert2);
        };
    }
}