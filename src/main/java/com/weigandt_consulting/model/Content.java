package com.weigandt_consulting.model;

public class Content {
	private Long id;
	private String content;

	public Content(Long id, String content) {
		this.id = id;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Content))
			return false;

		Content content = (Content) o;

		if (getId() != null ? !getId().equals(content.getId()) : content.getId() != null)
			return false;
		return getContent() != null ? getContent().equals(content.getContent()) : content.getContent() == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
		return result;
	}
}
