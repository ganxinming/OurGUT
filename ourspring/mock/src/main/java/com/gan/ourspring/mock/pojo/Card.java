package com.gan.ourspring.mock.pojo;

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
public class Card {
	private int score;
	private String name;
}
