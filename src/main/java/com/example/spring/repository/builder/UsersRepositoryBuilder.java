package com.example.spring.repository.builder;

import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.jdbc.SQL;

import com.example.spring.entity.User;

public class UsersRepositoryBuilder
		extends AbstractRepositoryBuilder {

	public static String TABLE = "users";

	public String findByUsername(String username) {

		return new SQL().SELECT("*").FROM(TABLE).WHERE("username = #{username}").toString();
	}

	public String findByEmail(String email) {

		return new SQL().SELECT("*").FROM(TABLE).WHERE("email = #{email}").toString();
	}

	public String findById(String id) {

		return new SQL().SELECT("*").FROM(TABLE).WHERE("id = #{id}").toString();
	}

	public String findAll() {

		return new SQL().SELECT("*").FROM(TABLE).toString();
	}

	public String insert(User entity) {

		Map<String, Object> parameters = convert(entity);

		SQL sql = new SQL().INSERT_INTO(TABLE);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value != null) {
				if (Objects.equals(key, "accountExpired")) {
					sql.VALUES("account_expired", String.format("#{%s}", key));
				} else if (Objects.equals(key, "credentialsExpired")) {
					sql.VALUES("credentials_expired", String.format("#{%s}", key));
				} else {
					sql.VALUES(key, String.format("#{%s}", key));
				}
			}
		}

		return sql.toString();

	}

	public String update(User entity) {

		Map<String, Object> parameters = convert(entity);

		SQL sql = new SQL().UPDATE(TABLE);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (Objects.equals(entry.getKey(), "id")) {
				sql.WHERE(String.format("%s = #{%s}", key, key));
			} else if (Objects.equals(key, "accountExpired")) {
				sql.SET(String.format("account_expired = #{%s}", key));
			} else if (Objects.equals(key, "credentialsExpired")) {
				sql.SET(String.format("credentials_expired = #{%s}", key));
			} else if (value != null) {
				sql.SET(String.format("%s = #{%s}", key, key));
			}
		}

		return sql.toString();

	}

	public String delete(User entity) {

		SQL sql = new SQL().DELETE_FROM(TABLE);
		sql.WHERE(String.format("%s = #{%s}", "id", "id"));
		return sql.toString();
	}

}
