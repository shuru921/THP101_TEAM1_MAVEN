package chat.dao;

import java.util.List;

import chat.been.ChatMessageBean;



public interface ChatMessageDao {
	int insert(ChatMessageBean chatMessageBean) ;
	List<ChatMessageBean> selectAll (int chatid);
}
