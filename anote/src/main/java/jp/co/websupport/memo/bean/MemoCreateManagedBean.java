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

@Named(value = "memoCreateManagedBean")
@ViewScoped
public class MemoCreateManagedBean implements Serializable {

	 /**
     * ビジネスロジックEJB
     */
    @EJB
    private ScheduleFacade scheduleFacade;


	  /**
     * スケジュールID
     */

//    @NotNull
//    private Integer sid;

//    private Schedule schedule;
    /**
     * タイトル
     */
    @NotNull
    private String title;

    /**
     * 登録日
     */
    @Temporal(TemporalType.DATE)
    @NotNull
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

    /** 登録されている一覧をsid順に取得する */
    private List<Schedule> scheduleList;



    /**
     * Creates a new instance of MemoCreateManagedBean
     */
    public MemoCreateManagedBean() {
    }

    public String getTitle() {
        return title;
    }

    public Date getSdate() {
        return sdate;
    }

    public Date getStime() {
        return stime;
    }

    public String getMemo() {
        return memo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public void setStime(Date stime) {
        this.stime = stime;
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

    /**
     * データ一覧取得
     */
    public void getAllScheduleList(){
        scheduleList=scheduleFacade.findAll();
    }
     /**
     * Schedule情報の新規登録
     *
     */
    public void createSchecdule(){
        Schedule schedule=new Schedule();
        schedule.setTitle(title);
        schedule.setSdate(sdate);
        schedule.setStime(stime);
        schedule.setMemo(memo);
        //DBに登録
        scheduleFacade.create(schedule);
        getAllScheduleList();
    }

}
