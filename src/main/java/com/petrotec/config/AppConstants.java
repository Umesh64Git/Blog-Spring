package com.petrotec.config;

import org.springframework.web.bind.annotation.RequestParam;

public class AppConstants {
	
	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "10";
	public static final String SORT_BY = "postId";
	public static final String SORT_DIR = "0";
	
	
	//Good Practice To Avoid Hard Coded Values By Using Constant (softCoding)
	//How To Use
	//ye Sarva static variables aahet
	//static variables la aapan class chya naava sobat use karto
	//defaultValue = AppConstants.PAGE_Number
	//postController madhe bagha aapan waparle aahe.
	//jevha pn aaplya la ya values la waprayachay tar aapan tyaana asach wapruu
	//aani jar future madhe aaplya la tyaana change pan karaych asel tar aapan tyaana ithioon change karuu shakato
	//kiva aankhin kahi fields pn add karuu shakato.
	
	//Explaination--> aapan hard Coded values na lihata aapya controller madhe direct tyaana aadhic declare 
	//karoon thevuu shakato
	//jasa apan pagination kartaana default value hard code kelya
	//tasach jar aaplya la dusrya kuthalya api saathi pagination karayachay tar parat aaplya la ya 
	//values manually set karawya laagti aaplya controller class madhe 
	//he hardcoding kami zhali pahije tya saathi aaapan ha AppConstant class banavtoy.
	//Soft Coding Saathi 
// @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
// @RequestParam(value = "sortBy",defaultValue = "postId",required = false)String sortBy,
// @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){

}
