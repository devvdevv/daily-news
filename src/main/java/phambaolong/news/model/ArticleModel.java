package phambaolong.news.model;

public class ArticleModel extends AbstractModel {
	private String title;
	private String thumbnail;
	private String shortDescription;
	private String content;
	private Long catogoryId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCatogoryId() {
		return catogoryId;
	}
	public void setCatogoryId(Long catogoryId) {
		this.catogoryId = catogoryId;
	}
	
}
