package jp.co.websupport.memo.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import jp.co.websupport.memo.ejb.ScheduleFacade;
import jp.co.websupport.memo.entity.Schedule;

@Named
@ViewScoped
public class MemoEditManagedBean implements Serializable {
	/**
	 * 編集対象Scheduleのsid
	 */
	private Long sid;

	/**
	 * ビジネスロジック実装EJB
	 */
	@EJB
	private ScheduleFacade scheduleFacade;

	/**
	 * スケジュール情報格納ビーン
	 */
	private Schedule schedule;

	public MemoEditManagedBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Long getSid() {
		return sid;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * 前画面から渡されたsidを取得して、情報保持ビーンを取得する。
	 *
	 */
	public void edit() {
		// f:viewParamでsidを受け取らず、Flashスコープで受け渡す場合
		/*
		 * 受け渡し元は FacesContext facesContext=FacesContext.getCurrentInstance();
		 * facesContext.getExternalContext().getFlash().put("sid", sid); return
		 * "/login?faces-redirect=true";
		 */
		/*
		 * 受け渡し取得側は Flash
		 * flash=FacesContext.getCurrentInstance().getExternalContext().getFlash();
		 * String selectId=(String) flash.get("sid");
		 */
		schedule = scheduleFacade.find(sid);

	}

	// 更新処理
	public void updateSchedule() {
		scheduleFacade.edit(schedule);
	}

}
