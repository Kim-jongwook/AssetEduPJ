package kr.co.kfs.assetedu.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Fnd01Fund {
	
	@NotNull
	@NotBlank(message = "빈칸 미허용")
	private String fnd01FundCd; //펀드코드
	
	@NotNull
	@NotBlank(message = "빈칸 미허용")
	private String fnd01FundNm; //펀드명
	
	private String fnd01FundType; //펀드 유형
	
	private String fnd01PublicCd; // 공모,사모 코드
	
	private String fnd01UnitCd; //펀드단위코드
	
	private String fnd01ParentCd; //모자구분코드
	
	private String fnd01ParentFundCd; //모펀드 코드
	
	private String fnd01StartDate; // 설정일자
	
	private String fnd01EndDate; //해지일자
	
	private Integer fnd01AccPeriod; //회계기간
	
	private String fnd01FirstCloseDate; //최초결산일자
	
	private String fnd01CurCd; //기준통화
	
	private String fnd01KsdItemCd; //예탁원종목코드
	
	private String fnd01KofiaCd; //협회표준코드
	
	private String fnd01KofiaClassCd; //협회상품분류코드
	
	private String fnd01FssCd; //금감원펀드코드
	
	private String fnd01Manager; //주운용역
	
	private String fnd01SubManager; //부운용역
	
	private String fnd01MngCoCd; //운용사코드
	
	private String fnd01TrustCoCd; //수탁사코드
	
	private String fnd01OfficeCocd; //사무관리사(주)코드
	
	private String fnd01SubOfficeCoCd; //사무관리사(부)코드
	
}
