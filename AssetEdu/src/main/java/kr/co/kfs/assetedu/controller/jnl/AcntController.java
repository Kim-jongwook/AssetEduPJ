package kr.co.kfs.assetedu.controller.jnl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Jnl10Acnt;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.service.Com02CodeService;
import kr.co.kfs.assetedu.service.Jnl10AcntService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/jnl/acnt")
public class AcntController {
	@Autowired
	Jnl10AcntService jnl10AcntService;
	@Autowired
	Com02CodeService com02CodeService;
	
	@GetMapping("list")
	public String list(@RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer PageSize
					 , @RequestParam(value = "currentPageNumber", required = false, defaultValue = "1")Integer currentPageNumber
					 , String searchText
					 , Model model) {
		log.debug("계정과목리스트");
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		Long totalCount = jnl10AcntService.totalCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, PageSize, currentPageNumber);
		log.debug("pageAttr: {}", pageAttr);
		condition.put("pageAttr", pageAttr);
		List<Jnl10Acnt> list = jnl10AcntService.list(condition);
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		return "/jnl/acnt/list";
	}
	
	@GetMapping("insert")
	public String insert(@ModelAttribute("jnl10Acnt")Jnl10Acnt acnt, Model model) {
		model.addAttribute("pageTitle", "계정과목-등록");
		model.addAttribute("acntAttrCodeList", com02CodeService.codeList("AcntAttrCode"));
		model.addAttribute("drcrTypeList", com02CodeService.codeList("DrcrType"));
		return "/jnl/acnt/insert_form";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("jnl10Acnt")Jnl10Acnt acnt 
						, BindingResult result 
						, Model model
						, RedirectAttributes redirectAttributes) {
		log.debug("계정과목 폼 제출");
		log.debug("acnt: {}", acnt);
		if(result.hasErrors()) {
			model.addAttribute("pageTitle", "계정과목-등록");
			model.addAttribute("acntAttrCodeList", com02CodeService.codeList("AcntAttrCode"));
			model.addAttribute("drcrTypeList", com02CodeService.codeList("DrcrType"));
			return "/jnl/acnt/insert_form";
		}else {
			String msg;
			Jnl10Acnt checkAcnt = jnl10AcntService.selectOne(acnt);
			if(checkAcnt != null) {
				msg= String.format(" \"%s\" 계정코드는 이미 \"%s\"로 등록되어 있습니다.", acnt.getJnl10AcntCd(), checkAcnt.getJnl10AcntNm());
				result.addError(new FieldError("", "", msg));
				model.addAttribute("pageTitle", "계정과목-등록");
				model.addAttribute("acntAttrCodeList", com02CodeService.codeList("AcntAttrCode"));
				model.addAttribute("drcrTypeList", com02CodeService.codeList("DrcrType"));
				return "/jnl/acnt/insert_form";
			}else {
				int i = jnl10AcntService.insert(acnt);
				log.debug("DB에 적용된 개수: {}", i);
				msg= String.format(" \"%s\" 계정 과목이 등록되었습니다.", acnt.getJnl10AcntNm());
				redirectAttributes.addAttribute("pageTitle", "계정정보 등록");
				redirectAttributes.addAttribute("msg", msg);
				redirectAttributes.addAttribute("jnl10AcntCd", acnt.getJnl10AcntCd());
				redirectAttributes.addAttribute("mode", "insert");
				return "redirect:/jnl/acnt/success";
			}
		}
	}
	
	@GetMapping("success")
	public String success(String msg, String mode, String pageTitle, String jnl10AcntCd, Model model) {
		model.addAttribute("msg", msg);
		model.addAttribute("mode", mode);
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("jnl10AcntCd", jnl10AcntCd);		
		log.debug("success 진입");
		log.debug("msg: {}, mode: {}, pageTitle: {}, jnl10AcntCd: {}, model: {}", msg, mode, pageTitle, jnl10AcntCd, model);
		
		return "jnl/acnt/success";
	}
	
	@GetMapping("/update/{jnl10AcntCd}")
	public String update(@PathVariable(value = "jnl10AcntCd")String jnl10AcntCd, Model model,String parentYn) {
		log.debug("update form 진입");
		Jnl10Acnt jnl10Acnt = new Jnl10Acnt();
		jnl10Acnt.setJnl10AcntCd(jnl10AcntCd);
		jnl10Acnt = jnl10AcntService.selectOne(jnl10Acnt);
		model.addAttribute("jnl10Acnt", jnl10Acnt);
		model.addAttribute("pageTitle", "계정과목-수정");
		model.addAttribute("acntAttrCodeList", com02CodeService.codeList("AcntAttrCode"));
		model.addAttribute("drcrTypeList", com02CodeService.codeList("DrcrType"));
		log.debug("jnl10Acnt: {}, model: {}", jnl10Acnt, model);
		return "jnl/acnt/update_form";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
