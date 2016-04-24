/**
 *  @author Mritunjay
 */

package BorrowLendMeAPIDao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import BorrowLendMeAPIModel.BaseModel;
import BorrowLendMeAPIModel.Users;

@SuppressWarnings("unchecked")
public interface GenericDAO {
	
	<T extends BaseModel> List<T> findAll(Class<T> clazz, boolean activeOnly,boolean desc,EntityManager entityManager);
	
	<T extends BaseModel> T getById(Class<T> clazz, Long id,EntityManager entityManager);

    <T extends BaseModel> void save(T entity,Users usr,EntityManager entityManager);

    <T extends BaseModel> void saveOrUpdate(T entity,Users usr,EntityManager entityManager);
    
    <T extends BaseModel> void deleteById(Class<T> clazz, Long id,EntityManager entityManager);
    
    <T extends BaseModel> int deleteByCriteria(Class<T> clazz, Map criterias,EntityManager entityManager);

    <T extends BaseModel> List<T> executeSimpleQuery(String query,EntityManager entityManager);
    
    <T extends BaseModel> List<T> executeSimpleQueryWithLimit(String query,EntityManager entityManager,int limit);
    
    <T extends BaseModel> List<T> executeSimpleQueryWithMinMaxLimit(String query,EntityManager entityManager,int minLimit,int maxLimit);
    
    <T extends BaseModel> boolean isUnique(T entity,Map<String,Object> param,EntityManager entityManager);

    <T extends BaseModel> T getByCriteria(Class<T> clazz, Map criterias,EntityManager entityManager);
    
    <T extends BaseModel> List<T> findByCriteria(Class<T> clazz, Map criterias,EntityManager entityManager);
    
    <T extends BaseModel> List<T> findByCriteria(Class<T> clazz, Map criterias, String orderField, boolean desc,EntityManager entityManager);
}