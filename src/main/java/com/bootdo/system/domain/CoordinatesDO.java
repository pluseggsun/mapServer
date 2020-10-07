package com.bootdo.system.domain;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CoordinatesDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String xcoordinates;
	
	private String ycoordinates;
	
	private String localone;
	
	private String localtwo;
	
	private String address;
	
	private String tohtml;
	
	private String image;
	
	private String addressremark;
	
	private String photooldname;
	
	private String photonewname;
	
	private String photooldsize;
	
	private String photonewsize;
	
	private String telnumber;
	
	private String photoyears;
	
	private String photoremark;
	
	private String status;
	
	private Date creatdate;
	
	private Date updatedate;
	
}
