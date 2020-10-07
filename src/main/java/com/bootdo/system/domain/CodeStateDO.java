package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CodeStateDO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String imagestatecd;
	private String imagestatename;
	
	private Date recInsTs;
	private Date recUpdTs;
	
}
