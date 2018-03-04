package jp.co.websupport.memo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import jp.co.websupport.memo.ejb.ScheduleFacade;
import jp.co.websupport.memo.entity.Schedule;


@Named(value ="memoShowAllManagedBean")
@ViewScoped
public class MemoShowAllManagedBean implements Serializable{

	 /**
     * スケジュールID
     */
    @NotNull
    private Long sid;

    /**
     * タイトル
     */
    @NotNull
    private String title;

    /**
     * 登録日
     */
    @Temporal(TemporalType.DATE)
    private Date sdate;

    /**
     * 登録時間
     */
    @Temporal(TemporalType.TIME)
    private Date stime;

    /**
     * メモ
     */
    private String memo;

    /*
     * データ一覧を取得する
     */
    private List<Schedule> scheduleList;


    /** DB処理EJB */
    @EJB
    private ScheduleFacade scheduleFacade;

  //コンストラクタ
  	public MemoShowAllManagedBean() {

  	}


	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getSdate() {
		return sdate;
	}


	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}


	public Date getStime() {
		return stime;
	}


	public void setStime(Date stime) {
		this.stime = stime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	 //データ一覧取得
    public void findList(){

        scheduleList=scheduleFacade.findAll();
    }
     /** 以下は初期表示では、viewActiionで代替するので不要
    @PostConstruct
    public void init(){
        getAllScheduleList();
    }
    */


    /**
     * スケジュール削除処理
     * @param schedule
     */
    public void deleteSchedule(Schedule schedule){
        scheduleFacade.remove(schedule);
    }


}
