package jp.co.websupport.memo.entity.view;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class ViewUtil {

	public static void addMessage(String summary, String detail) {
		addMessageInner(FacesMessage.SEVERITY_INFO, summary, detail);
	}

	public static void addWarningMessage(String summary, String detail) {
		addMessageInner(FacesMessage.SEVERITY_WARN, summary, detail);
	}

	public static void addErrorMessage(String summary, String detail) {
		addMessageInner(FacesMessage.SEVERITY_ERROR, summary, detail);
	}

	public static void addFatalMessage(String summary, String detail) {
		addMessageInner(FacesMessage.SEVERITY_FATAL, summary, detail);
	}

	// Severityは厳しさ 過酷の意味
	// FacesMessageクラスにはseverityプロパティがあって、メッセージのレベルを指定
	private static void addMessageInner(Severity severity, String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static SessionInfo getSessionInfo() {
		// ELResolverでEL式を開設する
		/*
		 * Unified ELではELResolverクラスがEL式の解決を行っており、
		 * 配列、リスト、マップ、JavaBeans、リソースバンドルを取り扱うための ELResolverがそれぞれ定義されています。
		 * さらにELResolverを自身でカスタマイズすることにより、 JNDIやJDBCのリソースや独自の暗黙オブジェクトなどの、
		 * 標準仕様のELResolverではサポートされていないものについても 取り扱うことができるようになります。
		 */

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionInfo sessionInfo = (SessionInfo) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "sessionInfo");// ${sessionInfo}
		return sessionInfo;
	}

	/*
	 * Flashとは画面遷移の1度きりだけ有効になるマップです。
	 */
	public static void putToFlash(String key,Object value) {
		Flash flash=FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put(key, value);
	}
	public static Object getFromFlash(String key) {
		Flash flash=FacesContext.getCurrentInstance().getExternalContext().getFlash();

		return flash.get(key);
	}


}
