package com.service;

import java.util.List;

import com.core.model.Grid;
import com.pojo.Immune;

public interface ImmuneServiceI {

	List<Immune> findImmuneDicMaps();

	Grid findImmuneList(Immune immune);

	Integer addImmune(Immune immune);

}
