package com.xcc.elec.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ApkVersionRowMapper implements RowMapper<ApkVersion>{

	@Override
	public ApkVersion mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApkVersion apkVersion = new ApkVersion();
		apkVersion.setId(rs.getInt("id"));
		apkVersion.setApkversion(rs.getDouble("apkversion"));
		apkVersion.setApkurl(rs.getString("apkurl"));
		apkVersion.setChannel(rs.getInt("channel"));
		return apkVersion;
	}

}
