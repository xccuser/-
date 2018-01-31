/**
 * 
 */
package com.xcc.elec.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**
 *  Administrator
 *
 */
public class UserSecretRowMapper implements RowMapper<UserSecret> {


	@Override
	public UserSecret mapRow(ResultSet rs, int arg1) throws SQLException {
		UserSecret usersecret = new UserSecret();
		usersecret.setId(rs.getInt("id"));
		usersecret.setMobile(rs.getString("mobile"));
		usersecret.setSecret(rs.getString("secret"));		
		usersecret.setCreatetime(rs.getTimestamp("createtime"));
		usersecret.setEndtime(rs.getTimestamp("endtime"));
		return usersecret; 
	}

	
	
	
	
}
