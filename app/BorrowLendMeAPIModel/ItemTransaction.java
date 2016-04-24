package BorrowLendMeAPIModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITEM_TRANSACTION")
public class ItemTransaction extends  AbstractBaseModel {
	
	@ManyToOne
	@JoinColumn(name="LENDER_USER")
	private Users lenderUser;
	
	@ManyToOne
	@JoinColumn(name="BORROWER_USER")
	private Users borrowerUser;
	
	@ManyToOne
	@JoinColumn(name="LENDER_USER_ITEM")
	private UsersItem lendeUserItem;
	
	@Column(name="LENDING_DATE")
	private Date lendingDate;
	
	@Column(name="LENDING_RETURN_DATE")
	private Date lendingReturnDate;
	
	@Column(name="PREFERRED_PICKUP_OPTION")
	private Integer prefferedPickOption;
	
	@Column(name="MUTUAL_AGREEMENT_PLACE_ADDRESS")
	private String mutualAggreedMeetUpAddress;
	
	@Column(name="WEAR_N_TEAR_TERMS")
	private String wearAndTearTerms;
	
	@Column(name="COMMENTS/REMARKS")
	private String commentsRemarks;

	public Users getLenderUser() {
		return lenderUser;
	}

	public void setLenderUser(Users lenderUser) {
		this.lenderUser = lenderUser;
	}

	public Users getBorrowerUser() {
		return borrowerUser;
	}

	public void setBorrowerUser(Users borrowerUser) {
		this.borrowerUser = borrowerUser;
	}

	public UsersItem getLendeUserItem() {
		return lendeUserItem;
	}

	public void setLendeUserItem(UsersItem lendeUserItem) {
		this.lendeUserItem = lendeUserItem;
	}

	public Date getLendingDate() {
		return lendingDate;
	}

	public void setLendingDate(Date lendingDate) {
		this.lendingDate = lendingDate;
	}

	public Date getLendingReturnDate() {
		return lendingReturnDate;
	}

	public void setLendingReturnDate(Date lendingReturnDate) {
		this.lendingReturnDate = lendingReturnDate;
	}

	public Integer getPrefferedPickOption() {
		return prefferedPickOption;
	}

	public void setPrefferedPickOption(Integer prefferedPickOption) {
		this.prefferedPickOption = prefferedPickOption;
	}

	public String getMutualAggreedMeetUpAddress() {
		return mutualAggreedMeetUpAddress;
	}

	public void setMutualAggreedMeetUpAddress(String mutualAggreedMeetUpAddress) {
		this.mutualAggreedMeetUpAddress = mutualAggreedMeetUpAddress;
	}

	public String getWearAndTearTerms() {
		return wearAndTearTerms;
	}

	public void setWearAndTearTerms(String wearAndTearTerms) {
		this.wearAndTearTerms = wearAndTearTerms;
	}

	public String getCommentsRemarks() {
		return commentsRemarks;
	}

	public void setCommentsRemarks(String commentsRemarks) {
		this.commentsRemarks = commentsRemarks;
	}
}
