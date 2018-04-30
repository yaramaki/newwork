package jp.co.websupport.memo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name= "\"LEND_HISTORY\"")
public class LendHistory implements IdEnitity,Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="LEND_DATE")
	private Date lendDate;


	@Temporal(TemporalType.DATE)
	@Column(name="DUE_DATE")
	private Date dueDate;


	@Temporal(TemporalType.DATE)
	@Column(name="RETURN_DATE")
	private Date returnDate;


	@Column(name="REVIEW")
	private String review;


	@Column(name="START_RATING")
	private double starRating;


	@ManyToOne
	@JoinColumn(name="MOVIE_ID",referencedColumnName="ID")
	private Movie movie;


	@ManyToOne
	@JoinColumn(name="LENDUSER_ID",referencedColumnName="ID")
	private Utbl lendUser;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getLendDate() {
		return lendDate;
	}


	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public double getStarRating() {
		return starRating;
	}


	public void setStartRating(double starRating) {
		this.starRating = starRating;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public Utbl getLendUser() {
		return lendUser;
	}


	public void setLendUser(Utbl lendUser) {
		this.lendUser = lendUser;
	}

}
