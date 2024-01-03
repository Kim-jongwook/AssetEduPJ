package kr.co.kfs.assetedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kfs.assetedu.model.Com01Corp;
import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Fnd01Fund;
import kr.co.kfs.assetedu.model.Jnl10Acnt;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.service.Com01CorpService;
import kr.co.kfs.assetedu.service.Fnd01FundService;
import kr.co.kfs.assetedu.service.Jnl10AcntService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/popup")
public class PopupController {
	@Autowired
	Com01CorpService com01CorpService;
	@Autowired
	Fnd01FundService fnd01FundService;
	@Autowired
	Jnl10AcntService jnl10AcntService;
	
	@GetMapping("corp")
	public String popupCorp(@RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize
							,@RequestParam(value = "currentPageNumber", required = false, defaultValue = "1")Integer currentPageNumber
							,Model model
							,String searchText
							,String corpCd
							,String corpNm
							,String com01CorpType) {
		log.debug("기관팝업");
		log.debug("searchText : {}, corpCd : {}, corpNm: {}", searchText, corpCd, corpNm);
		log.debug("currentPageNumber : {}, pageSize : {}", currentPageNumber, pageSize);
		
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		condition.put("com01CorpType", com01CorpType);
		Long totalCount = com01CorpService.selectCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr : {}", pageAttr);
		condition.put("pageAttr", pageAttr);

		List<Com01Corp> list = com01CorpService.selectList(condition);
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		return "/common/popup_corp";
	}
	
	@GetMapping("fund")
	public String popupFund(@RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize
							, @RequestParam(value = "currentPageNumber", required = false, defaultValue = "1")Integer currentPageNumber
							, Model model
							, String searchText
							, String fundCd
							, String fundNm
							, String parentYn) {
		log.debug("펀드팝업");
		log.debug("searchText : {}, fundCd : {}, fundNm: {}, parentYn: {}", searchText, fundCd, fundNm, parentYn);
		log.debug("currentPageNumber : {}, pageSize : {}", currentPageNumber, pageSize);
		
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		condition.put("parentYn", parentYn);
		Long totalCount = fnd01FundService.totalCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr:{}", pageAttr);

		condition.put("pageAttr", pageAttr);
		List<Fnd01Fund> list = fnd01FundService.selectList(condition);
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		return "/common/popup_fund";
	}
	
	@GetMapping("jnl/acnt/parent")
	public String popupAcnt(@RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize
						   , @RequestParam(value = "currentPageNumber", required = false, defaultValue = "1")Integer currentPageNumber
						   , String searchText
						   , String acntCd
						   , String acntNm
						   , String parentYn
						   , Model model) {
		log.debug("계정과목팝업");
		log.debug("searchText: {}, acntCd: {}, acntNm: {}, parentYn: {}, model: {}", searchText, acntCd, acntNm, parentYn, model);
		log.debug("currentPageNumber : {}, pageSize : {}", currentPageNumber, pageSize);

		Condition condition = new Condition();
		condition.put("searchText", searchText);
		condition.put("parentYn", parentYn);
		Long totalCount = jnl10AcntService.totalCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr:{}", pageAttr);
		condition.put("pageAttr", pageAttr);
		List<Jnl10Acnt> list = jnl10AcntService.list(condition);
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		return "/common/popup_jnl_acnt";
	}
}
