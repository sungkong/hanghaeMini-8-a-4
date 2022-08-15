package hanghae8mini.booglogbackend.service;

import hanghae8mini.booglogbackend.domain.Post;
import hanghae8mini.booglogbackend.dto.request.PostRequestDto;
import hanghae8mini.booglogbackend.dto.response.PostResponseDto;
import hanghae8mini.booglogbackend.dto.response.ResponseDto;
import hanghae8mini.booglogbackend.repository.MemberRepository;
import hanghae8mini.booglogbackend.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PostServiceTest {

//    @InjectMocks
//    private PostService postService;
//    @Mock
//    private PostRepository postRepository;
//    @Mock
//    private MemberRepository memberRepository;

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시글 작성")
    void createPost() {

        PostRequestDto requestDto = new PostRequestDto("비문학","테스트닉네임");
        HttpServletRequest request = new MockHttpServletRequest();
        ResponseDto<?> result = postService.createPost(requestDto, request);

        assertThat(result.isResult()).isTrue();
    }

    @Test
    @DisplayName("게시글 조회")
    void getPost(){

        HttpServletRequest request = new MockHttpServletRequest();
        Long postId = 1l;
        ResponseDto<?> result = postService.getPost(postId);
        assertThat(result.isResult()).isTrue();
        System.out.println(result.getData().toString());
    }

    @Test
    @DisplayName("메인페이지 조회")
    void getAllPost(){

        // given

        //when
        List<PostResponseDto> list = (java.util.List<PostResponseDto>) postService.getAllPost(5l,3).getData();
        System.out.println(list);

        //then
        assertThat(list.size()).isEqualTo(3);
    }

}