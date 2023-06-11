package chat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import chat.been.ChatMessageBean;
import chat.service.ChatMessageService;
import chat.service.impl.ChatMessageServiceImpl;


@WebServlet("/web/ChatMessageController/*")
public class ChatMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatMessageService service;
	
	@Override
	public void init() throws ServletException {
		service = new ChatMessageServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy/MM/dd HH:mm:ss")
				.create();
		ChatMessageBean cmb = gson.fromJson(req.getReader(), ChatMessageBean.class);
//		要寫基本邏輯判斷
		boolean result = service.send(cmb);
		JsonObject jo = new JsonObject();
		jo.addProperty("successful", result);
//		這裡的tostring被覆寫過 會轉成Gson
		resp.getWriter().write(jo.toString());
		
//		如果不是用JsonObject和JsonArray等google建立好的方法 要用gson.toJson(jo)自己轉成gson
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doget條件寫在網址後 但會包含/ 要拿掉
		String pathInfo = req.getPathInfo();
//		取/後面包含/
		if (pathInfo == null || pathInfo.equals("/")) {
			// 如果是空值或只有/要幹嘛
		} else {
			pathInfo = pathInfo.substring(1);
//			去除第一個字元 (/)
			Integer chatId = Integer.parseInt(pathInfo);
//			java的toint()
			List<ChatMessageBean> list = service.findAll(chatId);
			resp.getWriter().write(new Gson().toJson(list));
		}
	}
}
