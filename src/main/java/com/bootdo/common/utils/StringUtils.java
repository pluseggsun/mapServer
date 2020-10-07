package com.bootdo.common.utils;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * @author bootdo
 */
@Component
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public boolean commaDelimitedListContains(String commaDelimitedList, String value) {
		Set<String> list = org.springframework.util.StringUtils.commaDelimitedListToSet(commaDelimitedList);
		return list.contains(value);
	}
	public static String getUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
