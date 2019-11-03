package com.hengyue.visual.bean;
/**
 * 地图视图实体类
 * @author 章家宝
 *
 */

import java.util.List;
import java.util.Map;

public class MapVisualBean {

	private String text;				//表头应该写的标题
	
	private String subtext;				//标题下面的小字部分
	
	private List<String> typeList;		//类型集合
	
	private Map<String, Map<String, Integer>> dataMap;			//数据键值对
	
	private Integer max;				//设置最大值，最小值为0
	
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

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

	public Map<String, Map<String, Integer>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Map<String, Integer>> dataMap) {
		this.dataMap = dataMap;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
	
	
}
