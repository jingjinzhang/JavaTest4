package com.hand;


import com.hand.api.service.FilmService;
import com.hand.infra.config.JDBCConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

	public static void main(String[] args) {

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("================= Context Start =================");
		ApplicationContext ac = new AnnotationConfigApplicationContext(JDBCConfig.class);
		FilmService filmService = ac.getBean(FilmService.class);
		Map<String,Object> film = new HashMap<>();
		film.put("title","this is title");
		film.put("description","this is description");
		film.put("releaseYear",2018);
		film.put("languageId",1);
		film.put("originalLanguageId",null);
		film.put("rentalDuration",6);
		film.put("rentalRate","0.99");
		film.put("length",88);
		film.put("replacementCost","20.99");

		System.out.println("Film Title:");
		System.out.println(film.get("title").toString());
		System.out.println("Film Description:");
		System.out.println(film.get("description").toString());
		System.out.println("Film LanguageId:");
		System.out.println(film.get("languageId"));
		filmService.addFile(film);
		System.out.println("================= Context stop =================");
	}
}

