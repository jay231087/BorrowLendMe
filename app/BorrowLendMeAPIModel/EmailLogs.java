package BorrowLendMeAPIModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EMAIL_LOGS")
public class EmailLogs extends AbstractBaseModel{
	
	@Column(name="MAIL_FROM")
	private String mailFrom;
	
	@Column(name="MAIL_TO")
	private String mailTo;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="MAIL_GOING_DATE")
	private Date mailGoingDate;

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getMailGoingDate() {
		return mailGoingDate;
	}

	public void setMailGoingDate(Date mailGoingDate) {
		this.mailGoingDate = mailGoingDate;
	}
}
