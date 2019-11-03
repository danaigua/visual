package com.hengyue.visual.bean;
/**
 * 圆饼图的实体类
 * @author 章家宝
 *
 */

import java.util.List;
import java.util.Map;


public class PieVisualBean {
	
	private String text;
	
	private String subtext;

	private List<String> nameList;//名称数组集合
	
	private Map<String, Object> dataMap;//名称对应的值
	
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubtext() {
		return subtext;
	}

	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
}
