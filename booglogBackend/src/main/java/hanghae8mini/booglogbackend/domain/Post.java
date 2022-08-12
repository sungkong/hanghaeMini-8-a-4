package hanghae8mini.booglogbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = true)
    private List<Comment> commentList;

    @Column(nullable = true)
    private Member member;

    @Column(nullable = false)
    private Category category;
}
