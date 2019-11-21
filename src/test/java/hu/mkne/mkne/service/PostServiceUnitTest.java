package hu.mkne.mkne.service;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.model.Post;
import hu.mkne.mkne.model.PostCategory;
import hu.mkne.mkne.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@ComponentScan(basePackageClasses = {PostService.class})
@DataJpaTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostServiceUnitTest {

    private static final long STUB_ID = 1L;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private MemberService memberService;

    @Autowired
    private PostService postService;

    private Member testMember;
    private Post testPost;
    private List<Post> testPostList;

    @BeforeEach
    public void initTest() {
        testMember = Member.builder()
                .firstName("Unit")
                .lastName("Test")
                .username("unitTest")
                .email("unit@test.com")
                .password("password")
                .build();

        testPost = Post.builder()
                .category(PostCategory.NEWS)
                .title("Unit Testing")
                .postBody("Unit testing is sexy.")
                .author(testMember)
                .isPublished(false)
                .build();
    }
}
