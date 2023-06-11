package chat.service;

import java.util.List;

import chat.been.ChatBean;
import chat.been.SelectChatBean;
import chat.been.UserBean;



public interface ChatService {
	int insert (ChatBean chatBean);
	int update (ChatBean chatBean);
	int updateByToken (String mail, String token);
	List<SelectChatBean> selectAll (int uid);
	UserBean selectUser (String name);
	SelectChatBean selectChatmaterial(int uid, int beuid);
}
