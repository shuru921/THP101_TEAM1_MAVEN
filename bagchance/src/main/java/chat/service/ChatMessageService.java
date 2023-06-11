package chat.service;

import java.util.List;

import chat.been.ChatMessageBean;


public interface ChatMessageService {
	boolean send (ChatMessageBean chatMessageBean);
	List<ChatMessageBean> findAll (int chatid);
}
