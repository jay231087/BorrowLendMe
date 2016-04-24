package BorrowLendMeAPIUtil;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import play.Play;
import play.db.jpa.JPA;
import BorrowLendMeAPIDao.GenericDAO;
import BorrowLendMeAPIDao.GenericJpaDAO;
import BorrowLendMeAPIModel.Users;

public class StaticUtil {
	public static GenericDAO genericDAO=new GenericJpaDAO();
	public static Session noreplySession=noReplySession();
	public static EntityManager entityManager=getEntityManager();
	public static Logger log = Logger.getLogger("loggers");
	public static Session noReplySession(){
		String smtpHost=Play.application().configuration().getString("smtp.host");
		String smtpPort=Play.application().configuration().getString("smtp.port");
		final String username=Play.application().configuration().getString("smtpnoreply.user");
		final String password=Play.application().configuration().getString("smtpnoreply.password");
		Properties props=new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.from",username);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		Session session=Session.getInstance(props,new Authenticator() {
			@Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
		});
		return session;	
	}
	
	public static EntityManager getEntityManager() {
		return JPA.em();
	}
	
	public static Users getUserInfo(String email){
		EntityManager entityManager = JPA.em();
		Users usr=null;
		if (email != null && !email.equals("")) {
			StringBuffer sbquery = new StringBuffer("");
			sbquery.append("select obj from Users obj WHERE obj.email ='"
					+ email + "'");
			List<Users> users = StaticUtil.genericDAO.executeSimpleQuery(
					sbquery.toString(), entityManager);
			if(users.size()>0){
				usr = users.get(0);
			}
			return usr;
		} else {
			return null;
		}
	}
}
