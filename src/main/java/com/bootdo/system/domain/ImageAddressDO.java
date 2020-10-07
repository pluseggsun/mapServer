package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ImageAddressDO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long addressno;
	
	private String startime;
	private String endtime;

	private String googlemapmarkx;
	private String googlemapmarky;
	
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String address5;
	private String postmail;
	private String addresscontent;
	private String addressname;


	private Long imageno;
	
	private String imageresoold;
	private String imageresonew;
	private String image;
	private String imageyears;
	private String imagecontent;
	private String imagestate;
	
	
	private Date recInsTs;
	private Date recUpdTs;
	
	
	private String imageyearsname;
	private String imagestatename;
	private String cityName;
	private String townName;

	private Long registernoimage;
	private Long mapid;
	
}
