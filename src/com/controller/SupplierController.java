package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.Grid;
import com.mapping.SupplierMapper;
import com.pojo.Supplier;
import com.service.SupplierServiceI;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Resource(name="supplierServiceImpl")
	private SupplierServiceI supplierService;

	@Resource
	private SupplierMapper supplierDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectSupplier(Supplier supplier ){
		return  this.supplierService.findSupplierList(supplier);
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addSupplier(Supplier supplier ){
		 return this.supplierService.addSupplier(supplier);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteSupplier(@RequestParam Integer id ){
		return this.supplierDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateSupplierInit(@RequestParam int id ){
		Supplier supplier= this.supplierDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("supplier",supplier);//userlist是个Arraylist之类的  
		return new ModelAndView("supplier_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findSupplierDetail(@ModelAttribute("supplierForm") Supplier supplier ){
		return this.supplierDao.updateByPrimaryKeySelective(supplier);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/supplierDics")
	@ResponseBody
	public Map getSupplierDics(){
		Map map = new HashMap();
		map.put("results", this.supplierService.findSupplierDicMaps());
		map.put("success", true);
		return map;
	}
}
