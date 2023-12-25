package kr.co.kfs.assetedu.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Com01Corp {
	
	@NotBlank(message="필수 입력란 입니다.(기관코드)")
	private String com01CorpCd; //기관코드

	@NotBlank(message="필수 입력란 입니다.(기관구분)")
	private String com01CorpType; //기관구분

	@NotBlank(message="필수 입력란 입니다.(기관명)")
	private String com01CorpNm; //기관명

	private String com01CorpEnm; //기관영문명
	
	@Pattern(regexp = "^[a-zA-z0-9]{5}$", message = "대외 기관코드는 숫자와 영문자로 이루어진 5글자여야 합니다.")
	private String com01ExtnCorpCd; //대외기관코드
	
	private String com01CorpTypeNm; //기관구분명
	
}
