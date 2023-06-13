package explore.service.impl;

import java.util.List;

import explore.bean.Comment;
import explore.dao.CommentDao;
import explore.dao.impl.CommentDaoImpl;
import explore.service.CommentService;



public class CommentServiceImpl implements CommentService{
	private CommentDao dao;
	
	public CommentServiceImpl(){
		dao = new CommentDaoImpl();
	}
	
	@Override
	public boolean insert(Comment comment) {
		int result = dao.insert(comment);
		return result > 0;
	}
	@Override
	public List<Comment> findCommentByID(Integer id) {
		return dao.selectByStoryid(id);
		
	}

	@Override
	public List<Comment> findComments() {
		return dao.selectStoryComments();
	}

}
