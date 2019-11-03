package com.hengyue.visual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.abel533.echarts.DataRange;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.RoamController;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.DataView;
import com.github.abel533.echarts.feature.Mark;
import com.github.abel533.echarts.feature.Restore;
import com.github.abel533.echarts.feature.SaveAsImage;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.itemstyle.Emphasis;
import com.github.abel533.echarts.style.itemstyle.Normal;
import com.google.gson.Gson;
import com.hengyue.visual.bean.MapVisualBean;

/**
 * 地图视图可视化
 * @author 章家宝
 *
 */
public class MapVisual {

	/**
	 * 单例设计模式
	 */
	private MapVisual() {}
	/**
	 * 提供一个外部实体接口
	 * @return
	 */
	public MapVisual build() {
		return new MapVisual();
	}
	/**
	 * 获取option
	 * @return
	 */
	public static String getOption(MapVisualBean mapVisualBean) {
		String result = null;
		Option option = new Option();
		
		Title title = new Title();
		title.setText(mapVisualBean.getText());
		title.setSubtext(mapVisualBean.getSubtext());
		title.setX(X.center);
		
		Tooltip tooltip = new Tooltip();
		tooltip.setTrigger(Trigger.item);
		
		Legend legend = new Legend();
		legend.setOrient(Orient.vertical);
		legend.setX(X.left);
		legend.data(mapVisualBean.getTypeList());
		
		DataRange dataRange = new DataRange();
		dataRange.setMin(0);
		dataRange.setMax(mapVisualBean.getMax());
		dataRange.setX(X.left);
		dataRange.setY(Y.bottom);
		List<String> strList = new ArrayList<String>();
		strList.add("高");
		strList.add("低");
		dataRange.setText(strList);
		dataRange.setCalculable(true);
		
		Toolbox toolbox = new Toolbox();
		toolbox.setShow(true);
		toolbox.setOrient(Orient.vertical);
		toolbox.setX(X.right);
		Mark mark = new Mark();
		DataView dataView = new DataView();
		Restore restore = new Restore();
		SaveAsImage saveAsImage = new SaveAsImage();
		toolbox.feature(mark, dataView, restore, saveAsImage);
		
		RoamController roamController = new RoamController();
		roamController.setShow(true);
		roamController.setX(X.right);
		Map<String, Boolean> mapTypeControl = new HashMap<String, Boolean>();
		mapTypeControl.put("china", true);
		roamController.setMapTypeControl(mapTypeControl);
		
		List<Series> series = new ArrayList<Series>(); 
		for(int i = 0; i < mapVisualBean.getTypeList().size(); i++) {
			com.github.abel533.echarts.series.Map serie = new com.github.abel533.echarts.series.Map();
			serie.setName(mapVisualBean.getTypeList().get(i));
			serie.setType(SeriesType.map);
			serie.mapType("china");
			serie.setRoam(false);
			Label label = new Label();
			label.setShow(true);
			Normal normal = new Normal();
			normal.setLabel(label);
			Emphasis emphasis = new Emphasis();
			emphasis.setLabel(label);
			ItemStyle itemStyle = new ItemStyle();
			itemStyle.setNormal(normal);
			itemStyle.setEmphasis(emphasis);
			serie.setItemStyle(itemStyle);
			List<Data> dataList = new ArrayList<Data>();
			System.out.println(mapVisualBean.getTypeList().get(i));
			Map<String, Integer> map = mapVisualBean.getDataMap().get(mapVisualBean.getTypeList().get(i));
			Set<String> set = map.keySet();
			for (String string : set) {
				Data data = new Data();
				data.setName(string);
				data.setValue(map.get(string));
				dataList.add(data);
			}
			serie.setData(dataList);
			series.add(serie);
		}
		
		option.setTitle(title);
		option.setTooltip(tooltip);
		option.setLegend(legend);
		option.setDataRange(dataRange);
		option.setToolbox(toolbox);
		option.setRoamController(roamController);
		option.setSeries(series);
		Gson gson = new Gson();
		result = gson.toJson(option);
		return result;
	}
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		MapVisualBean mapVisualBean = new MapVisualBean();
		mapVisualBean.setMax(10000);
		mapVisualBean.setText("地图测试方法");
		mapVisualBean.setSubtext("测试哈");
		List<String> typeList = new ArrayList<String>();
		typeList.add("iphonexsMax");
		typeList.add("iphone11");
		typeList.add("iphone11Pro");
		mapVisualBean.setTypeList(typeList);
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		dataMap.put("北京", 5000);
		dataMap.put("重庆", 3243);
		dataMap.put("辽宁", 4323);
		dataMap.put("黑龙江", 343);
		dataMap.put("江苏", 432);
		dataMap.put("甘肃", 50);
		dataMap.put("陕西", 342);
		dataMap.put("广东", 9000);
		dataMap.put("青海", 324);
		Map<String, Map<String, Integer>> datas = new HashMap<String, Map<String,Integer>>();
		datas.put("iphonexsMax", dataMap);
		datas.put("iphone11", dataMap);
		datas.put("iphone11Pro", dataMap);
		mapVisualBean.setDataMap(datas);
		System.out.println(getOption(mapVisualBean));
	}
	
}
