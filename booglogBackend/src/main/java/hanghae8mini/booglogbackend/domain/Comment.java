package hanghae8mini.booglogbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Timestamped{

    private Long commentId;
    private String content;
    private Member member;
    private List<Post> postList;
    private List<Comment> commentList;
}
