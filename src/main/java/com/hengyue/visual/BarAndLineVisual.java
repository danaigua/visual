package com.hengyue.visual;

import java.util.ArrayList;
import java.util.List;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.DataView;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.feature.Mark;
import com.github.abel533.echarts.feature.Restore;
import com.github.abel533.echarts.feature.SaveAsImage;
import com.github.abel533.echarts.series.Series;
import com.google.gson.Gson;

/**
 * 折线图和柱状图的组合图
 * @author 章家宝
 *
 */
public class BarAndLineVisual {

	/**
	 * 单例设计模式
	 * 私有化构造方法
	 */
	private BarAndLineVisual() {}
	/**
	 * 提供一个外部实例化的接口
	 * @return
	 */
	public BarAndLineVisual build() {
		return new BarAndLineVisual();
	}
	/**
	 * 获取option
	 * @return
	 */
	public static String getOption() {
		String result = null;
		Option option = new Option();
		
		Tooltip tooltip = new Tooltip();
		tooltip.setTrigger(Trigger.axis);
		
		Toolbox toolbox = new Toolbox();
		toolbox.setShow(true);
		Mark mark = new Mark();
		DataView dataView = new DataView();
		MagicType magicType = new MagicType();
		magicType.setShow(true);
		List<String> typeList = new ArrayList<String>();
		typeList.add("line");
		typeList.add("bar");
		magicType.setType(typeList);
		Restore restore = new Restore();
		SaveAsImage saveAsImage = new SaveAsImage();
		toolbox.feature(mark, dataView, magicType, restore, saveAsImage);
		
		Legend legend = new Legend();
		legend.data("");
		
		Axis<CategoryAxis> xAxis = new CategoryAxis();
		xAxis.setType(AxisType.category);
		xAxis.data("");
		
		List<Axis> axisList = new ArrayList<Axis>();
		Axis<ValueAxis> yAxis = new ValueAxis();
		yAxis.setType(AxisType.value);
		yAxis.setName("");
		AxisLabel axisLabel = new AxisLabel();
		axisLabel.setFormatter("");
		yAxis.setAxisLabel(axisLabel);
		axisList.add(yAxis);
		
		List<Series> series = new ArrayList<Series>();
		
		
		option.setTooltip(tooltip);
		option.setToolbox(toolbox);
		option.setCalculable(true);
		option.xAxis(xAxis);
		option.setyAxis(axisList);
		option.setSeries(series);
		
		Gson gson = new Gson();
		result = gson.toJson(option);
		return result;
	}
	
}
