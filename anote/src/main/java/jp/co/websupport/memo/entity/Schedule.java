package jp.co.websupport.memo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the "SCHEDULE" database table.
 *
 */
@Entity
@Table(name="\"SCHEDULE\"")
@NamedQueries({
@NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s")
, @NamedQuery(name = "Schedule.findBySid", query = "SELECT s FROM Schedule s WHERE s.sid = :sid")
, @NamedQuery(name = "Schedule.findByTitle", query = "SELECT s FROM Schedule s WHERE s.title = :title")
, @NamedQuery(name = "Schedule.findBySdate", query = "SELECT s FROM Schedule s WHERE s.sdate = :sdate")
, @NamedQuery(name = "Schedule.findByStime", query = "SELECT s FROM Schedule s WHERE s.stime = :stime")
, @NamedQuery(name = "Schedule.findByMemo", query = "SELECT s FROM Schedule s WHERE s.memo = :memo")})
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)//フィールド値は、すべてnullでない
    @NotNull
	@SequenceGenerator(name="SCHEDULE_SID_GENERATOR", sequenceName="SCHEDULE_SEQ1")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCHEDULE_SID_GENERATOR")
	private Long sid;

	@Size(max = 200)
	@Column(name = "MEMO")
	private String memo;

	@Column(name = "SDATE")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date sdate;

	@Column(name = "STIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stime;

	@Size(max = 100)
    @Column(name = "TITLE")
	private String title;

	public Schedule() {
	}

	public Long getSid() {
		return this.sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getSdate() {
		return this.sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getStime() {
		return this.stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jp.co.javaee.memo.entity.Schedule[ sid=" + sid + " ]";
    }

}