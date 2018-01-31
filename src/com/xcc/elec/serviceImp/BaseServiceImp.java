package com.xcc.elec.serviceImp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.xcc.api.util.StringUtil;

public class BaseServiceImp {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 用户insert、update、delete类型的sql语句
	 * @param sql
	 * @param params  对象类型的数组，如果是list 则在调用发放前必须调用list.toArray()
	 */
	public void update(String sql,Object[] params){
		LogFactory.getLog(this.getClass()).info("update sql:"+sql+",["+StringUtil.join(params,",")+"]");
		System.out.println("update sql:"+sql+",["+StringUtil.join(params,",")+"]");
		this.jdbcTemplate.update(sql, params);
		
	}

	public <T> T getEntry(String sql,RowMapper<T> rowMapper,Object[] params){
		List<T> list = getEntryList(sql,rowMapper,params);
		if(list.size() == 0) return null;
		return list.get(0);
	}

	public <T> List<T> getEntryList(String sql,RowMapper<T> rowMapper,Object[] params){
		LogFactory.getLog(this.getClass()).info("query sql:"+sql+",["+StringUtil.join(params,",")+"]");
		System.out.println("query sql:"+sql+",["+StringUtil.join(params,",")+"]");
		return jdbcTemplate.query(sql, params, rowMapper);
	}

	
	public String queryString(String sql,Object[] params){
		List<String> list = queryStringArray(sql, params);
		if(list.size() == 0) return null;
		return list.get(0);
	}
	
	public List<String> queryStringArray(String sql,Object[] params){
		LogFactory.getLog(this.getClass()).info("queryForList sql:"+sql+",["+StringUtil.join(params,",")+"]");
		return this.jdbcTemplate.queryForList(sql, String.class, params);
	}
	
	public void saveAndGetId(final String sql,final Object[] params){
		LogFactory.getLog(this.getClass()).info("insert sql:"+sql+",["+StringUtil.join(params,",")+"]");
		this.jdbcTemplate.update(sql, params);
		/*KeyHolder keyHolder = new GeneratedKeyHolder();  
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();*/
	}
	
}
