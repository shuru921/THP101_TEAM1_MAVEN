package chat.service.impl;

import java.util.List;

import chat.been.ChatBean;
import chat.been.SelectChatBean;
import chat.been.UserBean;
import chat.dao.ChatDao;
import chat.dao.impl.ChatDaoImpl;
import chat.service.ChatService;



public class ChatServiceImpl implements ChatService {
	private ChatDao dao;
	
	public ChatServiceImpl() {
		dao = new ChatDaoImpl();
	}

	@Override
	public int insert(ChatBean chatBean) {
//		判斷是否已建立過這筆資料 在判斷狀態若為邀請中則調用update修改狀態 
		if (dao.selectByExist(chatBean).size() > 0) {
			return -1;
		}
		if (dao.selectByRepeat(chatBean).size() > 0 ) {
			return -1;
		}else if (dao.selectByRepeat(chatBean) == null) {
			return -1;
		}
		return dao.insert(chatBean);
	}

	@Override
	public int update(ChatBean chatBean) {
//		這裡單純修改狀態欄位
//		判斷是否已建立過這筆資料 建立過則修改
		if (dao.selectByRepeat(chatBean).size() > 0) {
			return -1;
		}
		return dao.update(chatBean);
	}

	@Override
	public List<SelectChatBean> selectAll(int uid) {
		return dao.selectAll(uid);
	}

	@Override
	public UserBean selectUser(String name) {
		return dao.selectUser(name);
	}

	@Override
	public SelectChatBean selectChatmaterial(int uid, int beuid) {
		return dao.selectChatmaterial(uid, beuid);
	}

	@Override
	public int updateByToken(String mail, String token) {
		return dao.updateByToken(mail,token);
	}
}

