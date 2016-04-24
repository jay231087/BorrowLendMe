package controllers;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import BorrowLendMeAPIModel.Users;
import BorrowLendMeAPIUtil.EmailUtil;
import BorrowLendMeAPIUtil.PasswordUtil;
import BorrowLendMeAPIUtil.StaticUtil;
import views.html.accountAccess;

public class LoginController extends Controller {
	
	@Transactional(readOnly=true)
	public static Result signUp(){
		EntityManager entityManager = StaticUtil.getEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		ObjectNode results = Json.newObject();
		try{
			transaction.begin();
			JsonNode json =request().body().asJson();
			ArrayNode an = results.putArray("signupresultdata");
			String email = json.findValue("email").asText();
			Map criterias = new HashMap();
			criterias.clear();
			criterias.put("email", email);
			Users user=StaticUtil.genericDAO.getByCriteria(Users.class, criterias, entityManager);
			if(user!=null){
				ObjectNode row = Json.newObject();
				row.put("message", "User Exist.");
				an.add(row);
			}else{
				String firstname = json.findValue("firstName").asText();
				String lastname = json.findValue("lastName").asText();
				String location = json.findValue("location").asText();
				String password = json.findValue("password").asText();
				user=new Users();
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setEmail(email);
				user.setLocation(location);
				user.setPassword(PasswordUtil.encrypt(password));
				StaticUtil.genericDAO.saveOrUpdate(user, user, entityManager);
				transaction.commit();
				ObjectNode row = Json.newObject();
				row.put("message", "Successfully Signed Up.");
				an.add(row);
				EmailUtil emailUtil = EmailUtil.getEmailUtil();
				String body = accountAccess.render(email, password).body();
				emailUtil.sendEmail(email, null, null, "User Account Details", body);
			}			
		}catch(Exception ex){
			if(transaction.isActive()){
				transaction.rollback();
			}
			ex.printStackTrace();
			StaticUtil.log.info(ex.getMessage());
		}
		return ok(results);
	}
	
	@Transactional(readOnly=true)
	public static Result signIn(){
		EntityManager entityManager = StaticUtil.getEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		ObjectNode results = Json.newObject();
		try{
			transaction.begin();
			JsonNode json =request().body().asJson();
			ArrayNode an = results.putArray("signinresultdata");
			String useremail = json.findValue("userEmail").asText();
			String userpassword = json.findValue("userPassword").asText();
			Map criterias = new HashMap();
			criterias.clear();
			criterias.put("email", useremail);
			criterias.put("password", PasswordUtil.encrypt(userpassword));
			Users user=StaticUtil.genericDAO.getByCriteria(Users.class, criterias, entityManager);
			if(user==null){
				ObjectNode row = Json.newObject();
				row.put("message", "User Doesn't Exist,Please Provide Correct Registered Email And Password.");
				an.add(row);
			}else{
				ObjectNode row = Json.newObject();
				row.put("message", "SignIn Successfull.");
				an.add(row);
			}
			transaction.commit();
		}catch(Exception ex){
			if(transaction.isActive()){
				transaction.rollback();
			}
			ex.printStackTrace();
			StaticUtil.log.info(ex.getMessage());
		}
		return ok(results);
	}
	
	@Transactional(readOnly=true)
	public static Result userExistence(){
		EntityManager entityManager = StaticUtil.getEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		ObjectNode results = Json.newObject();
		try{
			transaction.begin();
			JsonNode json =request().body().asJson();
			ArrayNode an = results.putArray("duplicateuseremail");
			String useremail = json.findValue("userEmail").asText();
			Map criterias = new HashMap();
			criterias.clear();
			criterias.put("email", useremail);
			Users user=StaticUtil.genericDAO.getByCriteria(Users.class, criterias, entityManager);
			if(user==null){
				ObjectNode row = Json.newObject();
				row.put("message", "Useremail Doesn't Exist.");
				an.add(row);
			}else{
				ObjectNode row = Json.newObject();
				row.put("message", "Useremail Exists.");
				an.add(row);
			}
			transaction.commit();
		}catch(Exception ex){
			if(transaction.isActive()){
				transaction.rollback();
			}
			ex.printStackTrace();
			StaticUtil.log.info(ex.getMessage());
		}
		return ok(results);
	}
}
