package chat.been;

import java.sql.Timestamp;

public class SelectChatBean {
	private Integer id;
	private Integer inviteUid;
	private Integer beInvitedUid;
	private String status;
	private Timestamp createDate;
	private Timestamp lastUpdateDate;
	private String inviteUidname;
	private String beInvitedUidname;
	private byte[] inviteUidpic;
	private byte[] beInvitedUidpic;
	private String message;
	private byte[] PICTURE;
	private String RECORDING_PATH;
	private Integer sendUid;
	private String beInvitedUidMail;
	private String invitedUidMail;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInviteUid() {
		return inviteUid;
	}
	public void setInviteUid(Integer inviteUid) {
		this.inviteUid = inviteUid;
	}
	public Integer getBeInvitedUid() {
		return beInvitedUid;
	}
	public void setBeInvitedUid(Integer beInvitedUid) {
		this.beInvitedUid = beInvitedUid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getInviteUidname() {
		return inviteUidname;
	}
	public void setInviteUidname(String inviteUidname) {
		this.inviteUidname = inviteUidname;
	}
	public String getBeInvitedUidname() {
		return beInvitedUidname;
	}
	public void setBeInvitedUidname(String beInvitedUidname) {
		this.beInvitedUidname = beInvitedUidname;
	}
	public byte[] getInviteUidpic() {
		return inviteUidpic;
	}
	public void setInviteUidpic(byte[] inviteUidpic) {
		this.inviteUidpic = inviteUidpic;
	}
	public byte[] getBeInvitedUidpic() {
		return beInvitedUidpic;
	}
	public void setBeInvitedUidpic(byte[] beInvitedUidpic) {
		this.beInvitedUidpic = beInvitedUidpic;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public byte[] getPICTURE() {
		return PICTURE;
	}
	public void setPICTURE(byte[] pICTURE) {
		PICTURE = pICTURE;
	}
	public String getRECORDING_PATH() {
		return RECORDING_PATH;
	}
	public void setRECORDING_PATH(String rECORDING_PATH) {
		RECORDING_PATH = rECORDING_PATH;
	}
	public Integer getSendUid() {
		return sendUid;
	}
	public void setSendUid(Integer sendUid) {
		this.sendUid = sendUid;
	}
	public String getBeInvitedUidMail() {
		return beInvitedUidMail;
	}
	public void setBeInvitedUidMail(String beInvitedUidMail) {
		this.beInvitedUidMail = beInvitedUidMail;
	}
	public String getInvitedUidMail() {
		return invitedUidMail;
	}
	public void setInvitedUidMail(String invitedUidMail) {
		this.invitedUidMail = invitedUidMail;
	}

	

}
