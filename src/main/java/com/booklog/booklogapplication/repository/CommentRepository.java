package com.booklog.booklogapplication.repository;

import com.booklog.booklogapplication.domain.Comment;
import com.booklog.booklogapplication.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
