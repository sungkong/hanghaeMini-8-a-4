package hanghae8mini.booglogbackend.domain;

import java.util.List;

public class Post extends Timestamped {

    Long postId;
    String title;
    String bookTitle;
    String author;
    String content;
    String imageUrl;
    List<Comment> commentList;
    Member member;
    Category category;
}
