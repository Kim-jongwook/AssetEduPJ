package kr.co.kfs.assetedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kfs.assetedu.model.Com02Code;
import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.service.Com02CodeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/code")
public class CodeController {
	
	@Autowired
	private Com02CodeService service;
	
	@GetMapping("list")
	public String list(@RequestParam(value = "pageSize", required = false, defaultValue = "10" )Integer pageSize,
					   @RequestParam(value = "currentPageNumber", required = false, defaultValue = "1")Integer currentPageNumber,
					   String searchText, 
					   Model model) {
		log.debug("공통코드 리스트 ");

		Condition condition = new Condition();
		condition.put("searchText", searchText);
		Long totalCount = service.totalCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr: {}", pageAttr);
		condition.put("pageAttr", pageAttr);
		List<Com02Code> listCategory = selectComCds(condition);
		model.addAttribute("pageAttr", pageAttr);
		model.addAttribute("listCategory", listCategory);
		return "/code/list";
	}

	/**
	 * 공통코드 리스트
	 * @param condition
	 * @return
	 */
	private  List<Com02Code> selectComCds(Condition condition) {
		Com02Code code = new Com02Code();
		code.setCom02CodeType("C");
		//code.setCom02UseYn("true");
		condition.putClass(code);
		List<Com02Code>list = service.selectList(condition);
		return list;
	}		
}
