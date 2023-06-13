package explore.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import explore.bean.Comment;
import explore.dao.CommentDao;


public class CommentDaoImpl implements CommentDao{
	private DataSource dataSource;
	public CommentDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/BagChance");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public int insert(Comment comment) {
		final String sql = "INSERT INTO STORY_COMMENTS(STORY_ID, UID, COMMENT)\r\n"
				+ "VALUES (?, ?, ?)";
		try (
				Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);)
		{
				pstmt.setInt(1, comment.getStory_id());
				pstmt.setInt(2, comment.getUid());
				pstmt.setString(3, comment.getComment());
				return pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	@Override
	public List<Comment> selectByStoryid(Integer id) {
		
		String sql = "SELECT A.ID, B.COMMENT, C.NICKNAME, C.PROFILE_PIC\r\n"
				+ "FROM STORY A\r\n"
				+ "LEFT JOIN STORY_COMMENTS B ON A.ID = B.STORY_ID\r\n"
				+ "LEFT JOIN USER C ON B.UID = C.ID\r\n"
				+ "WHERE A.ID = ?";
		List<Comment> list = new ArrayList<>();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Comment comment = new Comment();
					comment.setNickname(rs.getString("nickname"));
					comment.setProfile_pic(rs.getBytes("profile_pic"));
					comment.setComment(rs.getString("comment"));
					comment.setId(rs.getInt("ID"));
					list.add(comment);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public List<Comment> selectStoryComments() {
		
		String sql = "SELECT A.ID, B.COMMENT, C.NICKNAME, C.PROFILE_PIC\r\n"
				+ "FROM STORY A\r\n"
				+ "LEFT JOIN STORY_COMMENTS B ON A.ID = B.STORY_ID\r\n"
				+ "LEFT JOIN USER C ON B.UID = C.ID\r\n"
				+ "WHERE A.ID = ?";
		List<Comment> list = new ArrayList<>();
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					Comment comment = new Comment();
					comment.setNickname(rs.getString("nickname"));
					comment.setProfile_pic(rs.getBytes("profile_pic"));
					comment.setComment(rs.getString("comment"));
					comment.setId(rs.getInt("ID"));
					list.add(comment);
				}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

}
