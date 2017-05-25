package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.AdminCountryCount;
import com.app.model.BrandsOnly;
import com.app.model.CategoryType;
import com.app.model.HighestSoldItem;
import com.app.model.SubCategoryType;
import com.app.service.AdminOtherDetails;
import com.app.service.AdminPageAllInfo;
import com.app.service.BrandInfo;
import com.app.service.HighestSoldInfo;


@Controller
public class AdminPageInfo {

	@Autowired
	HighestSoldInfo details;
	
	@Autowired
	AdminPageAllInfo allInfo;
	
	@Autowired
	AdminOtherDetails otherDetails;
	
	@Autowired
	BrandInfo brandInfo;
	
	@RequestMapping(value = "/getHighestSoldProduct" , method = RequestMethod.GET)
	public @ResponseBody List<HighestSoldItem> getProductHighest()
	{
		return details.getHighestSold();
	}
	
	@RequestMapping(value = "/getCountrySoldProduct" , method = RequestMethod.GET)
	public @ResponseBody List<AdminCountryCount> getCountryHighest()
	{
		return details.getHighestSoldByCountry();
	}
	
	@RequestMapping(value = "/admin" , method = RequestMethod.GET)
	public String getAdminPage(Model model)
	{
		model.addAttribute("customer" , allInfo.customerCountList().get(0).getTotal());
		model.addAttribute("product" , allInfo.productCountList().get(0).getTotal());
		model.addAttribute("shipping" , allInfo.shippingCountList().get(0).getTotal());
		model.addAttribute("supplier" , allInfo.supplierCountList().get(0).getTotal());
		model.addAttribute("category", allInfo.supplierCountList().get(0).getTotal());
		model.addAttribute("subCategory" , allInfo.categorySubCountList().get(0).getTotal());
		return "admin";
	}
	
	@RequestMapping(value = "/adminOther" , method = RequestMethod.GET)
	public String getAdminOther()
	{
		return "adminOther";
	}
	
	@RequestMapping(value = "/addBrand")
	public @ResponseBody int getAddBrand(@RequestParam("brandName") String brandName)
	{
		return otherDetails.addBrand(brandName);
	}
	
	@RequestMapping(value = "/addCategory")
	public @ResponseBody int getAddCategory(@RequestParam("categoryType") String categoryType)
	{
		return otherDetails.addCategory(categoryType);
	}
	
	@RequestMapping(value = "/addSubCategory")
	public @ResponseBody int getAddSubCategory(@RequestParam("categoryID") int categoryID , @RequestParam("subCategoryType") String subCategoryType)
	{
		return otherDetails.addSubCategory(categoryID, subCategoryType);
	}
	
	@RequestMapping(value = "/getOnlyBrand")
	public @ResponseBody List<BrandsOnly> getBrand()
	{
		return brandInfo.onlyBrands();
	}
	
	@RequestMapping(value = "/getCategoryOnly")
	public @ResponseBody List<CategoryType> getCategory()
	{
		return otherDetails.getCategory();
	}
	
	@RequestMapping(value = "/getSubCategoryOnly")
	public @ResponseBody List<SubCategoryType> getSubCategory()
	{
		return otherDetails.getSubCategory();
	}
	
	@RequestMapping(value = "/adminSubCategory"  , method = RequestMethod.GET)
	public String getAdminSubCategory(Model model)
	{
		model.addAttribute("category" , otherDetails.getCategory());
		return "adminSubCategory";
	}
}