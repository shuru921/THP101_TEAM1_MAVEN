package explore.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import explore.bean.Explore;
import explore.dao.ExploreDao;






public class ExploreDaoImpl implements ExploreDao{
	private DataSource dataSource;
	public ExploreDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/BagChance");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Explore> selectByUid() {
		
		String sql = "SELECT A.CREATE_DATE, A.CONTENT,A.ID,B.PROFILE_PIC,B.NICKNAME,B.EXPLORE_AREA,B.GENDER,B.BIRTHDAY, C.PIC  FROM STORY A \r\n"
				+ "			LEFT JOIN (SELECT * FROM USER) B on A.UID = B.ID\r\n"
				+ "				LEFT JOIN (SELECT * FROM STORY_PIC) C on A.ID = C.STORY_ID\r\n"
				+ "			ORDER BY RAND() ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Explore> list = new ArrayList<>();
				while (rs.next()) {
					Explore explore = new Explore();
					explore.setNickname(rs.getString("nickname"));
					explore.setProfile_pic(rs.getBytes("profile_pic"));
					explore.setCreate_date(rs.getTimestamp("create_date"));
					explore.setContent(rs.getString("content"));
					explore.setPic(rs.getBytes("pic"));
					explore.setId(rs.getInt("id"));
//					byte[] picBytes = rs.getBytes("pic");
//			        List<byte[]> picList = new ArrayList<>();
//			        picList.add(picBytes);
//			        explore.setPic(picList);
					explore.setExplore_area(rs.getString("explore_area"));
					explore.setGender(rs.getString("gender"));
					explore.setBirthday(rs.getDate("birthday"));
					list.add(explore);
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
//	private List<byte[]> getByteArrayList(ResultSet rs, String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
		
		
		
//		List<Hashtable<String, Object>> list = new ArrayList<Hashtable<String, Object>>();
//		Hashtable<String, Object> table1 = new Hashtable<>()
//		
//		while (rs.next()) {
//			Hashtable<K, V>;
//			table.add ("IDstory", rs.getInt("ID"))
//			....
//			list.add(table);JsonObject
//		}
	

}
