package jp.co.websupport.memo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import jp.co.websupport.memo.converter.BooleanToIntegerConverter;

@Entity
@XmlRootElement
@Table(name="\"UTBL\"")
@NamedQuery(name="Utbl.findAll", query="SELECT u FROM Utbl u")
@SequenceGenerator(name="user_id_seq",sequenceName="SEQ2")
public class Utbl implements IdEnitity,Serializable{

	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_id_seq")
	private long id;

	@Column(name = "ACCOUNT")
	@NotNull
	private String account;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NAME")
	@NotNull
	private String name;

	@Column(name = "E_MAIL")
	private String email;

	@Column(name = "IS_ADMIN")
	@Convert(converter=BooleanToIntegerConverter.class)
	private boolean boolAdmin;
	//private boolean isAdmin;
	//private BigDecimal isAdmin;

	//追加
	@Column(name="THEME")
	private String theme;



	@OneToMany(mappedBy = "lendUser")
	List<LendHistory> lendHistories;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passowrd) {
		this.password = passowrd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public boolean getBoolAdmin() {


		return boolAdmin;

	}

	public void setBoolAdmin(Boolean boolAdmin) {

		this.boolAdmin=boolAdmin;
	}

	public List<LendHistory> getLendHistories() {
		return lendHistories;
	}

	public void setLendHistories(List<LendHistory> lendHistories) {
		this.lendHistories = lendHistories;
	}

	public void addLendHistory(LendHistory history) {
		if (lendHistories == null) {
			lendHistories = new ArrayList<>();

		}
		lendHistories.add(history);

	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}


}
