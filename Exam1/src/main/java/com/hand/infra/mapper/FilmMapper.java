package com.hand.infra.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FilmMapper {

	void addFilm(Map<String, Object> map);
}
