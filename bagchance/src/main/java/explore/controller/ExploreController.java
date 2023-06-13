package explore.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import explore.core.util.*;
import explore.core.util.CommonUtil;
import explore.service.ExploreService;
import explore.service.impl.ExploreServiceImpl;



@WebServlet("/explore/*")
public class ExploreController extends HttpServlet {
CommonUtil commonUtil = new CommonUtil();	
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new GsonBuilder()
	.setDateFormat("yyyy/MM/dd HH:mm:ss")
    .create();
	private static final ExploreService SERVICE = new ExploreServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("resp: " + resp);
		try {
			commonUtil.writeJsonBean(resp, SERVICE.selectByUid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
