package BorrowLendMeAPIModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_ITEMS")
public class UsersItem extends AbstractBaseModel {
	
	@Column(name="ITEM_NAME")
	private String itemName;
	
	@Column(name="ITEM_DESCRIPTION")
	private String itemDescription;
	
	@Column(name="COMMENTS_REMARKS")
	private String commentsRemarks;
	
	@Column(name="DAY_RATE")
	private Double dayRate;
	
	@Column(name="DEPOSIT_AMOUNT")
	private Double depositAmount;
	
	@Column(name="ITEM_IMAGE")
	private String itemImage;
	
	@ManyToOne
	@JoinColumn(name="ITEM_LENDER_USER")
	private Users lenderUser;
	
	@Column(name="ITEM_LOCATION")
	private String itemLocation;
	
	@Column(name="ITEM_POSTCODE")
	private String itemPostCode;
	
	@Column(name="ITEM_IMAGE1")
	private String itemImage1;
	
	@Column(name="ITEM_IMAGE2")
	private String itemImage2;
	
	@Column(name="ITEM_IMAGE3")
	private String itemImage3;

	@Column(name="ITEM_CURRENCY")
	private String itemCurrency;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCurrency() {
		return itemCurrency;
	}

	public void setItemCurrency(String itemCurrency) {
		this.itemCurrency= itemCurrency;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getCommentsRemarks() {
		return commentsRemarks;
	}

	public void setCommentsRemarks(String commentsRemarks) {
		this.commentsRemarks = commentsRemarks;
	}

	public Double getDayRate() {
		return dayRate;
	}

	public void setDayRate(Double dayRate) {
		this.dayRate = dayRate;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public Users getLenderUser() {
		return lenderUser;
	}

	public void setLenderUser(Users lenderUser) {
		this.lenderUser = lenderUser;
	}

	public String getItemLocation() {
		return itemLocation;
	}

	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}

	public String getItemPostCode() {
		return itemPostCode;
	}

	public void setItemPostCode(String itemPostCode) {
		this.itemPostCode = itemPostCode;
	}

	public String getItemImage1() {
		return itemImage1;
	}

	public void setItemImage1(String itemImage1) {
		this.itemImage1 = itemImage1;
	}

	public String getItemImage2() {
		return itemImage2;
	}

	public void setItemImage2(String itemImage2) {
		this.itemImage2 = itemImage2;
	}

	public String getItemImage3() {
		return itemImage3;
	}

	public void setItemImage3(String itemImage3) {
		this.itemImage3 = itemImage3;
	}
}
