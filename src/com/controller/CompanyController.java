package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.Grid;
import com.mapping.CompanyMapper;
import com.pojo.Company;
import com.service.CompanyServiceI;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Resource(name="companyServiceImpl")
	private CompanyServiceI companyService;
	

	@Resource
	private CompanyMapper companyDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectCompany(Company company ){
		return  this.companyService.findCompanyList(company);
	}
	
	@RequestMapping("/addinit")
	public ModelAndView addInit(){
		ModelAndView mv = new ModelAndView("company_add");
		List<Company> list = companyService.findCompanyDicMaps();
		mv.addObject("companylist", list) ;
		return mv ;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addCompany(Company company ){
		 return this.companyService.addCompany(company);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteCompany(@RequestParam Integer id ){
		return this.companyDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updateCompanyInit(@RequestParam int id ){
		Company company= this.companyDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("company",company);//userlist是个Arraylist之类的  
		List<Company> list = companyService.findCompanyDicMaps();
		model.put("companylist", list) ;
		return new ModelAndView("company_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findCompanyDetail(@ModelAttribute("companyForm") Company company ){
		return this.companyDao.updateByPrimaryKeySelective(company);
	}
	
}
