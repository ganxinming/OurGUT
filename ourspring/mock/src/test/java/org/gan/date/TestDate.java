package org.gan.date;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2021/4/6
 * @description
 */
public class TestDate {

	@Test
	public void test(){
		CaseCustomerVO vo=new CaseCustomerVO();
		vo.setTaskStartTime(new Date());
		System.out.println(JSON.toJSONString(vo));
	}
}
@Data
class CaseCustomerVO {
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date taskStartTime;

}
