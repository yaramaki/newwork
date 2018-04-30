package jp.co.websupport.memo.entity.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

import jp.co.websupport.memo.ejb.UserManager;
import jp.co.websupport.memo.entity.IdEntityListDataModel;
import jp.co.websupport.memo.entity.Utbl;

/**
 *ユーザー編集管理ビーン
 * @author aramaki
 *
 */
@Named(value="editUserView")
@ViewScoped
public class EditUserView implements Serializable{
	@EJB
	UserManager userManager;

	private String account;

	private String name;

	private String password;

	private String email;

	private boolean boolAdmin;

	private List<Utbl> users;

	/*
	 *
				チェックボックス selectionMode="multiple" でチェックボックスのカラムができる
				p:datatableタグに、value="#{＜ManagedBean名＞.＜後ほど説明するSelectableListDataModelのフィールド名＞}"
				selection="#{＜ManagedBean名＞.＜選択中のリストのメンバ＞}"、
				rowKey="#{＜データテーブルの可変変数＞.＜IDとなるフィールド名＞"
				を記述することで選択可能になります。
				p:columnタグの selectionMode="multiple" とすることで
				チェックボックスのカラムができます。
				抜粋)http://tshix.hatenablog.com/entry/2015/07/28/004921

	 *
	 *
	 *
	 */

	private String newPassword;



	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}



	public IdEntityListDataModel<Utbl> getIduserListDataModel() {
		return iduserListDataModel;
	}
	public void setIduserListDataModel(IdEntityListDataModel<Utbl> iduserListDataModel) {
		this.iduserListDataModel = iduserListDataModel;
	}





	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	private IdEntityListDataModel<Utbl> iduserListDataModel;

	private List<Utbl> selectedUsers;



	private boolean isSelected;
	public boolean getIsSelected() {
		return isSelected;
	}
	public void handleSelect(SelectEvent event) {
		isSelected=(getSelectedUsers().size()>0);
	}
	public void handleUnselect(UnselectEvent event) {
		isSelected=(getSelectedUsers().size()>0);
	}
	public void handleToggleSelect(ToggleSelectEvent event) {
		isSelected=(getSelectedUsers().size()>0);
	}





	public IdEntityListDataModel<Utbl> getUserListDataModel() {
		return iduserListDataModel;
	}
	public void setUserListDataModel(IdEntityListDataModel<Utbl> iduserListDataModel) {
		this.iduserListDataModel= iduserListDataModel;
	}
	public List<Utbl> getSelectedUsers() {
		return selectedUsers;
	}
	public void setSelectedUsers(List<Utbl> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	public void setBoolAdmin(boolean boolAdmin) {

		this.boolAdmin=boolAdmin;
	}


	public List<Utbl> getUsers() {
		return users;
	}
	public void setUsers(List<Utbl> users) {
		this.users = users;
	}



	@PostConstruct
	public void init(){
		users=userManager.findAll();
		iduserListDataModel=new IdEntityListDataModel<>(users);
	}
	public String addUser(){
		if(account==null || name==null){
			return null;
		}
		Utbl user=userManager.createUser(account, name);
		user.setPassword(password);
		user.setEmail(email);
		user.setBoolAdmin(boolAdmin);

		user=userManager.updateUser(user);//修正必要 返したuserは使用されない
		users=userManager.findAll();

		iduserListDataModel=new IdEntityListDataModel<>(users);



		return "success";
	}
	public String removeUser(){

		if(selectedUsers==null || selectedUsers.isEmpty()){
			return "success";
		}
		userManager.removeUser(selectedUsers);
		users.removeAll(selectedUsers);

		iduserListDataModel.setWrappedData(users);

		ViewUtil.addMessage("ユーザ削除", selectedUsers.size()+"件のユーザを削除しました");

		isSelected=false;

		return "success";
	}

	public String updatePassword() {
		if(selectedUsers==null || selectedUsers.isEmpty()){
			return "success";
		}
		Utbl user=selectedUsers.get(0);
		if(newPassword.equals(user.getPassword())){
			//同じパスワードなら更新しない
			return "success";
		}
		user.setPassword(newPassword);
		userManager.updateUser(user);
		ViewUtil.addMessage("パスワード変更",user.getName()+"のパスワードを変更しました。");
		return "success";
	}
	//行編集イベントハンドラ
	public void onRowEdit(RowEditEvent editEvent){
		Utbl user=(Utbl) editEvent.getObject();
		userManager.updateUser(user);
		ViewUtil.addMessage("ユーザーの編集", "ユーザ "+user.getName()+"を更新しました。");
	}
	public void onRowEditCancel(RowEditEvent event) {
		ViewUtil.addMessage("ユーザの編集", "編集をキャンセルしました。");
	}

}
