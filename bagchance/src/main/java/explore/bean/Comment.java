package explore.bean;

import java.io.Serializable;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getStory_id() {
		return story_id;
	}
	public void setStory_id(Integer story_id) {
		this.story_id = story_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public byte[] getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(byte[] profile_pic) {
		this.profile_pic = profile_pic;
	}
	private String comment;
	private Integer story_id;
	private Integer id;
	private Integer uid;
	private byte[] profile_pic;
	private String nickname;

}
