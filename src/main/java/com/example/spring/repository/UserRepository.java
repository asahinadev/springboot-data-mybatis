package com.example.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.example.spring.entity.User;
import com.example.spring.repository.builder.UsersRepositoryBuilder;

@Mapper
public interface UserRepository {

	@Results(id = "users", value = {
			// @formatter:off
			@Result(property =  "id",                 column =  "id",                  javaType =  String.class,        jdbcType = JdbcType.VARCHAR),
			@Result(property =  "username",           column =  "username",            javaType =  String.class,        jdbcType = JdbcType.VARCHAR),
			@Result(property =  "email",              column =  "email",               javaType =  String.class,        jdbcType = JdbcType.VARCHAR),
			@Result(property =  "password",           column =  "password",            javaType =  String.class,        jdbcType = JdbcType.VARCHAR),
			@Result(property =  "enabled",            column =  "enabled",             javaType =  boolean.class,       jdbcType = JdbcType.BIT),
			@Result(property =  "locked",             column =  "locked",              javaType =  boolean.class,       jdbcType = JdbcType.BIT),
			@Result(property =  "credentialsExpired", column =  "credentials_expired", javaType =  LocalDateTime.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property =  "accountExpired",     column =  "accoun_expired",      javaType =  LocalDateTime.class, jdbcType = JdbcType.TIMESTAMP),
			// @formatter:on
	})
	@SelectProvider(type = UsersRepositoryBuilder.class, method = "findByUsername")
	public User findByUsername(String username);

	@ResultMap("users")
	@SelectProvider(type = UsersRepositoryBuilder.class, method = "findByEmail")
	public User findByEmail(String email);

	@ResultMap("users")
	@SelectProvider(type = UsersRepositoryBuilder.class, method = "findById")
	public User findById(String email);

	@ResultMap("users")
	@SelectProvider(type = UsersRepositoryBuilder.class, method = "findAll")
	public List<User> findAll();

	@InsertProvider(type = UsersRepositoryBuilder.class, method = "insert")
	public int insert(User entity);

	@UpdateProvider(type = UsersRepositoryBuilder.class, method = "update")
	public int update(User entity);

	@DeleteProvider(type = UsersRepositoryBuilder.class, method = "delete")
	public int delete(User entity);

}
