package hanghae8mini.booglogbackend.domain;

import java.util.List;

public class Comment extends Timestamped{

    private Long commentId;
    private String content;
    private Member member;
    private List<Post> postList;
    private List<Comment> commentList;
}
