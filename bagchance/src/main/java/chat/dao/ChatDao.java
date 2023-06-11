package chat.dao;

import java.util.List;

import chat.been.ChatBean;
import chat.been.SelectChatBean;
import chat.been.UserBean;



public interface ChatDao {
//	留言時會insert物件(尚未生成聊天室)
	int insert (ChatBean chatBean);
//	修改狀態 預設為邀請中(尚未生成聊天室) 被留言者點選留言者+好友則修改狀態(生成聊天室)
	int update (ChatBean chatBean);
//	查詢全部有關聊天室
	List<SelectChatBean> selectAll (int uid);
//	假登入
	UserBean selectUser (String name);
//	找chatmaterial
	SelectChatBean selectChatmaterial (int uid, int beuid);
//	修改會員token
	int updateByToken (String mail, String token);
	
	
//	只用來在insert中當成判斷條件
	List<ChatBean> selectByRepeat (ChatBean chatBean);
//	只用來在insert中當成判斷條件
	String selectByStatus (List<ChatBean> test);
//	只用來在insert中當成判斷條件
	List<ChatBean> selectByExist (ChatBean chatBean);	
}
