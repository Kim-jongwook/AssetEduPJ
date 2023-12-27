package kr.co.kfs.assetedu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute(value = "item")Itm01Item item, BindingResult result
						, RedirectAttributes redirectAttributes) {
		log.debug("신규 종목 등록 form 제출");
		log.debug("item: {}", item);
		int i = itm01ItemService.insert(item);
		log.debug("DB에 적용된 개수: {}", i);
		String msg = String.format("\"%s\" 종목이 등록되었습니다. ", item.getItm01ItemNm());
		redirectAttributes.addAttribute("msg", msg);
		redirectAttributes.addAttribute("mode", "insert");
		redirectAttributes.addAttribute("pageTitle", "주식정보등록");
		redirectAttributes.addAttribute("itm01ItemCd", item.getItm01ItemCd());
		return "redirect:/item/success";
	}
	
	@RequestMapping("success")
	public String success(String msg, String pageTitle, String mode, String itm01ItemCd, Model model) {
		model.addAttribute("msg", msg);
		model.addAttribute("mode", mode);
		model.addAttribute("itm01ItemCd", itm01ItemCd);
		model.addAttribute("pageTitle", pageTitle);
		
		return "/item/success";
	}
	
	@GetMapping("update")
	public String update(@ModelAttribute("item")Itm01Item item, Model model) {
		log.debug("종목 수정 form");
		item = itm01ItemService.selectOne(item);
		model.addAttribute("item", item);
		model.addAttribute("stkListTypeList", com02CodeService.codeList("ListType"));
		model.addAttribute("marketTypeList", com02CodeService.codeList("MarketType"));
		model.addAttribute("stkTypeList", com02CodeService.codeList("StkType"));
		return "/item/update_form";
	}
	
	@PostMapping("update")
	public String update(@Valid @ModelAttribute("item")Itm01Item item, Model model
						,RedirectAttributes redirectAttributes) {
		log.debug("종목 수정 form 제출");
		int i = itm01ItemService.update(item);
		log.debug("DB에 적용된 갯수: {}", i);
		String msg= String.format("\"%s\"종목이 수정되었습니다.", item.getItm01ItemNm());
		redirectAttributes.addAttribute("msg", msg);
		redirectAttributes.addAttribute("mode", "update");
		redirectAttributes.addAttribute("pageTitle", "주식정보수정");
		redirectAttributes.addAttribute("itm01ItemCd", item.getItm01ItemCd());
		
		return "redirect:/item/success";
	}
	
	@GetMapping("delete")
	public String delete(@ModelAttribute("item")Itm01Item item) {
		log.debug("종목 삭제");
		log.debug("종목정보삭제 삭제할 종목코드 :" + item.getItm01ItemCd());
		int i = itm01ItemService.delete(item);
		log.debug("DB에 적용된 갯수: {}", i);
		return "redirect:/item/list";
	}
	
}
































