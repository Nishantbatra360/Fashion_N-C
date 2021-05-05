package com.entity;

public class Banner {
	private int id;
	private String type;
	private String path;
	private String heading;
	private String description;
	private String href;
	
	public Banner(int id,String type,String path,String heading,String description,String href) {
		this.id=id;
		this.type=type;
		this.path=path;
		this.heading=heading;
		this.description=description;
		this.href=href;
		
		if(heading==null) {
			this.heading="";
		}
		if(description==null) {
			this.description="";
		}
	}
	
	public int getID() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getPath() {
		return path;
	}
	public String getHeading() {
		return heading;
	}
	public String getDescription() {
		return description;
	}
	public String getHref() {
		return href;
	}
}
