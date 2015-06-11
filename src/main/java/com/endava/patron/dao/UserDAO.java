package com.endava.patron.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.endava.patron.model.User;

@Repository
public class UserDAO {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public User loginUser(User user) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
		String sql = "select * from Users where username=:username and password=:password";
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<User> result = null;
		try {
			parameters.put("username", user.getUsername());
			parameters.put("password", user.getPassword());
			result = jdbcTemplate.query(sql, parameters, new UserRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((result != null) && (!result.isEmpty())) {
			user = result.get(0);
		}
		return user;
	}

}
