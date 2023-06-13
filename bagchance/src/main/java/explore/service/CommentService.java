package explore.service;

import java.util.List;

import explore.bean.Comment;



public interface CommentService {
	boolean insert(Comment comment);
	List<Comment> findCommentByID(Integer id);
	List<Comment> findComments();
}
