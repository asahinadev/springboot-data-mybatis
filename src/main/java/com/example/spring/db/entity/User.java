package com.example.spring.db.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring.db.values.Role;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class User implements UserDetails {

	String id;
	String username;
	String email;
	String password;
	LocalDateTime created;
	LocalDateTime updated;
	LocalDateTime deleted;

	@Override
	public List<Role> getAuthorities() {
		return Arrays.asList(Role.ADMIN, Role.USER);
	}

	@Override
	public boolean isAccountNonExpired() {
		// アカウント有効期限内
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 否ロック状態
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// パスワード有効期限内
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 有効判定
		return Objects.isNull(deleted);
	}

}
