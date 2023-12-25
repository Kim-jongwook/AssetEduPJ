package kr.co.kfs.assetedu.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Com02Code {
	@NotBlank(message = "공통 코드가 존재하지 않습니다.")
	private String com02ComCd; //공통코드
	
	@NotBlank(message = "세부 코드가 존재하지 않습니다.")
	private String com02DtlCd; //세부코드
	
	@NotBlank(message = "코드명이 존재하지 않습니다.")
	private String com02CodeNm; //코드명
	
	@NotBlank(message = "코드 구분이 존재하지 않습니다.")
	@Pattern(regexp = "[cd]", message = "c와 d만 입력이 가능합니다.")
	private String com02CodeType; //코드구분
	
	private Integer com02Seq; //순번
	private String com02UseYn; //사용여부
	private String com02Note; //비고
}
