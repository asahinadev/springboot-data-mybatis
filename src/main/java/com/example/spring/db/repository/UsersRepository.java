package com.example.spring.db.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.example.spring.db.entity.User;

@Mapper
public interface UsersRepository {

	@SelectProvider(type = UsersRepositoryBuilder.class, method = "findById")
	public Optional<User> findById(String id);

	@SelectProvider(type = UsersRepositoryBuilder.class, method = "findByEmail")
	public Optional<User> findByEmail(String email);

	@SelectProvider(type = UsersRepositoryBuilder.class, method = "findByUsername")
	public Optional<User> findByUsername(String username);

	@InsertProvider(type = UsersRepositoryBuilder.class, method = "insert")
	public void insert(User user);

	@UpdateProvider(type = UsersRepositoryBuilder.class, method = "update")
	public void update(User user);

}
