package com.filesystem.model;

public class DataFile {

	private String image;
	private String name;
	private String type;
	private int size;

	public DataFile() {
		super();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "DataFile [image=" + image + ", name=" + name + ", type=" + type + ", size=" + size + "]";
	}

}
