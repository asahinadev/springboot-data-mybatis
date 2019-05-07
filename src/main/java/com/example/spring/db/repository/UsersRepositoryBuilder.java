package com.example.spring.db.repository;

import java.util.Objects;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.example.spring.db.entity.User;

public class UsersRepositoryBuilder {

	public static final String TABLE = "users";

	protected SQL find() {
		return new SQL().SELECT("*").FROM(TABLE);
	}

	public String findById(String id) {
		return find().WHERE("id = #{id}").toString();
	}

	public String findByEmail(String email) {
		return find().WHERE("email = #{email}", "deleted IS NULL").toString();
	}

	public String findByUsername(String username) {
		return find().WHERE("username= #{username}", "deleted IS NULL").toString();
	}

	public String insert(User user) {
		SQL insert = new SQL().INSERT_INTO(TABLE);

		insert.INTO_COLUMNS("id");
		insert.INTO_VALUES("#{user.id}");

		if (!StringUtils.isEmpty(user.getUsername())) {
			insert.INTO_COLUMNS("username");
			insert.INTO_VALUES("#{user.username}");
		}

		if (!StringUtils.isEmpty(user.getEmail())) {
			insert.INTO_COLUMNS("email");
			insert.INTO_VALUES("#{user.email}");
		}

		if (!StringUtils.isEmpty(user.getPassword())) {
			insert.INTO_COLUMNS("password");
			insert.INTO_VALUES("#{user.password}");
		}

		return insert.toString();

	}

	public String update(User user) {
		SQL update = new SQL().INSERT_INTO(TABLE);

		update.WHERE("id = #{user.id}");

		if (!StringUtils.isEmpty(user.getUsername())) {
			update.SET("username = #{user.username}");
		}

		if (!StringUtils.isEmpty(user.getEmail())) {
			update.SET("email = #{user.email}");
		}

		if (!StringUtils.isEmpty(user.getPassword())) {
			update.SET("password = #{user.password}");
		}

		if (!Objects.isNull(user.getDeleted())) {
			update.SET("deleted = #{user.deleted}");
		}
		return update.toString();
	}

}
