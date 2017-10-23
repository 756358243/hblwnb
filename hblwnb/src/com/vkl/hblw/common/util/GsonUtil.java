/**
 * 
 */
package com.vkl.hblw.common.util;

import java.sql.Timestamp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vkl.common.json.typeAdapter.TimestampTypeAdapter;

/**
 * @author xcc
 *
 */
public class GsonUtil {

	public static String toJson(Object src, String SimpleDateFormat) {
		Gson gson = new GsonBuilder().registerTypeAdapter(
				Timestamp.class,new TimestampTypeAdapter(SimpleDateFormat)).create();
		return gson.toJson(src);
	}
}
