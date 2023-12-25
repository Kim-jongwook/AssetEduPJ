package kr.co.kfs.assetedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("corpTypeList", com02CodeService.codeList("CorpType"));
		log.debug("corpTypeList: {}", model.getAttribute("corpTypeList"));
		return "/corp/insert_form";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("corp")Com01Corp corp, BindingResult result
						, RedirectAttributes redirectAttributes, Model model) {
		log.debug("기관 정보를 테이블에 저장하고 리스트로 이동함");
		log.debug("corp: {}", corp);
		
		if(result.hasErrors()) {
			model.addAttribute("corpTypeList", com02CodeService.codeList("CorpType"));
			return "/corp/insert_form";
		}else {
			String msg;
			Com01Corp checkCorp = com01CorpService.selectOne(corp);
			if(checkCorp != null) {
				msg = String.format("\"%s\" 기관코드는 이미 \"%s\" 으로 등록되어 있습니다.", corp.getCom01CorpCd(), checkCorp.getCom01CorpNm());
				result.addError(new FieldError("", "", msg));
				model.addAttribute("corpTypeList", com02CodeService.codeList("CorpType"));
				return "/corp/insert_form";
			}else {
				int i = com01CorpService.insert(corp);
				log.debug("DB에 적용된 갯수: {}", i);
				msg = String.format("\"%s\" 기관 정보가 등록되었습니다.", corp.getCom01CorpNm());
				redirectAttributes.addAttribute("mode", "insert");
				redirectAttributes.addAttribute("msg", msg);
				redirectAttributes.addAttribute("pageTitle", "기관정보등록");
				return "redirect:/corp/success";
			}
		}
	}
	
	@GetMapping("update")
	public String update(@ModelAttribute(name = "corp")Com01Corp corp, Model model) {
		log.debug("기관정보 수정 페이지 진입");
		corp = com01CorpService.selectOne(corp);
		model.addAttribute("corp", corp);
		model.addAttribute("corpTypeList", com02CodeService.codeList("CorpType"));
		return "/corp/update_form";
	}
	
	@PostMapping("update")
	public String update(@ModelAttribute(name = "corp")Com01Corp corp, Model model, RedirectAttributes redirectAttributes) {
		log.debug("기관정보 수정 폼 제출");
		log.debug("corp: {}", corp);
		int i = com01CorpService.update(corp);
		log.debug("수정된 행의 개수: {}", i);
		String msg = String.format("\"%s\" 기관정보가 수정되었습니다.", corp.getCom01CorpNm());
		redirectAttributes.addAttribute("mode", "update");
		redirectAttributes.addAttribute("msg", msg);
		redirectAttributes.addAttribute("pageTitle", "기관정보수정");
		redirectAttributes.addAttribute("Com01CorpCd", corp.getCom01CorpCd());
		return "redirect:/corp/success";
	}
	
	@GetMapping("success")
	public String success(String msg, String mode, String pageTitle, String Com01CorpCd, Model model) {
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("msg", msg);
		model.addAttribute("mode", mode);
		model.addAttribute("Com01CorpCd", Com01CorpCd);
		log.debug("success");
		
		return "/corp/success";
	}
	
	@GetMapping("delete")
	public void delete(@RequestParam("com01CorpCd") String corpCd, HttpServletResponse response) throws IOException {
		log.debug("기관정보 삭제");
		int i = com01CorpService.delete(corpCd);
		log.debug("삭제된 행의 개수: {}", i);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
	    out.println("<script>");
	    out.println("alert('삭제되었습니다.');");
	    out.println("window.location.href='/corp/list';");
	    out.println("</script>");
	    
	}
}





















