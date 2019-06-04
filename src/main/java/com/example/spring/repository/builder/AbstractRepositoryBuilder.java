package com.example.spring.repository.builder;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRepositoryBuilder {

	protected static <E> Map<String, Object> convert(E entity) {

		Map<String, Object> map = new ObjectMapper().convertValue(entity, new TypeReference<Map<String, Object>>() {
			// nop
		});

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			log.debug("{} : {}", entry.getKey(), value == null ? "<NULL>" : value.getClass());
			map.put(entry.getKey(), value);

		}

		log.debug("entity : {}", entity);
		log.debug("map    : {}", map);

		return map;
	}
}
