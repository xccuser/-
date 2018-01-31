package com.xcc.elec.service;


import java.util.List;




public interface TripService {

	void insertNewTripInfo(String userid, String startaddress, String endaddress,
			String length, String hastime, String speed, String starttime,
			String endtime);
	void insertNewTripList(long tripid, String lng, String lat);

}
