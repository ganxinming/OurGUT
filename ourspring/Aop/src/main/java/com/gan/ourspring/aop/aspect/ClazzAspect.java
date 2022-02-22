package com.gan.ourspring.aop.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.gan.ourspring.aop.annotation.FiledAnno;
import com.gan.ourspring.aop.annotation.MethodAnno;
import com.gan.ourspring.aop.entity.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author ganxinming
 * @createDate 2021/9/16
 * @description
 */
@Aspect
@Component
@Slf4j
public class ClazzAspect {


	@Pointcut("@annotation(com.gan.ourspring.aop.annotation.MethodAnno)")
	public void clazzMethod(){};

	@Before("clazzMethod()")
	public void before(JoinPoint point){
		log.info("before");
		//可以获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		System.out.println(request);
		//获取切入点各种值
		//获取参数的值，但拿不到参数名
		for(Object arg:point.getArgs()){
			System.out.println(arg);
		}
		Object target = point.getTarget();
		log.info("目标对象：{}", JSONUtil.toJsonStr(target));
		/**
		 * 有了目标对象就可以进行反射
		 */
		String methodName = point.getSignature().getName();
		Method method1 = null;
		/**
		 * 反射获取方法和注解(发现注解都是属于方法Method上的)
		 */
		for (Method m : target.getClass().getMethods()) {
			if (m.getName().equals(methodName)) {
				method1 = m;
				log.info("反射匹配了方法了:{}",methodName);
				for(Annotation aa:m.getAnnotations()){
					if(aa.annotationType().getSimpleName().equals(MethodAnno.class.getSimpleName())){
						log.info("第一种方式匹配了MethodAnno.class");
					}
					log.info("第一种方式获取，使用到了注解：{}",aa.annotationType().getSimpleName());
				}
				break;
			}
			log.info("第一种方式：{}",m.getName());
		}
		//signature能够拿到 修饰符+ 包名+组件名(类名) +方法名
		Signature signature = point.getSignature();
		log.info("参数名:{}",JSONUtil.toJsonStr(signature.getDeclaringTypeName()));
		//获取切入点方法名
		log.info("切入点方法：{}",signature.getName());
		/**
		 * 2.获取方法上的注解
		 */
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method2 = methodSignature.getMethod();
		if (method2 != null)
		{
			for(Annotation aa:method2.getAnnotations()){
				if(aa.annotationType().getSimpleName().equals(MethodAnno.class.getSimpleName())){
					log.info("第二种方式匹配了MethodAnno.class");
				}
				log.info("第二种方式获取，使用到了注解：{}",aa.annotationType().getSimpleName());
			}
		}

		log.info(signature.getDeclaringTypeName());
		log.info(signature.getDeclaringType().getSimpleName());
		//连接点所在位置的全部相关信息
		log.info(point.toLongString());
		//返回AOP代理对象
		Object aThis = point.getThis();
		log.info("AOP代理对象：{}",aThis.toString());

	}

	@After("clazzMethod()")
	public void after(){
		log.info("after");
	}

	/**
	 * 正常情况：@Before=====目标方法=====@AfterReturning=====@After
	 * 统一修改返回结果或者加密
	 * @param joinPoint
	 * @param o 设置returning，对应返回的对象，名称和传入的参数名相同
	 */
	@AfterReturning(returning = "o", pointcut = "clazzMethod()")
	public void methodAfterReturing(JoinPoint joinPoint, Object o){
		log.info("AfterReturning");
		if(o instanceof UserResponse){
			//进行设置加密或者返回字段统一处理
			((UserResponse) o).setAge(Integer.MAX_VALUE);
			Annotation[] annotations = o.getClass().getAnnotations();
			for(Annotation aa:annotations){
				if(aa.annotationType().getSimpleName().equals(FiledAnno.class.getSimpleName())){
					log.info("拿到注解FiledAnno");
				}
			}
		}
		return;
	}
}
