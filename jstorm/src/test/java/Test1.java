import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gan.jstorm.Ourjstorm;

/**
 * @author ganxinming
 * @createDate 2020/10/22
 * @description
 */
public class Test1 {
	public static void main(String[] args) {
		Ourjstorm ourjstorm=new Ourjstorm("123");
		byte[] bytes = JSON.toJSONString(ourjstorm).getBytes();
		System.out.println(bytes);
		Object parse = JSON.parse(bytes);
		Ourjstorm ourjstorm1 = JSON.toJavaObject((JSON) parse, Ourjstorm.class);
		System.out.println(ourjstorm1);
	}
}
