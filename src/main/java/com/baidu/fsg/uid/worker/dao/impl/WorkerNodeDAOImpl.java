package com.baidu.fsg.uid.worker.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baidu.fsg.uid.worker.dao.WorkerNodeDAO;
import com.baidu.fsg.uid.worker.entity.WorkerNodeEntity;

@Repository
public class WorkerNodeDAOImpl implements WorkerNodeDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final static String SQL_INSERT = "INSERT INTO WORKER_NODE" + 
			"(HOST_NAME, PORT, TYPE, LAUNCH_DATE, MODIFIED, CREATED)" + 
			"VALUES (?, ?, ?, ?, NOW(), NOW())";
	
	private final static String SQL_SELECT = "SELECT ID, HOST_NAME, PORT, TYPE, LAUNCH_DATE, MODIFIED, CREATED FROM WORKER_NODE\r\n" + 
			"WHERE HOST_NAME = ? AND PORT = ?";
	
	@Override
	public WorkerNodeEntity getWorkerNodeByHostPort(String host, String port) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SQL_SELECT, new Object[] {host, port}, WorkerNodeEntity.class);
	}

	@Override
	public void addWorkerNode(WorkerNodeEntity workerNodeEntity) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(SQL_INSERT, workerNodeEntity.getHostName()
				, workerNodeEntity.getPort()
				, workerNodeEntity.getType()
				, workerNodeEntity.getLaunchDate());
	}

}
