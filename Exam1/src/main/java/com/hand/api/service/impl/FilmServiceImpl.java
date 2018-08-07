package com.hand.api.service.impl;

import com.hand.api.service.FilmService;
import com.hand.infra.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmMapper filmMapper;

	@Override
	public void addFile(Map<String,Object> map) {
		filmMapper.addFilm(map);
	}
}
