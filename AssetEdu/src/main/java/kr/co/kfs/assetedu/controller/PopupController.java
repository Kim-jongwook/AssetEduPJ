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
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.service.Com01CorpService;
import kr.co.kfs.assetedu.service.Fnd01FundService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/popup")
public class PopupController {
	@Autowired
	Com01CorpService com01CorpService;
	@Autowired
	Fnd01FundService fnd01FundService;
	
	@GetMapping("corp")
	public String popupCorp(@RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize
							,@RequestParam(value = "currentPageNumber", required = false, defaultValue = "1")Integer currentPageNumber
							,Model model
							,String searchText) {
		log.debug("기관팝업");
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		Long totalCount = com01CorpService.selectCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
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
							, String searchText) {
		log.debug("펀드팝업");
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		Long totalCount = fund
		
		return "";
	}
}
