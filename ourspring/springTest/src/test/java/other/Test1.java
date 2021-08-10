package other;

import java.util.HashSet;

import com.ourgut.springtest.People;
import org.junit.jupiter.api.Test;

/**
 * @author ganxinming
 * @createDate 2021/8/10
 * @description
 */
public class Test1 {

	@Test
	public void test1(){
		People people=new People("123",1231);
		People people1=new People("123",1231);
		HashSet<People> set=new HashSet<>();
		set.add(people1);
		set.add(people);
		set.forEach(x->{
			System.out.println(x.toString());
		});
	}
}
