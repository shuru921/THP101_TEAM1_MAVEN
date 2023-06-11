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

import chat.been.ChatBean;
import chat.been.SelectChatBean;
import chat.been.UserBean;
import chat.service.ChatService;
import chat.service.impl.ChatServiceImpl;



@WebServlet("/web/ChatController/*")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatService service;

	@Override
	public void init() throws ServletException {
		service = new ChatServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
		ChatBean cb = gson.fromJson(req.getReader(), ChatBean.class);

		int result = service.insert(cb);
		JsonObject jo = new JsonObject();
		jo.addProperty("insert", result);
		resp.getWriter().write(jo.toString());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
		ChatBean cb = gson.fromJson(req.getReader(), ChatBean.class);
		int result = service.update(cb);
		JsonObject jo = new JsonObject();
		jo.addProperty("updte", result);
		resp.getWriter().write(jo.toString());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
		} else {
			pathInfo = pathInfo.substring(1);
			Integer uid = Integer.parseInt(pathInfo);
			List<SelectChatBean> list = service.selectAll(uid);
			resp.getWriter().write(new Gson().toJson(list));
		}
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
		} else {
			pathInfo = pathInfo.substring(1);
			UserBean list = service.selectUser(pathInfo);
			resp.getWriter().write(new Gson().toJson(list));
		}
	}
		//		Gson gson = new Gson();
//		String ub = gson.fromJson(req.getReader(), String.class);
//		UserBean result = service.selectUser(ub);
//		resp.getWriter().write(result.toString());
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 這是搜尋
		String pathInfo = req.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
		} else {
			pathInfo = pathInfo.substring(1);
//			String test =  pathInfo.replaceAll("/", "");
			String[] str = pathInfo.split("/");
			SelectChatBean scb = service.selectChatmaterial(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			resp.getWriter().write(new Gson().toJson(scb));
		}
	}
}
