package kr.co.kfs.assetedu.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;

@Data
public class Itm01Item {
	
	@NotNull
	@NotBlank
	private String itm01ItemCd;
	
	@NotNull
	@NotBlank
	private String itm01ItemNm;
	
	private String itm01ItemEnm;
	
	private String itm01ShortCd;
	
	private String itm01IssType;
	
	@NotBlank
	private String itm01StkType;
	
	@NotBlank
	private String itm01ListType;
	
	@NotBlank
	private String itm01MarketType;
	
	@NumberFormat(pattern = "#,###")
	private Integer itm01Par;
	
	private String itm01IssCoCd;
	
	private String itm01IssTypeNm;    //발행구분명         
	private String itm01StkTypeNm;    //증권종류명    
	private String itm01ListTypeNm;   //상장구분명  
	private String itm01MarketTypeNm; //시장구분명 
	private String itm01IssCoNm;   	  //발행기관명
}
