package controllers;

import java.io.File;

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
import BorrowLendMeAPIModel.UsersItem;
import BorrowLendMeAPIUtil.StaticUtil;

public class ItemController extends Controller{
	
	@Transactional(readOnly=true)
	public static Result postUserItem(){
		EntityManager entityManager = StaticUtil.getEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		ObjectNode results = Json.newObject();
		Users user=null;
		try{
			transaction.begin();	
			JsonNode json =request().body().asJson();
			ArrayNode an = results.putArray("postitemresultdata");
			String userEmail = json.findValue("userEmail")!=null?json.findValue("userEmail").asText():null;
			String itemName = json.findValue("itemName")!=null?json.findValue("itemName").asText():null;
			String itemDescription = json.findValue("itemDescription")!=null?json.findValue("itemDescription").asText():null;
			String itemCommentsNRemarks = json.findValue("itemCommentsNRemarks")!=null?json.findValue("itemCommentsNRemarks").asText():null;
			String itemDayRate = json.findValue("itemDayRate")!=null?json.findValue("itemDayRate").asText():null;
			String itemDepositAmount = json.findValue("itemDepositAmount")!=null?json.findValue("itemDepositAmount").asText():null;
			String itemCurrency = json.findValue("itemCurrency")!=null?json.findValue("itemCurrency").asText():null;
			String itemLocation = json.findValue("itemLocation")!=null?json.findValue("itemLocation").asText():null;
			String itemPostCode = json.findValue("itemPostCode")!=null?json.findValue("itemPostCode").asText():null;
			String filePath = json.findValue("filePath")!=null?json.findValue("filePath").asText():null;
			String filePath1 = json.findValue("filePath1")!=null?json.findValue("filePath1").asText():null;
			String filePath2 = json.findValue("filePath2")!=null?json.findValue("filePath2").asText():null;
			String filePath3 = json.findValue("filePath3")!=null?json.findValue("filePath3").asText():null;
			if(userEmail!=null && !userEmail.equals("")){
				user = StaticUtil.getUserInfo(userEmail);
			}
			if(user!=null){
				if(itemName!=null && !itemName.equals("") && itemDescription!=null && !itemDescription.equals("")){
					UsersItem userItem=new UsersItem();
					if(itemName!=null && !itemName.equals("")){
						userItem.setItemName(itemName);
					}
					if(itemDescription!=null && !itemDescription.equals("")){
						userItem.setItemDescription(itemDescription);
					}
					if(itemCommentsNRemarks!=null && !itemCommentsNRemarks.equals("")){
						userItem.setCommentsRemarks(itemCommentsNRemarks);
					}
					if(itemDayRate!=null && !itemDayRate.equals("")){
						userItem.setDayRate(Double.parseDouble(itemDayRate));
					}
					if(itemDepositAmount!=null && !itemDepositAmount.equals("")){
						userItem.setDepositAmount(Double.parseDouble(itemDepositAmount));
					}
					if(itemCurrency!=null && !itemCurrency.equals("")){
						userItem.setItemCurrency(itemCurrency);
					}
					if(itemLocation!=null && !itemLocation.equals("")){
						userItem.setItemLocation(itemLocation);
					}
					if(itemPostCode!=null &&  !itemPostCode.equals("")){
						userItem.setItemPostCode(itemPostCode);
					}
					if(filePath!=null && !filePath.equals("")){
						userItem.setItemImage(filePath);
					}
					if(filePath1!=null && !filePath1.equals("")){
						userItem.setItemImage1(filePath1);
					}
					if(filePath2!=null && !filePath2.equals("")){
						userItem.setItemImage2(filePath2);
					}
					if(filePath3!=null && !filePath3.equals("")){
						userItem.setItemImage3(filePath3);
					}
					StaticUtil.genericDAO.saveOrUpdate(userItem, user, entityManager);
					transaction.commit();	
					ObjectNode row = Json.newObject();
					row.put("message", "You have successfully posted your Item.You will start getting requests from lenders soon.");
					an.add(row);					
				}else{
					ObjectNode row = Json.newObject();
					row.put("message", "Please Fill Item Name And Describe Your Item.");
					an.add(row);
				}
			}else{
				ObjectNode row = Json.newObject();
				row.put("message", "User Doesn't Exist.");
				an.add(row);
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
}
