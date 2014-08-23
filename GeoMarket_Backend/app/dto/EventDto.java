package dto;

public class EventDto {

	private static final long serialVersionUID = -6426827362089475472L;

	private String id;
	private String category;
	private Long expires;
	private String companyName;
	private String eventType;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getExpires() {
		return expires;
	}

	public void setExpires(Long expires) {
		this.expires = expires;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
