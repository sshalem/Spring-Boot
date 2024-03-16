package com.google.drive.api.model;

import java.io.ByteArrayOutputStream;

public class ResponseData {

	private ByteArrayOutputStream byteArrayOutputStream;
	private String mimeType;
	private String fileName;

	public ResponseData() {
		super();
	}

	private ResponseData(ResponseDataBuilder responseDataBuilder) {
		super();
		this.byteArrayOutputStream = responseDataBuilder.byteArrayOutputStream;
		this.mimeType = responseDataBuilder.mimeType;
		this.fileName = responseDataBuilder.fileName;
	}

	public ByteArrayOutputStream getByteArrayOutputStream() {
		return byteArrayOutputStream;
	}

	public void setByteArrayOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
		this.byteArrayOutputStream = byteArrayOutputStream;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "ResponseData [byteArrayOutputStream=" + byteArrayOutputStream + ", mimeType=" + mimeType + ", fileName="
				+ fileName + "]";
	}

	public static ResponseDataBuilder builder() {
		return new ResponseDataBuilder();
	}

	/*****************
	 * Builder class
	 *****************/

	public static class ResponseDataBuilder {

		private ByteArrayOutputStream byteArrayOutputStream;
		private String mimeType;
		private String fileName;

		public ResponseDataBuilder() {
		}

		public ResponseDataBuilder setStream(ByteArrayOutputStream byteArrayOutputStream) {
			this.byteArrayOutputStream = byteArrayOutputStream;
			return this;
		}

		public ResponseDataBuilder setMimeType(String mimeType) {
			this.mimeType = mimeType;
			return this;
		}

		public ResponseDataBuilder setFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public ResponseData build() {
			return new ResponseData(this);
		}
	}
}
