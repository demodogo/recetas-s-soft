package com.duoc.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.recetas.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
