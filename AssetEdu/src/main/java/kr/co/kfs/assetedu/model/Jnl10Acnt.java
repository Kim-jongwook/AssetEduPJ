package kr.co.kfs.assetedu.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Jnl10Acnt {
	@NotNull
	@NotBlank
	private String jnl10AcntCd;
	
	@NotNull
	@NotBlank
	private String jnl10AcntNm;
	
	private String jnl10ParentCd;
	
	private String jnl10AcntAttrCd;
	
	private String jnl10DrcrType;
	
	private String jnl10SlipYn;
	
	private String jnl10UseYn;
	
	private String jnl10AcntAttrNm;
	
	private String jnl10DrcrTypeNm;
	
	private String jnl10ParentNm;

}
