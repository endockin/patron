package com.endava.patron.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.endava.patron.dao.CategoryDAO;

@RestController
@RequestMapping("/service/category/")
public class CategoryServiceController {

	@Autowired
	private CategoryDAO categoryService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public MappingJacksonValue getAllCategories(@RequestParam String callback) {
		MappingJacksonValue value = new MappingJacksonValue(categoryService.getCategories());
        value.setJsonpFunction(callback);
        return value;
	}
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public MappingJacksonValue getStatus(@RequestParam String callback) {
		MappingJacksonValue value = new MappingJacksonValue("Category rest services are up and running!");
        value.setJsonpFunction(callback);
        return value;
	}

	public CategoryDAO getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryDAO categoryService) {
		this.categoryService = categoryService;
	}
	

}
