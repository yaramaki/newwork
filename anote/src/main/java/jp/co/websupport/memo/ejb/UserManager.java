package jp.co.websupport.memo.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import jp.co.websupport.memo.entity.LendHistory;
import jp.co.websupport.memo.entity.Utbl;
@Stateless
public class UserManager {

	@PersistenceContext(unitName="anote")
	private EntityManager entityManager;


	/**
	 * 新規ユーザー登録を行う(accont name指定）
	 * @param account 新規アカウント
	 * @param name 新規ユーザー名
	 * @return 新規登録ユーザー情報保持ビーン
	 */
	public Utbl createUser(String account,String name){
		Utbl user=new Utbl();
		user.setAccount(account);
		user.setName(name);
		entityManager.persist(user);

		return user;
	}

	/**
	 * 新規ユーザー登録
	 * @param user 新規ユーザー情報保持ビーン
	 */
	public void createUser(Utbl user){
		if(user!=null){
			entityManager.persist(user);

		}
	}

	/**
	 * ID指定でユーザー情報取得
	 * @param id
	 * @return
	 */
	public Utbl findById(long id){
		Utbl user=entityManager.find(Utbl.class, id);
		return user;
	}

	/**
	 * 全件取得
	 * @return ユーザー情報Utblのリスト
	 */
	public List<Utbl> findAll(){
		TypedQuery<Utbl> query=entityManager.createQuery("SELECT u FROM Utbl u order by u.account asc",Utbl.class);
		List<Utbl> result=query.getResultList();
		return result;
	}

	public Utbl findByAccount(String account) {
		//EntityManager entityManager = getEm();
		TypedQuery<Utbl> query = entityManager.createQuery("SELECT u FROM Utbl u where u.account=:account", Utbl.class);
		query.setParameter("account", account);
		Utbl user;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return user;
	}

	/**
	 * ログイン処理
	 * @param account
	 * @param password
	 * @return
	 */
	public Utbl login(String account,String password){
		TypedQuery<Utbl> query=entityManager.createQuery("select u from Utbl u where account=:account and  u.password = :password", Utbl.class);
		query.setParameter("account", account);
		query.setParameter("password", password);
		Utbl user=null;

		//修復ユーザーに対応し、結果を複数で取得
		List<Utbl> list=query.getResultList();
		if(list.size()>0){
			user=list.get(0);
			return user;
		}
		if ("admin".equals(account) && "admin".equals(password)) {

			Utbl admin = findByAccount(account);
			if (admin == null) {
				admin = createUser(account, password);
				admin.setName("admin");
				admin.setBoolAdmin(true);
				updateUser(admin);

			}
		}
		return user;
	}

	/**
	 * ユーザー情報更新
	 * @param user
	 * @return
	 */
	public Utbl updateUser(Utbl user) {

		if (!entityManager.contains(user)) {
			// エンティティーが永続化コンテキスト内で管理されていない場合
			//画面表示後、値を編集する場合、一度表示された時点ででタッチ状態になっているので、if分で確認している
			user=entityManager.merge(user);
		}

		return user;

	}

	/**
	 * エンティティーの削除
	 * @param selectedUsers
	 * @return
	 */
	public int removeUser(List<Utbl> selectedUsers) {
		int removeCount = 0;

		for (Utbl user : selectedUsers) {
			Utbl find = entityManager.find(Utbl.class, user.getId());
			if (find == null) {
				continue;
			}
			List<LendHistory> histories = find.getLendHistories();
			for (LendHistory history : histories) {
				history.setLendUser(null);
				entityManager.merge(history);
			}
			entityManager.remove(find);
			removeCount++;
		}
		return removeCount;
	}




}
