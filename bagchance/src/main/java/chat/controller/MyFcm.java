package chat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import chat.been.UserBean;
import chat.service.ChatService;
import chat.service.impl.ChatServiceImpl;



@WebServlet("/fcm/*")
public class MyFcm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatService service;
	@Override
	public void init() throws ServletException {
		service = new ChatServiceImpl();
//		初始化
		try (InputStream in = getServletContext().getResourceAsStream("/firebaseServerKey.json")) {
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(in))
					.build();
			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		將前端傳的firebase token傳至db
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
		JsonObject jo =gson.fromJson(req.getReader(), JsonObject.class);
		String mail = jo.get("mail").getAsString();
		String token = jo.get("token").getAsString();
		int result = service.updateByToken(mail,token);
		JsonObject jo2 = new JsonObject();
		jo2.addProperty("token", result);
		resp.getWriter().write(jo2.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
//			把前端傳的資料以列讀入 並串接至jsonIn
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
//		轉成jsonObject 就可以取得我前端加入的屬姓
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
//		kotlin的when action是key 這邊用來區分我要幹嘛 我只要單發fcm 其他刪掉了
		switch (action) {
		case "singleFcm":
			System.out.println("============1=============="+action);
			sendSingleFcm(jsonObject);
			break;
		}
	}

	public boolean sendSingleFcm(JsonObject jsonObject) {
		System.out.println("=============2=============");
        // 取得前端傳進來的通知物件
        String title = jsonObject.get("title").getAsString();
        String body = jsonObject.get("body").getAsString();
        String mail = jsonObject.get("toMail").getAsString();

		System.out.println("==============3============"+mail);

        // 主要設定訊息標題與內容，client app一定要在背景時才會自動顯示
        Notification notification = Notification.builder()
                .setTitle(title) // 設定標題
                .setBody(body) // 設定內容
                .build();
        // 發送 notification message
        // 從DB 找到接收推播的會員
        UserBean user = service.selectUser(mail);

		System.out.println("==========4================"+user.getMail());
        Message message = Message.builder()
                .setNotification(notification) // 設定client app在背景時會自動顯示訊息
                .setToken(user.getTokenFacebook()) // 送訊息給指定token的裝置
                .build();
        try {
            // 得到該FCM推播的id，這邊沒有使用，可能可以做跟管理有關的功能
            String messageId = FirebaseMessaging.getInstance().send(message);
            return true;
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}

