package jp.co.websupport.memo.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jp.co.websupport.memo.entity.Schedule;

@Stateless
public class ScheduleFacade extends AbstractFacade<Schedule>{

	@PersistenceContext(unitName="anote")
	private EntityManager em;

	public ScheduleFacade() {
		super(Schedule.class);

	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

}
