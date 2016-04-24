/**
 *  @author Mritunjay
 */
package BorrowLendMeAPIDao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import BorrowLendMeAPIModel.BaseModel;
import BorrowLendMeAPIModel.Users;

@SuppressWarnings( { "unchecked" })
public class GenericJpaDAO implements GenericDAO {
	
	protected static Logger log = Logger.getLogger("controllers");
	 
    //method to get entity  by passing particular id of a particular entity
    public <T extends BaseModel> T getById(Class<T> clazz, Long id,EntityManager entityManager) {
    	log.info("genericDao getById{} returns entity by the primary key");
		return entityManager.find(clazz, id);
    }
    
    //method to execute simple hibernate query
    public <T extends BaseModel> List<T> executeSimpleQuery(String query,EntityManager entityManager) {
    	log.info("genericDao executeSimpleQuery{} returns list of entity by executing plain hql query on entity");
    	log.info(query);
		Query q = entityManager.createQuery(query);
		return q.getResultList();		
    }
    
	@Override
	public <T extends BaseModel> List<T> executeSimpleQueryWithLimit(
			String query, EntityManager entityManager, int limit) {
		log.info("genericDao executeSimpleQueryWithLimit{} returns list of entity by executing plain hql query on entity and limit the size");
		log.info(query);
		Query q = entityManager.createQuery(query).setMaxResults(limit);
		return q.getResultList();
	}
	
	@Override
	public <T extends BaseModel> List<T> executeSimpleQueryWithMinMaxLimit(
			String query, EntityManager entityManager, int minLimit,int maxLimit) {
		log.info("genericDao executeSimpleQueryWithMinMaxLimit{} returns list of entity by executing plain hql query on entity and limit the size from min to max");
		log.info(query);
		Query q = entityManager.createQuery(query).setFirstResult(minLimit).setMaxResults(maxLimit);
		return q.getResultList();
	}
    
    //method to save an entity in database
    public <T extends BaseModel> void save(T entity,Users usr,EntityManager entityManager) {
    	log.info("genericDao save{} performs a save operation on the entity.");
    	if (entity.getId() == null) {
    		log.info("save on entity new object"+entity);
			entity.setCreatedAt(Calendar.getInstance().getTime());
			if(usr!=null){
				log.info("save on entity new object when user is not null"+entity);
				entity.setCreatedBy(usr);
			}else{
				log.info("save on entity new object when user is null"+entity);
				entity.setCreatedBy(null);
			}
			entityManager.persist(entity);	    
		}
    }

    //method to save or update an entity in database
    public <T extends BaseModel> void saveOrUpdate(T entity,Users usr,EntityManager entityManager) {
    	log.info("genericDao saveOrUpdate{} performs a save operation on the entity if primary key of entity does not exist.");
    	log.info("if primary key exists then perform update on the entity.");
    	try{
			if (entity.getId() == null) {
				log.info("save on entity new object when user is not null"+entity);
				entity.setCreatedAt(Calendar.getInstance().getTime());
				if(usr!=null){
					entity.setCreatedBy(usr);
				}else{
					entity.setCreatedBy(null);
				}
				entityManager.persist(entity);	    
			} else {
				log.info("update on entity new object when user is not null"+entity);
				entity.setModifiedAt(Calendar.getInstance().getTime());
				if(usr!=null){
					entity.setModifiedBy(usr);
				}else{
					entity.setModifiedBy(null);
				}
				entityManager.merge(entity);
			}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}  	
    }

    //method to delete an entity object from database
    public <T extends BaseModel> void deleteById(Class<T> clazz, Long id,EntityManager entityManager) {
    	log.info("genericDao deleteById{} delete entity records by primary key.");
    	log.info("delete on"+clazz.getSimpleName());
    	entityManager.remove(getById(clazz, id,entityManager));
    }

	@Override
	public <T extends BaseModel> boolean isUnique(T entity,Map<String,Object> param,EntityManager entityManager) {
		log.info("genericDao isUnique{} check on the entire object of entity for uniqueness.");
		boolean unique=true;
			for (Entry<String, Object> entityMap : param.entrySet()){
				String key=entityMap.getKey();
				String value=entityMap.getValue().toString();
				List<T> resultentity=entityManager.createQuery("from " +entity.getClass().getSimpleName()+ " obj WHERE obj."+key+"='"+value+"'").getResultList();
				log.info(entityManager.createQuery("from " +entity.getClass().getSimpleName()+ " obj WHERE obj."+key+"='"+value+"'"));
				if(resultentity.size()>0){
					unique=false;
				}
			}
		return unique;
	}

	@Override
	public <T extends BaseModel> T getByCriteria(Class<T> clazz, Map criterias,
			EntityManager entityManager) {
		log.info("get by criteris on"+clazz.getSimpleName());
		List<T> items =  findByCriteria(clazz, criterias, null, false,entityManager);
		if (items!=null && items.size()>0)
			return items.get(0);
		return null;
	}

	@Override
	public <T extends BaseModel> List<T> findByCriteria(Class<T> clazz,
			Map criterias, EntityManager entityManager) {
		return findByCriteria(clazz, criterias, null, false,entityManager);
	}

	@Override
	public <T extends BaseModel> List<T> findByCriteria(Class<T> clazz,
			Map criterias, String orderField, boolean desc,
			EntityManager entityManager) {
				//Build the Query String with Search Criteria
				StringBuilder query = new StringBuilder("Select obj from ").append(
						clazz.getSimpleName()).append(" obj");
				if (criterias != null && criterias.size()>0 ) {
					Object[] keyArray = criterias.keySet().toArray();
					for (int i = 0; i < keyArray.length; i++) {
						if (i == 0) {
							query.append(" where");
						}
						query.append(" obj.").append(keyArray[i]).append("=:").append("p"+i);
						if (i != (keyArray.length - 1)) {
							query.append(" and");
						}
					}
				}
				if(orderField !=null){
					query.append("  ORDER BY obj.").append(orderField);
					if(!desc)
						query.append(" asc");
					else
						query.append(" desc");
						
				}
				//Build the query Object
				Query jpaQuery = entityManager.createQuery(query.toString());
				log.info(query.toString());
				//Set the search Parameters for the jpaQuery
				if (criterias != null && criterias.size()>0 ) {
					Object[] keyArray = criterias.keySet().toArray();
					for (int i = 0; i < keyArray.length; i++) 
						jpaQuery.setParameter("p"+i, criterias.get(keyArray[i]));
				}
				//Execute the query and return List of objects
				return (List<T>) jpaQuery.getResultList();
	}

	@Override
	public <T extends BaseModel> List<T> findAll(Class<T> clazz, boolean activeOnly,boolean desc,EntityManager entityManager) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder("Select obj from ").append(
				clazz.getSimpleName()).append(" obj");
		if(activeOnly){
			query.append(" where obj.presentStatus=1");
		}
		if(!desc){
			query.append(" ORDER BY obj.id asc");
		}
		if(desc){
			query.append(" ORDER BY obj.id desc");
		}
		Query jpaQuery = entityManager.createQuery(query.toString());
		log.info(query.toString());
		return (List<T>) jpaQuery.getResultList();
	}

	@Override
	public <T extends BaseModel> int deleteByCriteria(Class<T> clazz,
			Map criterias, EntityManager entityManager) {
		StringBuilder query = new StringBuilder("Delete from ").append(
				clazz.getSimpleName()).append(" obj");
		if (criterias != null && criterias.size()>0 ) {
			Object[] keyArray = criterias.keySet().toArray();
			for (int i = 0; i < keyArray.length; i++) {
				if (i == 0) {
					query.append(" where");
				}
				query.append(" obj.").append(keyArray[i]).append("=:").append("p"+i);
				if (i != (keyArray.length - 1)) {
					query.append(" and");
				}
			}
		}
		//Build the query Object
		Query jpaQuery = entityManager.createQuery(query.toString());
		log.info(query.toString());
		//Set the search Parameters for the jpaQuery
		if (criterias != null && criterias.size()>0 ) {
			Object[] keyArray = criterias.keySet().toArray();
			for (int i = 0; i < keyArray.length; i++) 
				jpaQuery.setParameter("p"+i, criterias.get(keyArray[i]));
		}
		return jpaQuery.executeUpdate();
	}


}
