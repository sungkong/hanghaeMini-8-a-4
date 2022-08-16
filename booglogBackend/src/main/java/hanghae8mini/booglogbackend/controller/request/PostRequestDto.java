package hanghae8mini.booglogbackend.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    private String title;
    private String bookTitle;
    private String author;
    private String content;
    private String imageUrl;
    private String category;
    private String nickname;

    // 테스트용
    public PostRequestDto(String category, String nickname) {
        this.title = "글제목";
        this.bookTitle = "책제목";
        this.author = "글작가";
        this.content = "글내용";
        this.imageUrl = "이미지url";
        this.category = category;
        this.nickname = nickname;
    }
}
