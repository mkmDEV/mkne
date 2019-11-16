package hu.mkne.mkne.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member author;

    @CreationTimestamp
    private LocalDateTime creationDate;

    private LocalDateTime publishDate;

    @Column(nullable = false)
    private Boolean isPublished;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String postBody;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PostCategory category;


    public void publishPost() {
        this.isPublished = true;
        setPublishDate(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author +
                ", creation_date=" + creationDate +
                ", publish_date=" + publishDate +
                ", title='" + title + '\'' +
                ", post_body='" + postBody + '\'' +
                ", category=" + category +
                '}';
    }

}
