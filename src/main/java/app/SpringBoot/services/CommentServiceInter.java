package app.SpringBoot.services;

import app.SpringBoot.entities.Comment;

public interface CommentServiceInter {
    Comment createComment(Long userId, Long postId, Comment comment);

}
