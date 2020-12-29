package ec.edu.ups.ejb;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class AbstractFacade<T> {

	private Class<T> entityClass;
	
	public AbstractFacade(Class<T> entityClass) {
    	this.entityClass = entityClass;
    }
	
	protected abstract EntityManager getEntityManager();
	
	public void create(T entity) {
		getEntityManager().persist(entity);
    }
	
	public T read(Object id) {
		return getEntityManager().find(entityClass, id);
    }

    public void update(T entity) {
		getEntityManager().merge(entity);
    }

    public void delete(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> findAll() {
    	javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    	cq.select(cq.from(entityClass));
    	return getEntityManager().createQuery(cq).getResultList();
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public int count() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    	javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
    	cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
    	return ((Long) q.getSingleResult()).intValue();
    }
}
