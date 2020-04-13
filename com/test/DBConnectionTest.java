package com.test;

import com.util.DBConnectionMgr;

public class DBConnectionTest {
	void methodA(DBConnectionMgr bdMgr1) {
		System.out.println(this+" ,"+bdMgr1);
	}

	public static void main(String[] args) {
		DBConnectionTest dbTest = new DBConnectionTest();
		DBConnectionMgr bdMgr1 = DBConnectionMgr.getInstance();
		DBConnectionMgr bdMgr2 = DBConnectionMgr.getInstance();
		dbTest.methodA(bdMgr1);
		if(bdMgr1==bdMgr2) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}

}
