package com.hengyue.visual;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.DataView;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.feature.Mark;
import com.github.abel533.echarts.feature.Restore;
import com.github.abel533.echarts.feature.SaveAsImage;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.series.Pie;
import com.google.gson.Gson;
import com.hengyue.visual.bean.PieVisualBean;
/**
 * 饼图数据可视化应用类
 * @author 章家宝
 *
 */
public class PieVisual {

	/**
	 * 单例设计模式
	 */
	private PieVisual() {}
	/**
	 * 提供外部接口
	 * @return
	 */
	public static PieVisual build() {
		return new PieVisual();
	}
	/**
	 * 获取数据option
	 */
	public static String getOption(PieVisualBean pieVisualBean) {
		Option option  = new Option();
		String result = null;
		
		Title title = new Title();
		title.setText(pieVisualBean.getText());
		title.setSubtext(pieVisualBean.getSubtext());
		title.setX(X.center);
		
		Tooltip tooltip = new Tooltip();
		tooltip.setTrigger(Trigger.item);
		tooltip.setFormatter("{a} <br/>{b} : {c} ({d}%)");
		
		Legend legend = new Legend();
		legend.setOrient(Orient.vertical);
		legend.setX(X.left);
		legend.data(pieVisualBean.getNameList());
		
		Toolbox toolbox = new Toolbox();
		toolbox.setShow(true);
		Mark mark = new Mark();
		DataView dataView = new DataView();
		dataView.setShow(true);
		dataView.setReadOnly(false);
		MagicType magicType = new MagicType();
		magicType.setShow(false);
		magicType.type(SeriesType.pie);
		com.github.abel533.echarts.feature.MagicType.Option option2 = new com.github.abel533.echarts.feature.MagicType.Option();
		Funnel funnel = new Funnel();
		funnel.setX("25%");
		funnel.setWidth("50%");
		funnel.setFunnelAlign(X.left);
		funnel.setMax(1548);
		option2.funnel(funnel);
		magicType.setOption(option2);
		Restore restore = new Restore();
		SaveAsImage saveAsImage = new SaveAsImage();
		toolbox.feature(mark, dataView, magicType, restore, saveAsImage);
		
		Pie serie = new Pie();
		serie.setName("类别名称");
		serie.setType(SeriesType.pie);
		serie.setRadius("55%");
		serie.center("50%", "60%");
		List<Data> dataList = new ArrayList<Data>();
		for(int i = 0; i < pieVisualBean.getNameList().size(); i++) {
			Data data = new Data();
			data.setName(pieVisualBean.getNameList().get(i));
			data.setValue(pieVisualBean.getDataMap().get(pieVisualBean.getNameList().get(i)));
			dataList.add(data);
		}
		serie.setData(dataList);
		
		option.setTitle(title);
		option.setToolbox(toolbox);
		option.setLegend(legend);
		option.setToolbox(toolbox);
		option.setCalculable(true);
		option.series(serie);
		Gson gson = new Gson();
		result = gson.toJson(option);
		return result;
	}
	public static void main(String[] args) {
		PieVisualBean pieVisualBean = new PieVisualBean();
		pieVisualBean.setText("测试一下");
		pieVisualBean.setSubtext("test");
		List<String> strList = new ArrayList<String>();
		strList.add("直接访问");
		strList.add("邮件营销");
		strList.add("联盟广告");
		strList.add("视频广告");
		strList.add("搜索引擎");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("直接访问", 360);
		map.put("邮件营销", 456);
		map.put("联盟广告", 657);
		map.put("视频广告", 345);
		map.put("搜索引擎", 456);
		pieVisualBean.setDataMap(map);
		pieVisualBean.setNameList(strList);
		System.out.println(getOption(pieVisualBean));
	}
}
