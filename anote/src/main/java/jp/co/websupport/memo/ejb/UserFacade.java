package jp.co.websupport.memo.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jp.co.websupport.memo.entity.Utbl;

/**
 * Session Bean implementation class UserFacade
 */
@Stateless
public class UserFacade extends AbstractFacade<Utbl>{

	@PersistenceContext(unitName="anote")
	private EntityManager em;

	public UserFacade() {
		super(Utbl.class);

	}

	protected EntityManager getEntityManager() {

		return em;
	}


}
