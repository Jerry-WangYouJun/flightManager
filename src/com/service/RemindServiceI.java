package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Remind;

public interface RemindServiceI {

	List<Remind> findRemindDicMaps();

	Grid findRemindList(Remind remind);

	Integer addRemind(Remind remind);

}
