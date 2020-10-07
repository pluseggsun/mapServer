package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CodeYearsDO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	private String imageyearscd;
	private String imageyearsname;
	
	private Date recInsTs;
	private Date recUpdTs;
	

}
