package chat.been;

import java.sql.Timestamp;

public class ChatBean {
	private Integer id;
    private Integer inviteUid;
    private Integer beInvitedUid;
    private String status;
    private Timestamp createDate;
    private Timestamp lastUpdateDate;
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
	@Override
	public String toString() {
		return "ChatBean [id=" + id + ", inviteUid=" + inviteUid + ", beInvitedUid=" + beInvitedUid + ", status="
				+ status + ", createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
    
    
	
}
