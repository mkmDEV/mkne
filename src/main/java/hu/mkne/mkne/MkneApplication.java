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
                    .title("Fenntarthatóság témaköre a magyar felsőoktatásban")
                    .postBody("A Magyar Tudományos Akadémia és az UNESCO Magyar Nemzeti Bizottság az ELTE Humánökológia mesterszak közreműködésével, 2018. november 19-én tudományos tanácskozást rendezett, amin a Magyar Környezeti Nevelési Egyesület több tagja is részt vett. \"A fenntarthatóság pedagógiája\" szekció vitaindító anyagát Victor András készítette, a vitát Varga Attila vezette. A szekciókban kialakult álláspontokat és a javaslatokat bemutató kiadvány itt letölthető, vagy innen elérhető: http://ofi.hu/hir/fenntarthatosag-temakor-es-magyar-felsooktatas")
                    .author(member1)
                    .isPublished(false)
                    .build();

            Post post2 = Post.builder()
                    .category(PostCategory.NEWS)
                    .title("Ökokarácsony")
                    .postBody("Bízunk benne, hogy újra nagyon sokan tölthetjük együtt az adventi időszak egy meghitt délelőttjét, ajándékok készítésével készülve az ünnepre.\n" +
                            "Idén december 7-én, szombaton 10–13 óráig tartjuk ökokarácsonyi rendezvényünket az MKNE és a Lágymányosi Bárdos Lajos Két Tanítási Nyelvű Általános Iskola közös szervezésében." +
                            "\nCímünk: (1117 Budapest, Baranyai utca 16‒18.) ")
                    .author(member1)
                    .isPublished(false)
                    .build();

            Post post3 = Post.builder()
                    .category(PostCategory.NEWS)
                    .title("Országos Környezeti Nevelési Konferencia")
                    .postBody("Szakmai, civil és tudományos szervezetek közösségeit képviselő szakértők, ökopedagógusok, szemléletformálók, kommunikátorok és kutatók közreműködésével elkészültek a Nemzeti Környezeti Nevelési Stratégiai Alapvetés legújabb kéziratai. A megújított Alapvetés véglegesítése előtt, az immár 21 éves hagyománynak megfelelően, nyilvános konferenciát tartott a munkát összefogó Magyar Környezeti Nevelési Egyesület.")
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
                    .postBody("Szeretettel várjuk a 8-14 éves diákokat következő Agostyán Ágoston-ligetbeli táborunkba augusztus 22-26 között!")
                    .title("Mocorgó Puli tábor!")
                    .author(member1)
                    .isPublished(false)
                    .build();

            Post advert2 = Post.builder()
                    .category(PostCategory.ADVERT)
                    .title("1%")
                    .postBody("Kedves Tagtársunk, Támogatónk!\n" +
                            "Örömmel vesszük és megköszönjük, ha adód 1%-át egyesületünknek ajánlod fel és erre biztatod ismerőseidet is.\n" +
                            "A befolyt összeget programjaink megvalósítására fordítjuk.")
                    .author(member1)
                    .isPublished(false)
                    .build();

            memberRepository.save(member1);
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
            postRepository.save(unpublishedPost);
            postRepository.save(advert);
            postRepository.save(advert2);

            post1.publishPost();
            post2.publishPost();
            post3.publishPost();
            advert.publishPost();
            advert2.publishPost();

            postRepository.saveAndFlush(post1);
            postRepository.saveAndFlush(post2);
            postRepository.saveAndFlush(post3);
            postRepository.saveAndFlush(advert);
            postRepository.saveAndFlush(advert2);
        };
    }
}