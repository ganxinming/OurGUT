package com.ourGut.risk.executeRule;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.Script;
import org.checkerframework.checker.units.qual.K;

/**
 * @author ganxinming
 * @createDate 2021/4/29
 * @description
 */
public class Jexl {
	public static void main(String[] args) {

	String script= "var getFirstLoginMap=function(map1,map2){\n" +
				"  if(empty(map2) && !empty(map1)){\n" +
				"  return map1;\n" +
				"  }\n" +
				"  if(!empty(map2) && empty(map1)){\n" +
				"  return map2;\n" +
				"  }\n" +
				"  if(!empty(map2) && !empty(map1)){\n" +
				"    if(empty(map2.timestamp) && !empty(map1.timestamp)){\n" +
				"    return map1;\n" +
				"     }\n" +
				"    if(!empty(map2.timestamp) && empty(map1.timestamp)){\n" +
				"    return map2;\n" +
				"     }\n" +
				"    if(!empty(map2.timestamp) && !empty(map1.timestamp)){\n" +
				"      if(new(\"java.lang.Long\", map2.timestamp) < new(\"java.lang.Long\", map1.timestamp)){\n" +
				"     return map2;\n" +
				"    }\n" +
				"      else{\n" +
				"     return map1;\n" +
				"     }\n" +
				"\n" +
				"    }\n" +
				"  }\n" +
				"}\n" +
				"\n" +
				"\n" +
				"var map=getFirstLoginMap($and_first_login_map,$ios_first_login_map);\n" +
				"var currentLg=new(\"java.lang.Double\", empty(map)?(!empty($inviteeLongitude)&&$inviteeClientType=~[1,2])?$inviteeLongitude:0d\n" +
				":empty(map.currentLg)?0d:map.currentLg);\n" +
				"var currentLt=new(\"java.lang.Double\", empty(map)?(!empty($inviteeLatitude)&&$inviteeClientType=~[1,2])?$inviteeLatitude:0d\n" +
				":empty(map.currentLt)?0d:map.currentLt);\n" +
				"var ipLg=new(\"java.lang.Double\", empty(map)?(!empty($inviteeClientipLg)&&$inviteeClientType=~[1,2])?$inviteeClientipLg:0d\n" +
				":empty(map.ipLg)?0d:map.ipLg);\n" +
				"var ipLt=new(\"java.lang.Double\", empty(map)?(!empty($inviteeClientipLt)&&$inviteeClientType=~[1,2])?$inviteeClientipLt:0d\n" +
				":empty(map.ipLt)?0d:map.ipLt);\n" +
				"\n" +
				"if(currentLg>0d && currentLt >0d && ipLg>0d && ipLt>0){\n" +
				"  return  distance:getDistance(currentLg,currentLt,ipLg,ipLt)/1000;\n" +
				"  }\n" +
				"else{\n" +
				"  -1;\n" +
				"}";
		Map<String, Object> map = new HashMap<String, Object>();


		Map<String, Object> andMap= Maps.newHashMap();
		andMap.put("cityName","杭州");
		andMap.put("timestamp",1617699274171L);
		andMap.put("ip","127.0.0.1");
		andMap.put("currentLg",1.12);
		andMap.put("currentLt",1.12);
		andMap.put("cityCode",0571);
		andMap.put("ipGeo","杭州");
		andMap.put("ipLg",1.21);
		andMap.put("ipLt",1.22);
//		andMap=null;
		Map<String, Object> iosMap= Maps.newHashMap();
		iosMap.put("cityName","苏州");
		iosMap.put("timestamp",1617699274170L);
		iosMap.put("ip","227.0.0.1");
		iosMap.put("currentLg",1.12);
		iosMap.put("currentLt",1.12);
		iosMap.put("cityCode",0571);
		iosMap.put("ipGeo","苏州");
		iosMap.put("ipLg",1.21);
		iosMap.put("ipLt",1.22);
//		iosMap=null;
		map.put("$and_first_login_map",iosMap);
		map.put("$ios_first_login_map",andMap);
//		map.put("$inviteeClientType",2);
//		map.put("$inviteeLongitude",1);
//		map.put("$inviteeLatitude",1);
//		map.put("$inviteeClientipLg",2);
//		map.put("$inviteeClientipLt",2);
		Object parse = parse(script, map);
		System.out.println(parse);
	}

	public static Object parse(String re, Map<String, Object> map) {
		JexlEngine jexlEngine = new JexlEngine();
		Expression expression = jexlEngine.createExpression(re);
		Script script = jexlEngine.createScript(re);
		JexlContext jexlContext = new MapContext();
		for(Map.Entry<String, Object> entry :map.entrySet()){
			jexlContext.set(entry.getKey(),entry.getValue());
		}

		return expression.evaluate(jexlContext);
	}

}
