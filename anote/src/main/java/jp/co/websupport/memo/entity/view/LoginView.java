package jp.co.websupport.memo.entity.view;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import jp.co.websupport.memo.ejb.UserManager;
import jp.co.websupport.memo.entity.Utbl;

@Named
@ViewScoped
public class LoginView implements Serializable {

	@EJB
	UserManager userManager;

	private String account;

	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login(){

		if(userManager==null){
			ViewUtil.addErrorMessage("マネージャー null" , "Managerがnullです");
			return "failer";
		}
		Utbl loginUser=userManager.login(account, password);
		if(loginUser==null){
			ViewUtil.addErrorMessage("ログイン失敗", "ユーザーが存在しません");
			return "failer";
		}

		SessionInfo info=ViewUtil.getSessionInfo();
		info.setLoginUser(loginUser);


		return "success";
	}
}
