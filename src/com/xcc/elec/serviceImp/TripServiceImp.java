/**
 * 
 */
package com.xcc.elec.serviceImp;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.xcc.elec.service.TripService;

/**
 *  Administrator
 *
 */
@Service
public class TripServiceImp extends BaseServiceImp implements TripService {

	/* 
	 * 
	 */
	@Override
	public void insertNewTripInfo(String userid, String startaddress, String endaddress, String length, String hastime,
			String speed, String starttime, String endtime) {
		String sql="insert into tb_trip (userid,startaddress,endaddress,length,hastime,speed,start_time,end_time) "
				+ "values (?,?,?,?,?,?,?,?)";
		 saveAndGetId(sql, new Object[]{userid,startaddress,endaddress,length,hastime,speed,starttime,endtime});
	}


	/* 
	 * 
	 */
	@Override
	public void insertNewTripList(long tripid, String lng, String lat) {
		String sql="insert into trip_list(tripid,lng,lat) values(?,?,?)";
		update(sql,new Object[]{tripid,lng,lat});
	}

	
	
	
	
	
	
	
	
	
}
