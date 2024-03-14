package com.google.drive.api.model;

public class Response {

	private int status;
	private String message;
	private String url;

	public Response() {
	}

	private Response(Builder builder) {
		this.status = builder.status;
		this.message = builder.message;
		this.url = builder.url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public String toString() {
		return "Res{" + "status=" + status + ", message='" + message + '\'' + ", url='" + url + '\'' + '}';
	}

	public static class Builder {

		private int status;
		private String message;
		private String url;

		public Builder() {
			super();
		}

		public Builder setStatus(int status) {
			this.status = status;
			return this;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Response build() {
			return new Response(this);
		}

	}

}
