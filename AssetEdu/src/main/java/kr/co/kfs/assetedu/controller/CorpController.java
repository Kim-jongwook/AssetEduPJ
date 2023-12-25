package kr.co.kfs.assetedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kfs.assetedu.model.Com01Corp;
import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.service.Com01CorpService;
import kr.co.kfs.assetedu.service.Com02CodeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/corp")
public class CorpController {
	@Autowired
	Com01CorpService com01CorpService;
	@Autowired
	Com02CodeService com02CodeService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
					   @RequestParam(value = "currentPageNumber", required = false, defaultValue = "1") Integer currentPageNumber,
					   String searchText, Model model) {
		log.debug("기관정보 리스트");
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		Long totalCount = com01CorpService.selectCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr:{}", pageAttr);
		condition.put("pageAttr", pageAttr);
		List<Com01Corp> listCategory = com01CorpService.selectList(condition);
		model.addAttribute("list", listCategory);
		model.addAttribute("pageAttr", pageAttr);
		return "/corp/list";
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert(Model model) {
		log.debug("기관정보 등록");
		model.addAttribute("corp", new Com01Corp());
		return "/corp/insert_form";
	}
}
