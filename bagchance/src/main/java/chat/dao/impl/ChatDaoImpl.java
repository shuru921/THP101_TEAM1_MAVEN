package chat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import chat.been.ChatBean;
import chat.been.SelectChatBean;
import chat.been.UserBean;
import chat.dao.ChatDao;

;

public class ChatDaoImpl implements ChatDao {
	private DataSource dataSource;
//	建立空建構子
	public ChatDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/BagChance");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(ChatBean chatBean) {
		String sql = "insert into CHAT(INVITE_UID,BE_INVITED_UID) Value(?,?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, chatBean.getInviteUid());
			pstmt.setInt(2, chatBean.getBeInvitedUid());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(ChatBean chatBean) {
		String sql = "update CHAT set STATUS = ?, LAST_UPDATE_DATE = NOW() where ID = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, chatBean.getStatus());
			pstmt.setInt(2, chatBean.getId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<SelectChatBean> selectAll(int uid) {
		String sql = "SELECT c.ID, c.INVITE_UID, c.BE_INVITED_UID, c.STATUS, c.CREATE_DATE, c.LAST_UPDATE_DATE,\r\n"
				+ "       u1.NICKNAME AS inviteUidName, u2.NICKNAME AS beInvitedUidName,\r\n"
				+ "       u1.PROFILE_PIC AS inviteUidPic, u2.PROFILE_PIC AS beInvitedUidPic,\r\n"
				+ "       m.MESSAGE, m.PICTURE, m.RECORDING_PATH, m.SEND_UID,u1.mail as beInvitedMail, u2.mail as invitedMail\r\n"
				+ "FROM CHAT c\r\n"
				+ "INNER JOIN USER u1 ON c.INVITE_UID = u1.ID\r\n"
				+ "INNER JOIN USER u2 ON c.BE_INVITED_UID = u2.ID\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT cm1.CHAT_ID, cm1.MESSAGE, cm1.PICTURE, cm1.RECORDING_PATH, cm1.SEND_UID\r\n"
				+ "  FROM CHAT_MESSAGE cm1\r\n"
				+ "  INNER JOIN (\r\n"
				+ "    SELECT CHAT_ID, MAX(ID) AS MAX_ID\r\n"
				+ "    FROM CHAT_MESSAGE\r\n"
				+ "    GROUP BY CHAT_ID\r\n"
				+ "  ) cm2 ON cm1.CHAT_ID = cm2.CHAT_ID AND cm1.ID = cm2.MAX_ID\r\n"
				+ ") m ON c.ID = m.CHAT_ID\r\n"
				+ "WHERE c.INVITE_UID = ? OR c.BE_INVITED_UID = ?\r\n"
				+ "ORDER BY c.ID";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
				pstmt.setInt(1, uid);
				pstmt.setInt(2, uid);
				try (ResultSet rs = pstmt.executeQuery()) {
					List<SelectChatBean> list = new ArrayList<>();
					while (rs.next()) {
						SelectChatBean chatBean = new SelectChatBean();
						chatBean.setId(rs.getInt("id"));
						chatBean.setInviteUid(rs.getInt("invite_Uid"));
						chatBean.setBeInvitedUid(rs.getInt("be_Invited_Uid"));
						chatBean.setStatus(rs.getString("status"));
						chatBean.setCreateDate(rs.getTimestamp("create_Date"));
						chatBean.setLastUpdateDate(rs.getTimestamp("last_Update_Date"));
						chatBean.setInviteUidname(rs.getString("inviteUidName"));
						chatBean.setBeInvitedUidname(rs.getString("beInvitedUidname"));
						chatBean.setInviteUidpic(rs.getBytes("inviteUidPic"));
						chatBean.setBeInvitedUidpic(rs.getBytes("beInvitedUidPic"));
						chatBean.setMessage(rs.getString("MESSAGE"));
						chatBean.setPICTURE(rs.getBytes("PICTURE"));
						chatBean.setRECORDING_PATH(rs.getString("RECORDING_PATH"));
						chatBean.setSendUid(rs.getInt("SEND_UID"));
						chatBean.setBeInvitedUidMail("beInvitedMail");
						chatBean.setInvitedUidMail("invitedMail");
						list.add(chatBean);
					}
					return list;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	
	
	
	
	
	
	@Override
	public List<ChatBean> selectByRepeat(ChatBean chatBean) {
		String sql = "SELECT c1.ID, c1.INVITE_UID, c1.BE_INVITED_UID, c1.STATUS, c1.CREATE_DATE, c1.LAST_UPDATE_DATE"
				+ " FROM CHAT c1"
				+ " JOIN CHAT c2 ON c1.INVITE_UID = c2.BE_INVITED_UID AND c1.BE_INVITED_UID = c2.INVITE_UID"
				+ " WHERE ((c1.INVITE_UID = ? AND c1.BE_INVITED_UID = ?) OR (c1.INVITE_UID = ? AND c1.BE_INVITED_UID = ?))"
				+ " OR ((c2.INVITE_UID = ? AND c2.BE_INVITED_UID = ?) OR (c2.INVITE_UID = ? AND c2.BE_INVITED_UID = ?))";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
				pstmt.setInt(1, chatBean.getInviteUid());
				pstmt.setInt(2, chatBean.getBeInvitedUid());
				pstmt.setInt(3, chatBean.getBeInvitedUid());
				pstmt.setInt(4, chatBean.getInviteUid());
				pstmt.setInt(5, chatBean.getInviteUid());
				pstmt.setInt(6, chatBean.getBeInvitedUid());
				pstmt.setInt(7, chatBean.getBeInvitedUid());
				pstmt.setInt(8, chatBean.getInviteUid());
				try (ResultSet rs = pstmt.executeQuery()) {
					List<ChatBean> list = new ArrayList<>();
					while (rs.next()) {
						ChatBean chatBean2 = new ChatBean();
						chatBean2.setId(rs.getInt("id"));
						chatBean2.setInviteUid(rs.getInt("invite_Uid"));
						chatBean2.setBeInvitedUid(rs.getInt("be_Invited_Uid"));
						chatBean2.setStatus(rs.getString("status"));
						chatBean2.setCreateDate(rs.getTimestamp("create_Date"));
						chatBean2.setLastUpdateDate(rs.getTimestamp("last_Update_Date"));
						list.add(chatBean2);
					}
					return list;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public String selectByStatus(List<ChatBean> test) {
		return test.get(0).getStatus();
	}

	@Override
	public List<ChatBean> selectByExist(ChatBean chatBean) {
		String sql = "select * from CHAT where (INVITE_UID = ? AND BE_INVITED_UID = ?)or(INVITE_UID = ? AND BE_INVITED_UID = ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, chatBean.getInviteUid());
			pstmt.setInt(2, chatBean.getBeInvitedUid());
			pstmt.setInt(3, chatBean.getBeInvitedUid());
			pstmt.setInt(4, chatBean.getInviteUid());
			try (ResultSet rs = pstmt.executeQuery()) {
				List<ChatBean> list = new ArrayList<>();
				while (rs.next()) {
					ChatBean chatBean2 = new ChatBean();
					chatBean2.setId(rs.getInt("id"));
					chatBean2.setInviteUid(rs.getInt("invite_Uid"));
					chatBean2.setBeInvitedUid(rs.getInt("be_Invited_Uid"));
					chatBean2.setStatus(rs.getString("status"));
					chatBean2.setCreateDate(rs.getTimestamp("create_Date"));
					chatBean2.setLastUpdateDate(rs.getTimestamp("last_Update_Date"));
					list.add(chatBean2);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBean selectUser(String name) {
		String sql = "select * from USER where MAIL = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					UserBean userBean = new UserBean();
					userBean.setId(rs.getInt("ID"));
					userBean.setMail(name);
					userBean.setPhone(rs.getString("PHONE"));
					userBean.setPassword(rs.getString("PASSWORD"));
					userBean.setNickname(rs.getString("NICKNAME"));
					userBean.setGender(rs.getString("GENDER"));
					userBean.setBirthday(rs.getDate("BIRTHDAY"));
					userBean.setExploreArea(rs.getString("EXPLORE_AREA"));
					userBean.setProfilePic(rs.getBytes("PROFILE_PIC"));
					userBean.setProfileIntro(rs.getString("PROFILE_INTRO"));
					userBean.setUserStatus(rs.getString("USER_STATUS"));
					userBean.setCreateDate(rs.getTimestamp("CREATE_DATE"));
					userBean.setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
					userBean.setTokenGoogle(rs.getString("TOKEN_GOOGLE"));
					userBean.setTokenFacebook(rs.getString("TOKEN_FACEBOOK"));
					return userBean;
				}
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SelectChatBean selectChatmaterial(int uid, int beuid) {
		String sql = "SELECT c.ID, c.INVITE_UID, c.BE_INVITED_UID, c.STATUS, c.CREATE_DATE, c.LAST_UPDATE_DATE,\r\n"
				+ "       u1.NICKNAME AS inviteUidName, u2.NICKNAME AS beInvitedUidName,\r\n"
				+ "       u1.PROFILE_PIC AS inviteUidPic, u2.PROFILE_PIC AS beInvitedUidPic,\r\n"
				+ "       u1.MAIL AS invitedUidMail, u2.MAIL AS beInvitedUidMail,\r\n"
				+ "       m.MESSAGE, m.PICTURE, m.RECORDING_PATH, m.SEND_UID\r\n"
				+ "FROM CHAT c\r\n"
				+ "INNER JOIN USER u1 ON c.INVITE_UID = u1.ID\r\n"
				+ "INNER JOIN USER u2 ON c.BE_INVITED_UID = u2.ID\r\n"
				+ "LEFT JOIN (\r\n"
				+ "  SELECT cm1.CHAT_ID, cm1.MESSAGE, cm1.PICTURE, cm1.RECORDING_PATH, cm1.SEND_UID\r\n"
				+ "  FROM CHAT_MESSAGE cm1\r\n"
				+ "  INNER JOIN (\r\n"
				+ "    SELECT CHAT_ID, MAX(ID) AS MAX_ID\r\n"
				+ "    FROM CHAT_MESSAGE\r\n"
				+ "    GROUP BY CHAT_ID\r\n"
				+ "  ) cm2 ON cm1.CHAT_ID = cm2.CHAT_ID AND cm1.ID = cm2.MAX_ID\r\n"
				+ ") m ON c.ID = m.CHAT_ID\r\n"
				+ "WHERE (c.INVITE_UID = ? AND c.BE_INVITED_UID = ?) OR (c.INVITE_UID = ? AND c.BE_INVITED_UID = ?)\r\n"
				+ "ORDER BY c.ID";
		try (
			Connection conn = dataSource.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setInt(1, uid);
			pstmt.setInt(2, beuid);
			pstmt.setInt(3, beuid);
			pstmt.setInt(4, uid);
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					SelectChatBean chatBean = new SelectChatBean();
					chatBean.setId(rs.getInt("id"));
					chatBean.setInviteUid(rs.getInt("invite_Uid"));
					chatBean.setBeInvitedUid(rs.getInt("be_Invited_Uid"));
					chatBean.setStatus(rs.getString("status"));
					chatBean.setCreateDate(rs.getTimestamp("create_Date"));
					chatBean.setLastUpdateDate(rs.getTimestamp("last_Update_Date"));
					chatBean.setInviteUidname(rs.getString("inviteUidName"));
					chatBean.setBeInvitedUidname(rs.getString("beInvitedUidname"));
					chatBean.setInviteUidpic(rs.getBytes("inviteUidPic"));
					chatBean.setBeInvitedUidpic(rs.getBytes("beInvitedUidPic"));
					chatBean.setMessage(rs.getString("MESSAGE"));
					chatBean.setPICTURE(rs.getBytes("PICTURE"));
					chatBean.setRECORDING_PATH(rs.getString("RECORDING_PATH"));
					chatBean.setSendUid(rs.getInt("SEND_UID"));
					chatBean.setBeInvitedUidMail(rs.getString("beInvitedUidMail"));
					chatBean.setInvitedUidMail(rs.getString("invitedUidMail"));
					System.out.println(chatBean);
					return chatBean;
				}
			}	
		}catch (Exception e) {
			e.printStackTrace();
			}
		return null;
	}

	@Override
	public int updateByToken(String mail, String token) {
		String sql = "update USER set TOKEN_FACEBOOK = ?  where MAIL = ? ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, token);
			pstmt.setString(2, mail);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}

