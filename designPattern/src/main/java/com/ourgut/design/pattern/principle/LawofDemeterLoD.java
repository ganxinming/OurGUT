package com.ourgut.design.pattern.principle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ganxinming
 * @createDate 2022/1/8
 * @description
 * 迪米特原则是指一个对象应该对其他对象保持最少的了解，尽量降低类与类之间的耦合度。
 * （前提：如果是没有必要直接通信，比如：你老板让会计统计钱有多少，不需要让老板带着钱去给会计，应该是会计自己去想办法拿到钱统计，老板只需要问会计拿到结果就好了）
 * 如果两个类不必彼此直接通信，那么这两个类就不应当发生直接的相互作用
 * 迪米特原则主要强调：只和朋友交流，不和陌生人说话。出现在成员变量、方法的输入、输出参数中的类都可以称为成员朋友类，
 * 而出现在方法体内部的类不属于朋友类。
 *
 * 比如：A和B类进行交流，B类依赖C字段，进行统计，不要让A类去传入
 *
 * 思考：简单点说：一个类如无必要，无需了解其他类中相关的属性或者类，但是入参类和出参类都可以关心，而出现在方法体内的类，不应该关心他。
 * 像下面：boos调用TeamLeader，应该一个方法就结束而不应该，传参数进去。他们之间其实没有必要依赖这个参数
 */
public class LawofDemeterLoD {


	public class TeamLeader {
		//这里TeamLeader不应该让Boss传入参数，然后去统计
		public void checkNumberOfCourses(List<ICourse> courseList){

			/**
			 * 正确应该自己去得到需要的参数
			 */
//			List<Course> courseList = new ArrayList<Course>();
//
//			for(int i = 0 ;i < 20;i++){
//
//				courseList.add(new Course());
//
//			}

			System.out.println("目前已发布的课程数量是："+courseList.size());
		}
	}

	 class Boss {

		public void commandCheckNumber(TeamLeader teamLeader){

			//模拟Boss一页一页往下翻页，TeamLeader实时统计
			List<ICourse> courseList = new ArrayList<ICourse>();

			for (int i= 0; i < 20 ;i ++){
				courseList.add(new ICourseImpl());
			}
			teamLeader.checkNumberOfCourses(courseList);

		}

	}


}
