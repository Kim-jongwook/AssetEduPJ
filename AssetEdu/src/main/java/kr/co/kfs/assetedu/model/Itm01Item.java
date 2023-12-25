package kr.co.kfs.assetedu.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Itm01Item {
	
	@NotNull
	private String itm01ItemCd;
	
	@NotNull
	private String itm01ItemNm;
	
	private String itm01ItemEnm;
	
	private String itm01ShortCd;
	
	private String itm01IssType;
	
	private String itm01StkType;
	
	private String itm01ListType;
	
	private String itm01MarketType;
	
	private Integer itm01Par;
	
	private String itm01IssCoCd;
}
