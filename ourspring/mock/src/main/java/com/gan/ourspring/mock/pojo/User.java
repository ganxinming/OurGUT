package com.gan.ourspring.mock.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2020/11/25
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String name;
	private Card card;
	private List<Card> cardList;
	private List<String> list;

	public String getSet(String name){
		return name;
	}
	public Integer getIncreate(Integer a,Integer b){
		return a+b;
	}
}
