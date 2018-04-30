package jp.co.websupport.memo.entity.view;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import jp.co.websupport.memo.entity.Utbl;
//eager 属性が true であれば、JSF は起動時に管理対象 Bean を作成してアプリケーション・スコープに配置します
@ManagedBean(name="sessionInfo",eager=true)
@ApplicationScoped
public class SessionInfo implements Serializable{


	private Utbl loginUser;

	//private String first;


	public String getFirst() {

		return "aristo";
	}

//	public void setFirst(String first) {
//		this.first = first;
//	}

	public Utbl getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Utbl loginUser) {
		this.loginUser = loginUser;
	}


	public String logout() {
		ExternalContext externalContext=FacesContext.getCurrentInstance().getExternalContext();
		externalContext.invalidateSession();//sessionの破棄

		HttpServletRequest request=(HttpServletRequest) externalContext.getRequest();

		try {
			request.logout();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return "/index.xhtml?faces-redirect=true";
	}


	public String getTheme() {
		if(loginUser==null || (loginUser.getTheme()!=null && loginUser.getTheme().length()==0)) {

			return "aristo";
		}
		return loginUser.getTheme();
	}
}
