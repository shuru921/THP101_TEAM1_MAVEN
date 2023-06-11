package chat.service.impl;

import java.util.List;

import chat.been.ChatMessageBean;
import chat.dao.ChatMessageDao;
import chat.dao.impl.ChatMessageDaoImpl;
import chat.service.ChatMessageService;



public class ChatMessageServiceImpl implements ChatMessageService {
	private ChatMessageDao dao;
	
	public ChatMessageServiceImpl() {
		dao = new ChatMessageDaoImpl();
	}

	@Override
	public boolean send(ChatMessageBean chatMessageBean) {
		// TODO 錄音 圖片 文字商業邏輯
		return dao.insert(chatMessageBean)>= 1;
	}

	@Override
	public List<ChatMessageBean> findAll(int chatid) {
		return dao.selectAll(chatid);
	}
	
}
