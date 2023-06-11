package chat.been;

import java.sql.Timestamp;

public class ChatMessageBean {
	private Integer id;
    private Integer chatId;
    private Integer sendUid;
    private String message;
    private byte[] picture;
    private String recordingPath;
    private String messageStatus;
    private Timestamp createDate;
	public int getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	public int getChatId() {
		return chatId;
	}
	public void setChatId(Integer  chatId) {
		this.chatId = chatId;
	}
	public int getSendUid() {
		return sendUid;
	}
	public void setSendUid(Integer  sendUid) {
		this.sendUid = sendUid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getRecordingPath() {
		return recordingPath;
	}
	public void setRecordingPath(String recordingPath) {
		this.recordingPath = recordingPath;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ChatMessageBean [id=" + id + ", chatId=" + chatId + ", sendUid=" + sendUid + ", message=" + message
				+ ", picturePath=" + picture + ", recordingPath=" + recordingPath + ", messageStatus="
				+ messageStatus + ", createDate=" + createDate + "]";
	}
    
	
    
}
