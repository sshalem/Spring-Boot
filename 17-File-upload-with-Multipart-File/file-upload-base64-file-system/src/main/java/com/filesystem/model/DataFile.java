package com.filesystem.model;

public class DataFile {

	private String image;

	public DataFile() {
		super();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "DataFile [image=" + image + "]";
	}

}
