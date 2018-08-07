package com.hand.infra.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Repository;

@Aspect
@Repository
public class PublishString {

	@After("execution(* com.hand.api.service.impl.FilmServiceImpl.*(..))")
	public void AfterInsertFilmEvent() {
		System.out.println("After Insert Film Data");
	}

	@Before("execution(* com.hand.api.service.impl.FilmServiceImpl.*(..))")
	public void BeforeInsertFilmEvent() {
		System.out.println("Before Insert Film Data");
	}

}
