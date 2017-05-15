package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.Grid;
import com.mapping.DictionaryMapper;
import com.pojo.Dictionary;
import com.service.DictionaryServiceI;

@Controller
@RequestMapping("/dic")
public class DicController {

	@Resource(name="dictionaryServiceImpl")
	private DictionaryServiceI dicService;
	
	@Resource
	private DictionaryMapper dicDao;
	
	@ResponseBody
	@RequestMapping("/query")
	public Grid getSelectdics(Dictionary dictionary ){
		return this.dicService.findDicList(dictionary);
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Integer addDic(Dictionary Dictionary ){
		 return this.dicService.addDic(Dictionary);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer deleteDic(@RequestParam Integer id ){
		return this.dicDao.deleteByPrimaryKey(id);
		 
	}
	
	@RequestMapping("/updateinit")
	public ModelAndView updatedicInit(@RequestParam int id ){
		Dictionary dic= this.dicDao.selectByPrimaryKey(id);
		Map<String,Object> model =new HashMap<String,Object>();
		model.put("dictionary",dic);//userlist是个Arraylist之类的  
		return new ModelAndView("dictionary_update", model); 
	}
	
	@ResponseBody    
	@RequestMapping("/update")
	public int findDicDetail(@ModelAttribute("dictionaryForm") Dictionary dic ){
		return this.dicDao.updateByPrimaryKeySelective(dic);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping("/dics/{dicNo}")
	public Map getSelectdics(@PathVariable("dicNo") String dicNo){
		Map map = new HashMap();
		map.put("results", this.dicService.findDicMaps(dicNo));
		map.put("success", true);
		return map;
	}
}
