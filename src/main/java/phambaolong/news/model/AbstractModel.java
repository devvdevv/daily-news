package phambaolong.news.model;

import java.sql.Timestamp;
import java.util.List;

public abstract class AbstractModel<T> {
	private Long id;
	private String createdBy;
	private Timestamp createdDate;
	private String modifiedBy;
	private Timestamp modifiedDate;
	private Long[] listId;
	private List<T> listItems;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long[] getListId() {
		return listId;
	}
	public void setListId(Long[] listId) {
		this.listId = listId;
	}
	public List<T> getListItems() {
		return listItems;
	}
	public void setListItems(List<T> listItems) {
		this.listItems = listItems;
	}
}
