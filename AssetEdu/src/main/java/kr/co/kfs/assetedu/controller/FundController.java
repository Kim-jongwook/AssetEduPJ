package kr.co.kfs.assetedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Fnd01Fund;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.service.Fnd01FundService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/fund")
public class FundController {
	@Autowired
	Fnd01FundService fnd01FundService;

	@GetMapping("list")
	public String list(@RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize
						, @RequestParam(value = "currentPageNumber", required = false, defaultValue = "1")Integer currentPageNumber
						, String searchText
						, Model model) {
		log.debug("펀드 리스트");
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		Long totalCount = fnd01FundService.totalCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr:{}", pageAttr);
		condition.put("pageAttr", pageAttr);
		List<Fnd01Fund> list = fnd01FundService.selectList(condition);
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		return "/fund/list";
	}
}
