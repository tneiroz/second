package me.eun.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	 						         //모든 패키지 * , 함수 * , 파라미터 (..) 
	@Before("execution(* me.eun.service.*.*(..)")
	public void logBefore() {
		System.out.println("=====================");
	}
}
