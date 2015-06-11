package com.endava.patron.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.endava.patron.model.Category;
import com.endava.patron.model.Image;

@Repository
public class CategoryDAO {

	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Category> getCategories(){
		String sql = "select * from Categories inner join Images on Categories.categoryid=Images.categoryid";
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = template.queryForList(sql);
		List<Category> categories = new ArrayList<Category>();
		for (Map<String, Object> row : rows) {
			System.out.println(row);
			Image image = new Image();
			image.setImageId((Integer)row.get("imageid"));
			image.setImageName((String)row.get("imagename"));
			image.setImageDescription((String)row.get("imagedescription"));
			image.setImageLogo((String)row.get("imageLogo"));
			Category category = new Category();
			category.setCategoryId((Integer) row.get("categoryid"));
			image.setCategoryId((Integer) row.get("categoryid"));
			if (categories.contains(category)) {
				Category categoryFromList = categories.get(categories.indexOf(category));
				categoryFromList.getImages().add(image);
			}else {
				category.setCategoryName((String) row.get("categoryname"));
				List<Image> categoryImages = new ArrayList<Image>();
				categoryImages.add(image);
				category.setImages(categoryImages);
				categories.add(category);
			}
		}
		return categories;		
	}
}
