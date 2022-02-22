package com.gan.ourspring.aop.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.smartcardio.Card;

import com.gan.ourspring.aop.annotation.FiledAnno;
import lombok.Data;
import lombok.ToString;

/**
 * @author ganxinming
 * @createDate 2021/9/16
 * @description
 */
@Data
public class UserResponse implements Serializable {
	private Map<String, Integer> map;
	//进行注解标记，在切面中就可以进行操作了
	@FiledAnno(value = 1,name = "list")
	private List<Object> list ;
	private String name;
	private Integer age;
	@FiledAnno(value = 2,name = "family")
	private Family family;
}
