package com.yhy.hzzll.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONTool {

	public static String getString(JSONObject obj, String lable) {
		String result = "";
		try {
			result = obj.getString(lable);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Integer getInt(JSONObject obj, String lable) {
		Integer result = null;
		try {
			result = obj.getInt(lable);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Double getDouble(JSONObject obj, String lable) {
		Double result = null;
		try {
			result = obj.getDouble(lable);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean getBoolean(JSONObject obj, String lable) {
		boolean result = false;
		try {
			result = obj.getBoolean(lable);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static JSONArray getJsonArray(JSONObject obj, String lable) {
		JSONArray result = null;
		try {
			result = obj.getJSONArray(lable);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static JSONObject getJsonObject(JSONObject obj, String lable) {
		JSONObject result = null;
		try {
			result = obj.getJSONObject(lable);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static JSONObject getJsonObjectOfStr(String str) {
		JSONObject result = null;
		try {
			result = new JSONObject(str);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}
