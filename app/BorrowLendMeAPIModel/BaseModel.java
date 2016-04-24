package BorrowLendMeAPIModel;

import java.io.Serializable;
import java.util.Date;

public interface BaseModel extends Serializable {

    Long getId();

    void setId(Long id);
    
    Date getCreatedAt();
    
    void setCreatedAt(Date createdAt);
    
    Users getCreatedBy();
    
    void setCreatedBy(Users createdBy);
    
    Date getModifiedAt();
    
    void setModifiedAt(Date modifiedAt);
    
    Users getModifiedBy();
    
    void setModifiedBy(Users modifiedBy);
    
    Integer getPresentStatus();
    
    void setPresentStatus(Integer presentStatus);
}
