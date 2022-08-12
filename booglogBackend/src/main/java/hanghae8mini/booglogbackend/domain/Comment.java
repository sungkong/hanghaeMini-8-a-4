package hanghae8mini.booglogbackend.domain;

import java.util.List;

public class Comment extends Timestamped{

    Long commentId;
    String content;
    Member member;
    List<Post> postList;
    List<Comment> commentList;
}
