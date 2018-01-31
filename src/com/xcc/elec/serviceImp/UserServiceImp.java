package com.xcc.elec.serviceImp;


import java.util.Date;

import org.springframework.stereotype.Service;

import com.xcc.elec.model.ApkVersion;
import com.xcc.elec.model.ApkVersionRowMapper;
import com.xcc.elec.model.User;
import com.xcc.elec.model.UserRowMapper;
import com.xcc.elec.model.UserSecret;
import com.xcc.elec.model.UserSecretRowMapper;
import com.xcc.elec.service.UserService;


@Service
public class UserServiceImp extends BaseServiceImp implements UserService{



	/* 
	 * 
	 * 
	 *
	 */
	@Override
	public void insertUserFeedBack(String userid, String content) {
		// TODO Auto-generated method stub
		String sql = "insert into user_feedback(userid,content,createtime,status) "
				+ "values(?,?,?,?)";



	}

	/* 
	 * 
	 *	select * from user where mobile=? and status=1
	 *
	 */
	@Override
	public User loginName(String mobile) {
		//select id from user_secret where mobile = ? limit 1
		String sql="select * from tb_root_user where phone=?";
		return getEntry(sql, new UserRowMapper(), new Object[]{mobile});
	}

	/* 
	 * 
	 */
	@Override
	public ApkVersion getApkVersion(String channel) {
		String sql = "select * from apk_version where channel = ? ";
		return getEntry(sql, new ApkVersionRowMapper(), new Object[]{channel});
	}

	/* 
	 * 
	 */
	@Override
	public String getMobileSecret(String mobile) {
		String sql = "select id from user_secret where mobile = ? limit 1";
		return queryString(sql, new Object[]{mobile});
	}

	/* 
	 * 
	 */
	@Override
	public void insertMobileSecret(String mobile, String secret) {
		String sql = "insert into user_secret(mobile,secret,createtime,endtime) values(?,?,?,?)";
		long curren = System.currentTimeMillis();
	    curren += 10 * 60 * 1000;
	    Date da = new Date(curren);
	    update(sql, new Object[]{mobile,secret,new Date(),da});
	}

	/* 
	 * 
	 */
	@Override
	public void updateMObileSecret(String mobile, String secret, String id) {
		String sql="update user_secret set mobile=?,secret=?,endtime=?,createtime=?"
				+ " where id=?";
		long curren = System.currentTimeMillis();
	    curren += 10 * 60 * 1000;
	    Date da = new Date(curren);
		update(sql, new Object[]{mobile,secret,da,new Date(),id});


	}

	/* 
	 *
	 *
	 *
	 * 用户手机查询
	 * 
	 * 
	 */
	@Override
	public UserSecret getUserSecretByMobile(String mobile) {
		String sql = "select * from user_secret where mobile = ? ";//and "+ "endtime >=now() limit 1";
		return getEntry(sql, new UserSecretRowMapper(), new Object[]{mobile});

	}

	/* 
	 * 
	 */
	@Override
	public User getUserByMobile(String mobile) {
		String sql = "select * from tb_root_user where phone = ?";
		return getEntry(sql, new UserRowMapper(), new Object[]{mobile});
	}

	/* 
	 * 
	 */
	@Override
	public void updateUserPwdByUserId(String pwd, int id) {

		String sql="update tb_root_user set userpassword=? where id=?";
		update(sql, new Object[]{pwd,id});

	}

	/* 
	 * 用户注册
	 * INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
	 * 注册 : 密码，手机，用户名，昵称，身份证，真实姓名
	 */
	@Override
	public void insertUser(User user) {
		String sql="insert into tb_root_user (name,phone,"
				+ "userpassword,identity,nickname) values (?,?,?,?,?)";
		update(sql,new Object[]{user.getName(),user.getMobile(),user.getPwd(),user.getIdentity(),user.getNickname()});
	}

	
	
	@Override
	public User getUserByUserId(String userid) {
		String sql="select * from tb_root_user where identity=? ";//and status=1";
		return getEntry(sql, new UserRowMapper(), new Object[]{userid});
	}

	/* 
	 */
	@Override
	public void updateUserMobileByUserId(String newmobile, int id) {
		String sql="update tb_root_user set phone=? where id=?";
		update(sql, new Object[]{newmobile,id});
	}



}







