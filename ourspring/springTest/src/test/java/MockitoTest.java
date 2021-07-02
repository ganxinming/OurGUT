import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import com.ourgut.springtest.Compute;
import com.ourgut.springtest.People;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author ganxinming
 * @createDate 2021/7/2
 * @description
 */
public class MockitoTest {


	/**
	 * 除了使用静态方法 mock() 模拟出一个实例，我们还可以通过注解 @Mock 也模拟出一个实例：
	 */
	@Mock People people;

	/**
	 * 可以搭配spring的@service使用，使得实体类也有mock类的功能
	 */
	@Spy Compute compute;



	@InjectMocks People people1;
	@Test
	public void createMockObject() {
		// 使用 mock 静态方法创建 Mock 对象.
		List mockedList = mock(List.class);
		Assert.assertTrue(mockedList instanceof List);

		// mock 方法不仅可以 Mock 接口类, 还可以 Mock 具体的类型.
		ArrayList mockedArrayList = mock(ArrayList.class);
		Assert.assertTrue(mockedArrayList instanceof List);
		Assert.assertTrue(mockedArrayList instanceof ArrayList);

		Compute compute = mock(Compute.class);
		//当我们有了一个 Mock 对象后, 我们可以定制它的具体的行为. 例如:
		//when(​...).thenReturn(​...) 方法链不仅仅要匹配方法的调用, 而且要方法的参数一样才行.
		when(compute.add(1)).thenReturn(10L).thenReturn(11L);
		Assert.assertEquals(1,compute.add(1));

		//甚至可以设置多个thenReturn，表示第一调用这个结果，第二次是另一个结果
		Iterator i = mock(Iterator.class);
		when(i.next()).thenReturn("Hello,").thenReturn("Mockito!"); // 1
		String result = i.next() + " " + i.next(); // 2
		Assert.assertEquals("Hello, Mockito!", result);

		//设置某个mock对象在调用某个方法时抛出异常
		doThrow(new NoSuchElementException()).when(compute).add(10);

	}


	/**
	 * spy：其实就是将一个真实的对象映射成一个mock，对于没有定制化打桩的方法，都是调用实体方法。
	 */
	@Test
	public void testSpy(){
		List list = new LinkedList();
		List spy = spy(list);

		// 对 spy.size() 进行定制.
		when(spy.size()).thenReturn(100);

		spy.add("one");
		spy.add("two");

		// 因为我们没有对 get(0), get(1) 方法进行定制,
		// 因此这些调用其实是调用的真实对象的方法.
		Assert.assertEquals(spy.get(0), "one");
		Assert.assertEquals(spy.get(1), "two");

		Assert.assertEquals(spy.size(), 100);

		System.out.println(people1);
	}




	public static class ArticleManager {
		private User user;
		private ArticleDatabase database;

		public ArticleManager(User user, ArticleDatabase database) {
			super();
			this.user = user;
			this.database = database;
		}

		public void initialize() {
			database.addListener(new ArticleListener());
		}
	}

	public static class User {
	}

	public static class ArticleListener {
	}

	public static class ArticleDatabase {
		public void addListener(ArticleListener listener) {
		}
	}



	@Mock
	private User user;
	@Mock
	private ArticleDatabase database;
	@InjectMocks
	private ArticleManager manager;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void testInjectMock() {
		// Calls addListener with an instance of ArticleListener
		manager.initialize();
		// Validate that addListener was called
		verify(database).addListener(any(ArticleListener.class));
	}


}
