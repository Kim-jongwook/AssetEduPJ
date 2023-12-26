package kr.co.kfs.assetedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Itm01Item;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.service.Com02CodeService;
import kr.co.kfs.assetedu.service.Itm01ItemService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/item")
public class ItemController {
	@Autowired
	Itm01ItemService itm01ItemService;
	@Autowired
	Com02CodeService com02CodeService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(@RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize,
					   @RequestParam(value = "currentPageNumber", required = false, defaultValue = "1")Integer currentPageNumber,
					   String searchText,
					   Model model) {
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		log.debug("주식종목정보리스트");
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		Long totalCount = itm01ItemService.totalCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr: {}", pageAttr);
		condition.put("pageAttr", pageAttr);
		List<Itm01Item> list = itm01ItemService.selectList(condition);
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		return "/item/list";
	}
	
	@GetMapping("insert")
	public String insert(@ModelAttribute("item")Itm01Item item, Model model) {
		log.debug("신규 종목 등록 form");
		
		model.addAttribute("stkListTypeList", com02CodeService.codeList("ListType"));
		model.addAttribute("marketTypeList", com02CodeService.codeList("MarketType"));
		model.addAttribute("stkTypeList", com02CodeService.codeList("StkType"));
		return "/item/insert_form";
	}
}
