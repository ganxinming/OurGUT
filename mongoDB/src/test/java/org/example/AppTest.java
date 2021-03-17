package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        List<Integer> list =new ArrayList<>();
        list.add(1);
        list.add(2);
//        list.addAll(null);
		List<Integer> collect = list.stream().filter(x->x == 1).collect(Collectors.toList());
		System.out.println(collect);
    }
}
