package explore.dao;

import java.util.List;

import explore.bean.Comment;


public interface CommentDao {
	int insert(Comment comment);

	List<Comment> selectByStoryid(Integer id);

	List<Comment> selectStoryComments();
}
