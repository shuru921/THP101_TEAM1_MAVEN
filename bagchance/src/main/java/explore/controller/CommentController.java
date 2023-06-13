package explore.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import explore.bean.Comment;
import explore.core.util.CommonUtil;
import explore.service.CommentService;
import explore.service.impl.CommentServiceImpl;




@WebServlet("/comment/*")
public class CommentController extends HttpServlet{
	CommonUtil commonUtil = new CommonUtil();	
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new GsonBuilder()
	.setDateFormat("yyyy/MM/dd HH:mm:ss")
    .create();
	private static final CommentService SERVICE = new CommentServiceImpl();
	private static final Integer Integer = null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
            Comment comment = gson.fromJson(req.getReader(), Comment.class);
            boolean result = SERVICE.insert(comment);
            JsonObject respBody = new JsonObject();
            respBody.addProperty("successful", result);
            String message = "留言" + (result ? "成功":"失敗");
            respBody.addProperty(message, result);
            resp.getWriter().write(respBody.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("resp: " + resp);
		try {
			String pathInfo = req.getPathInfo();
	        pathInfo = pathInfo.substring(1);
	        String[] pathVariables = pathInfo.split("/");
	        Integer id = Integer.parseInt(pathVariables[0]);
	        commonUtil.writeJsonBean(resp, SERVICE.findCommentByID(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
			
			
		

}
