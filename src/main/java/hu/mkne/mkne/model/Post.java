package hu.mkne.mkne.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Timestamp creationDate;

    private Timestamp publishDate;

    private String title;

    private String postBody;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PostCategory category;

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
