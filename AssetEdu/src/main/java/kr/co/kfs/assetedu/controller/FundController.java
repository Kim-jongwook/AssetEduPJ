package kr.co.kfs.assetedu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Fnd01Fund;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.service.Com02CodeService;
import kr.co.kfs.assetedu.service.Fnd01FundService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/fund")
public class FundController {
	@Autowired
	Fnd01FundService fnd01FundService;
	@Autowired
	Com02CodeService com02CodeService;

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
	
	@GetMapping("insert")
	public String insert(@ModelAttribute("fund")Fnd01Fund fund, Model model) {
		model.addAttribute("fundTypeList", com02CodeService.codeList("FundType"));
		model.addAttribute("publicCdList", com02CodeService.codeList("PublicCode"));
		model.addAttribute("unitCdList"  , com02CodeService.codeList("FundUnitCode"));
		model.addAttribute("parentCdList", com02CodeService.codeList("FundParentCode"));
		return "/fund/insert_form";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("fund")Fnd01Fund fund
						 , BindingResult result
						 , RedirectAttributes redirectAttributes
						 , Model model) {
		log.debug("펀드 insert 폼 제출");
		log.debug("fund: {}", fund);
		
		if(result.hasErrors()) {
			model.addAttribute("fundTypeList", com02CodeService.codeList("FundType"));
			model.addAttribute("publicCdList", com02CodeService.codeList("PublicCode"));
			model.addAttribute("unitCdList"  , com02CodeService.codeList("FundUnitCode"));
			model.addAttribute("parentCdList", com02CodeService.codeList("FundParentCode"));
			return "/fund/insert_form";
		}
		
		String msg;
		
		Fnd01Fund checkFund = fnd01FundService.selectOne(fund);
		if(checkFund != null) {
			msg = String.format(" \"%s\" 펀드 코드는 이미 \"%s\"으로 등록되어 있습니다. ", fund.getFnd01FundCd(), checkFund.getFnd01FundNm());
			result.addError(new FieldError("", "", msg));
			model.addAttribute("fundTypeList", com02CodeService.codeList("FundType"));
			model.addAttribute("publicCdList", com02CodeService.codeList("PublicCode"));
			model.addAttribute("unitCdList"  , com02CodeService.codeList("FundUnitCode"));
			model.addAttribute("parentCdList", com02CodeService.codeList("FundParentCode"));
			return "/fund/insert_form";
		}else {
			int i = fnd01FundService.insert(fund);
			log.debug("Db에 적용된 개수: {}", i);
			
			msg = String.format("\"%s\" 펀드가 등록되었습니다", fund.getFnd01FundNm());
			redirectAttributes.addAttribute("mode", "insert");
			redirectAttributes.addAttribute("msg" , msg);
			redirectAttributes.addAttribute("pageTitle" , "펀드정보등록");
			redirectAttributes.addAttribute("fund01FundCd", fund.getFnd01FundCd());
			return "redirect:/fund/success";
		}
	}
	
	@GetMapping("success")
	public String success(String msg, String mode, String pageTitle, String fund01FundCd, Model model) {
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("msg", msg);
		model.addAttribute("mode", mode);
		model.addAttribute("fund01FundCd", fund01FundCd);
		log.debug("success");
		log.debug("msg: {}, mode: {}, pageTitle: {}, fund01FundCd:{}, model:{}", msg, mode, pageTitle, fund01FundCd, model);
		return "/fund/success";
	}
	
	@GetMapping("update")
	public String update(@ModelAttribute("fund")Fnd01Fund fund, Model model) {
		log.debug("펀드 업데이트폼");
		fund = fnd01FundService.selectOne(fund);
		
		model.addAttribute("fund", fund);
		model.addAttribute("fundTypeList", com02CodeService.codeList("FundType"));
		model.addAttribute("publicCdList", com02CodeService.codeList("PublicCode"));
		model.addAttribute("unitCdList"  , com02CodeService.codeList("FundUnitCode"));
		model.addAttribute("parentCdList", com02CodeService.codeList("FundParentCode"));
		return "/fund/update_form";
	}
	
	@PostMapping("update")
	public String updateSubmit(@Valid @ModelAttribute("fund")Fnd01Fund fund, BindingResult result 
								, Model model
								, RedirectAttributes redirectAttributes) {
		log.debug("펀드 업데이트 폼 제출");
		log.debug("fund: {}", fund);
		if(result.hasErrors()) {
			model.addAttribute("fundTypeList", com02CodeService.codeList("FundType"));
			model.addAttribute("publicCdList", com02CodeService.codeList("PublicCode"));
			model.addAttribute("unitCdList"  , com02CodeService.codeList("FundUnitCode"));
			model.addAttribute("parentCdList", com02CodeService.codeList("FundParentCode"));
			return "/fund/update_form";
		}else {
			int i = fnd01FundService.update(fund);
			String msg;
			msg = String.format(" \"%s\" 펀드가 수정되었습니다.", fund.getFnd01FundNm());
			redirectAttributes.addAttribute("msg", msg);
			redirectAttributes.addAttribute("pageTitle", "펀드정보수정");
			redirectAttributes.addAttribute("mode", "update");
			redirectAttributes.addAttribute("fund01FundCd", fund.getFnd01FundCd());
			
			log.debug("redirectAttributes: {}", redirectAttributes);
			
			return "redirect:/fund/success";
		}
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam(value = "fnd01FundCd")String fnd01FundCd) {
		log.debug("종목 삭제");
		log.debug("fnd01FundCd: {}", fnd01FundCd);
		int i = fnd01FundService.delete(fnd01FundCd);
		log.debug("DB에 적용된 갯수: {}", i);
		return "redirect:/fund/list";
	}
}
















