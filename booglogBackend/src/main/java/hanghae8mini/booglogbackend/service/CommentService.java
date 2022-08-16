package hanghae8mini.booglogbackend.service;

import hanghae8mini.booglogbackend.controller.request.CommentRequestDto;
import hanghae8mini.booglogbackend.controller.response.CommentResponseDto;
import hanghae8mini.booglogbackend.controller.response.ResponseDto;
import hanghae8mini.booglogbackend.domain.Comment;
import hanghae8mini.booglogbackend.domain.Member;
import hanghae8mini.booglogbackend.domain.Post;
import hanghae8mini.booglogbackend.repository.CommentRepository;
import hanghae8mini.booglogbackend.repository.MemberRepository;
import hanghae8mini.booglogbackend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final MemberRepository memberRepository;

    // 댓글 작성
    @Transactional
    public ResponseDto<?> createComment(CommentRequestDto requestDto) {  // Dto: content, nickname
        // require return commentId, content, nickname, createdAt
        // 1. check user info through HttpRequest's Two tokens.
        // 2. validate user info
//        Optional<Member> optionalMember = memberRepository.findByNickname(requestDto.getNickname());
//        Member member = optionalMember.orElseThrow(UnsupportedOperationException::new);

//        Member member = Member.builder()
//                .nickname(requestDto.getNickname())
//                .build();
        // 3. validate present post or not
        Post post = postRepository.findByPostId(requestDto.getPostId());
        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }

        Comment comment = Comment.builder()
                .nickname(requestDto.getNickname())
                .post(post)
                .content(requestDto.getContent())
                .build();
        commentRepository.save(comment);
        return ResponseDto.success(
                CommentResponseDto.builder()
                        .commentId(comment.getCommentId())
                        .nickname(comment.getNickname())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .modifiedAt(comment.getModifiedAt())
                        .build()
        );

    }

    // 댓글 수정
    @Transactional
    public ResponseDto<?> updateComment(Long id, CommentRequestDto requestDto) {
        // 1. check login status
        // 2. validate user info

        Optional<Member> optionalMember = memberRepository.findByNickname(requestDto.getNickname());
        Member member = optionalMember.orElseThrow(UnsupportedOperationException::new);
        if (member == null) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 사용자입니다.");
        }

        Member member1 = Member.builder()
                .nickname(requestDto.getNickname())
                .build();

        // 3. check post is present or not
        // 4. check comment is present or not
        // 5. check the user whether the user is the writer

        Optional<Comment> optionalComment = commentRepository.findById(id);
        Comment comment = optionalComment.orElseThrow(UnsupportedOperationException::new);
        if (null == comment) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 댓글 ID 입니다.");
        }

//        if (comment.validateMember(member1)) {
//            return ResponseDto.fail("BAD_REQUEST", "작성자만 수정할 수 있습니다.");
//        }

        comment.update(requestDto);
        return ResponseDto.success(
                CommentResponseDto.builder()
                        .commentId(comment.getCommentId())
                        .nickname(comment.getNickname())
                        .content(comment.getContent())
                        .modifiedAt(comment.getModifiedAt())
                        .build()
        );


    }

    // 댓글 존재 검증 by commentId
//    @Transactional(readOnly = true)
//    public Comment isPresentComment(Long id) {
//        Optional<Comment> optionalComment = commentRepository.findById(id);
//        return optionalComment.orElse(null);
//    }

    @Transactional
    public ResponseDto<?> deleteComment(Long id) {
        // 1. 사용자 로그인 확인
        // 2. 댓글 존재 유무 확인
        Optional<Comment> optionalComment = commentRepository.findById(id);
        Comment comment = optionalComment.orElseThrow(UnsupportedOperationException::new);
        // 3. 작성자인지 확인

        commentRepository.delete(comment);
        return ResponseDto.success(
                "sucess"
        );
    }
}
