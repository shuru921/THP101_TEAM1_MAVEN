package chat.been;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class UserBean {
	 	private int id;
	 	private String mail;
	 	private String phone;
	 	private String password;
	 	private String nickname;
	 	private String gender;
	 	private Date birthday;
	    private String exploreArea;
	    private byte[] profilePic;
	    private String profileIntro;
	    private String userStatus;
	    private Timestamp createDate;
	    private Timestamp lastUpdateDate;
	    private String tokenGoogle;
	    private String tokenFacebook;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		public String getExploreArea() {
			return exploreArea;
		}
		public void setExploreArea(String exploreArea) {
			this.exploreArea = exploreArea;
		}
		public byte[] getProfilePic() {
			return profilePic;
		}
		public void setProfilePic(byte[] profilePic) {
			this.profilePic = profilePic;
		}
		public String getProfileIntro() {
			return profileIntro;
		}
		public void setProfileIntro(String profileIntro) {
			this.profileIntro = profileIntro;
		}
		public String getUserStatus() {
			return userStatus;
		}
		public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
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
		public String getTokenGoogle() {
			return tokenGoogle;
		}
		public void setTokenGoogle(String tokenGoogle) {
			this.tokenGoogle = tokenGoogle;
		}
		public String getTokenFacebook() {
			return tokenFacebook;
		}
		public void setTokenFacebook(String tokenFacebook) {
			this.tokenFacebook = tokenFacebook;
		}
		@Override
		public String toString() {
			return "UserBean [id=" + id + ", mail=" + mail + ", phone=" + phone + ", password=" + password
					+ ", nickname=" + nickname + ", gender=" + gender + ", birthday=" + birthday + ", exploreArea="
					+ exploreArea + ", profilePic=" + Arrays.toString(profilePic) + ", profileIntro=" + profileIntro
					+ ", userStatus=" + userStatus + ", createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate
					+ ", tokenGoogle=" + tokenGoogle + ", tokenFacebook=" + tokenFacebook + "]";
		}
	    
	    
	    
}
