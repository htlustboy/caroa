package com.caroa.model;

import java.util.Date;

import org.springframework.boot.orm.jpa.EntityScan;

@EntityScan
public class File {
	
	private String id;
	private String fileName;
	private String filePath;
	private long fileSize;
	private int clickRate;
	private int downloadRate;
	private String category; 
	private int isEnable; 
	private Date createDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public int getClickRate() {
		return clickRate;
	}
	public void setClickRate(int clickRate) {
		this.clickRate = clickRate;
	}
	public int getDownloadRate() {
		return downloadRate;
	}
	public void setDownloadRate(int downloadRate) {
		this.downloadRate = downloadRate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
