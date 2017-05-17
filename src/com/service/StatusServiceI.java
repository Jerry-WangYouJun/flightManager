package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Status;

public interface StatusServiceI {

	List<Status> findStatusDicMaps();

	Grid findStatusList(Status status);

	Integer addStatus(Status status);

}
