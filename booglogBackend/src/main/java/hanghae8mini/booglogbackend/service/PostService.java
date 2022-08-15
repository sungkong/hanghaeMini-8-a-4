package hanghae8mini.booglogbackend.service;


import hanghae8mini.booglogbackend.domain.Category;
import hanghae8mini.booglogbackend.domain.Member;
import hanghae8mini.booglogbackend.domain.Post;
import hanghae8mini.booglogbackend.dto.response.PostResponseDto;
import hanghae8mini.booglogbackend.dto.response.ResponseDto;
import hanghae8mini.booglogbackend.dto.request.PostRequestDto;
import hanghae8mini.booglogbackend.repository.MemberRepository;
import hanghae8mini.booglogbackend.repository.PostCustomRepository;
import hanghae8mini.booglogbackend.repository.PostRepository;
import hanghae8mini.booglogbackend.util.CheckMemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CheckMemberUtil checkMemberUtil;
    private final PostCustomRepository postCustomRepository;

    @Transactional
    public ResponseDto<?> createPost(PostRequestDto requestDto, HttpServletRequest request) {

        // Member member = checkMemberUtil.validateMember(request);

        /*if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }*/

        // 테스트용
        Member member = validationMemberById(1l);

        Category categoryEnum = null;
        try{
            categoryEnum = Category.valueOf(requestDto.getCategory());
        } catch(IllegalArgumentException e) {
            return ResponseDto.fail("BAD_REQUEST", "카테고리에 없는 항목입니다.");
        }

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .bookTitle(requestDto.getBookTitle())
                .content(requestDto.getContent())
                .imageUrl(requestDto.getImageUrl())
                .member(member)
                .category(categoryEnum)
                .author(requestDto.getAuthor())
                .build();
        postRepository.save(post);
        return ResponseDto.success(true, "작성이 완료되었습니다.");
    }

    // 게시글 상세조회
    @Transactional(readOnly = true)
    public ResponseDto<?> getPost(Long postId) {

        // 테스트용
        Member member = validationMemberById(1l);

        Post post = checkMemberUtil.isPresentPost(postId);

        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }
/*
        List<Comment> commentList = commentRepository.findAllByPost(post);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        Map<Long, CommentResponseDto> map = new HashMap<>();

        commentList.stream().forEach(c -> {
                    CommentResponseDto cdto = new CommentResponseDto(c);
                    if(c.getParent() != null){
                        cdto.setParentId(c.getParent().getId());
                    }
                    map.put(cdto.getId(), cdto);
                    if (c.getParent() != null) map.get(c.getParent().getId()).getChildren().add(cdto);
                    else commentResponseDtoList.add(cdto);
                }
        );*/
        return ResponseDto.success(
                PostResponseDto.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .bookTitle(post.getBookTitle())
                        .author(post.getAuthor())
                        .nickname(post.getMember().getNickname())
                        .category(post.getCategory())
                        .content(post.getContent())
                        .imageUrl(post.getImageUrl())
                        //.commentResponseDtoList(commentResponseDtoList)
                        .createdAt(post.getCreatedAt())
                        .build()
        );
    }

    // 게시글 메인페이지 목록조회
    @Transactional(readOnly = true)
    public ResponseDto<?> getAllPost(Long lastPostId, int size) {

        // 테스트용
        Member member = validationMemberById(1l);

        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        List<Post> postList = new ArrayList<>();
        // 비로그인 로직
        if (member == null) {
            postList = postRepository.PostAllRandom(size);
        } else { // 로그인 로직
            postList = postRepository.findAllByMemberIdAndCategory(1l, lastPostId, size);
        }
        postResponseDtoList = postList.stream().map((post) -> PostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .bookTitle(post.getBookTitle())
                .author(post.getAuthor())
                .nickname(post.getMember().getNickname())
                .category(post.getCategory())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                //.commentResponseDtoList(commentResponseDtoList)
                .createdAt(post.getCreatedAt())
                .build()).collect(Collectors.toList());
        /*for (Post post : postList) {
            //  int comments = commentRepository.countAllByPost(post);
            //  int postLikes = postLikeRepository.findAllByPost(post).size();
            postResponseDtoList.add(
                    PostResponseDto.builder()
                            .postId(post.getPostId())
                            .title(post.getTitle())
                            .bookTitle(post.getBookTitle())
                            .author(post.getAuthor())
                            .nickname(post.getMember().getNickname())
                            .category(post.getCategory().getName())
                            .content(post.getContent())
                            .imageUrl(post.getImageUrl())
                            //.commentResponseDtoList(commentResponseDtoList)
                            .createdAt(post.getCreatedAt())
                            .build()
            );
        }
        */
        return ResponseDto.success(postResponseDtoList);

    }

    // 작성자의 게시글 리스트
    public ResponseDto<?> getAllPostByMember(HttpServletRequest request) {

        // 테스트용
        Member member = validationMemberById(1l);

        List<Post> postList = postRepository.findAllByMemberMemberId(member.getMemberId());
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        // List<PostListResponseDto> postListResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            //  int comments = commentRepository.countAllByPost(post);
            //  int postLikes = postLikeRepository.findAllByPost(post).size();
            postResponseDtoList.add(
                    PostResponseDto.builder()
                            .postId(post.getPostId())
                            .title(post.getTitle())
                            .bookTitle(post.getBookTitle())
                            .author(post.getAuthor())
                            .nickname(post.getMember().getNickname())
                            .category(post.getCategory())
                            .content(post.getContent())
                            .imageUrl(post.getImageUrl())
                            //.commentResponseDtoList(commentResponseDtoList)
                            .createdAt(post.getCreatedAt())
                            .build()
            );
        }

        return ResponseDto.success(postResponseDtoList);
    }

    public ResponseDto<?> updatePost(Long postId, PostRequestDto requestDto, HttpServletRequest request) {

        //member 검증 로직 필요
        // Member member = new Member(requestDto.getNickname());

        // 테스트용
        Member member = validationMemberById(1l);
        Category categoryEnum = null;

        Post post = postRepository.findByPostId(postId);

        if(!post.getMember().getMemberId().equals(member.getMemberId())){
            return ResponseDto.fail("BAD_REQUEST", "작성자만 수정할 수 있습니다.");
        }
        try{
            categoryEnum = Category.valueOf(requestDto.getCategory());
        } catch(IllegalArgumentException e) {
            return ResponseDto.fail("BAD_REQUEST", "카테고리에 없는 항목입니다.");
        }
        post = Post.builder()
                .title(requestDto.getTitle())
                .bookTitle(requestDto.getBookTitle())
                .content(requestDto.getContent())
                .imageUrl(requestDto.getImageUrl())
                .member(member)
                .category(categoryEnum)
                .author(requestDto.getAuthor())
                .build();
        postRepository.save(post);
        return ResponseDto.success(true, "수정이 완료되었습니다.");

    }


    public ResponseDto<?> deletePost(Long postId, HttpServletRequest request) {

        // member 검증 필요
        Member member = validationMemberById(1l);
        Post post = postRepository.findByPostId(postId);

        if(!post.getMember().getMemberId().equals(member.getMemberId())){
            return ResponseDto.fail("BAD_REQUEST", "작성자만 삭제할 수 있습니다.");
        }
        postRepository.deleteById(postId);
        return ResponseDto.success(true, "삭제가 완료되었습니다.");
    }

    private Member validationMemberById(Long id){
        return memberRepository.findByMemberId(1l).orElse(null);
    }
    private Member validationMemberByNickname(String nickname){
        return memberRepository.findByNickname(nickname).orElse(null);
    }
}
