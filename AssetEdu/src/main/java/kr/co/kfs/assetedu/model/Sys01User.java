package kr.co.kfs.assetedu.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Sys01User{
	
	@NotNull
	private String sys01UserId;  //사용자 id

	@NotNull
	private String sys01UserNm;  //사용자 이름

	@NotNull	
	transient private String sys01Pwd; //사용자 password
	
	@NotNull
	private String sys01Tel; //사용자 전화번호
	
	@NotNull
	private String sys01Email; //사용자 이메일
}
