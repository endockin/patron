package com.endava.patron.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.endava.patron.model.User;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User u = new User();
		u.setUserid(rs.getInt(1));
		u.setUsername(rs.getString(2));
		u.setPassword(rs.getString(3));
		u.setFirstName(rs.getString(4));
		u.setLastName(rs.getString(5));
		u.setEmail(rs.getString(6));
		u.setUserRole(rs.getInt(7));
		return u;
	}

}
