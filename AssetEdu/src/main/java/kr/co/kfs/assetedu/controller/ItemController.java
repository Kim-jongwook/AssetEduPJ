package kr.co.kfs.assetedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Itm01Item;
import kr.co.kfs.assetedu.service.Itm01ItemService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/item")
public class ItemController {
	@Autowired
	Itm01ItemService itm01ItemService;
	
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
		List<Itm01Item> list = itm01ItemService.selectList(condition);
		model.addAttribute("list", list);
		return "/item/list";
	}
}
