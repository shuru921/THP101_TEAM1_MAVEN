package chat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import chat.been.ChatMessageBean;
import chat.dao.ChatMessageDao;



public class ChatMessageDaoImpl implements ChatMessageDao {
	private DataSource dataSource;

	public ChatMessageDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/BagChance");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(ChatMessageBean chatMessageBean) {
		String sql = "insert into CHAT_MESSAGE(CHAT_ID,SEND_UID,MESSAGE,PICTURE,RECORDING_PATH,MESSAGE_STATUS) values(?,?,?,?,?,?);";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, chatMessageBean.getChatId());
			pstmt.setInt(2, chatMessageBean.getSendUid());
			pstmt.setString(3, chatMessageBean.getMessage());
			pstmt.setBytes(4, chatMessageBean.getPicture());
			pstmt.setString(5, chatMessageBean.getRecordingPath());
			pstmt.setString(6, chatMessageBean.getMessageStatus());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<ChatMessageBean> selectAll(int chatid) {
		String sql = "select * from CHAT_MESSAGE where CHAT_ID = ?";
		try (Connection conn = dataSource.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setInt(1, chatid);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<ChatMessageBean> list = new ArrayList<>();
				while (rs.next()) {
					ChatMessageBean chatMessageBean = new ChatMessageBean();
					chatMessageBean.setId(rs.getInt("id"));
					chatMessageBean.setChatId(rs.getInt("chat_Id"));
					chatMessageBean.setSendUid(rs.getInt("send_Uid"));
					chatMessageBean.setMessage(rs.getString("message"));
					chatMessageBean.setPicture(rs.getBytes("picture"));
					chatMessageBean.setRecordingPath(rs.getString("recording_Path"));
					chatMessageBean.setMessageStatus(rs.getString("message_Status"));
					chatMessageBean.setCreateDate(rs.getTimestamp("create_Date"));
					list.add(chatMessageBean);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
