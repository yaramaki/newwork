package jp.co.websupport.memo.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import jp.co.websupport.memo.converter.BooleanToIntegerConverter;



@Entity
@XmlRootElement
@Table(name = "\"MOVIE\"")
@SequenceGenerator(name="user_id_seq1",sequenceName="SEQ1")
public class Movie implements IdEnitity,Serializable{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_id_seq1")
	private long id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "OUTLINE")
	private String outline;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "IS_LENT")
	@Convert(converter=BooleanToIntegerConverter.class)
	private boolean isLent;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	List<LendHistory> lendHistories;


	//ドタッグ & ドロップで追加
	@Column(name="IMAGE")
	private String image;



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setLent(boolean isLent) {
		this.isLent = isLent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getIsLent() {
		return isLent;
	}

	public void setIsLent(boolean isLent) {
		this.isLent = isLent;
	}

	public List<LendHistory> getLendHistories() {
		return lendHistories;
	}

	public void setLendHistories(List<LendHistory> lendHistories) {
		this.lendHistories = lendHistories;
	}

	public void addLendHistory(LendHistory history) {
		if(lendHistories==null) {
			lendHistories=new ArrayList<>();

		}
		lendHistories.add(history);


	}



}
