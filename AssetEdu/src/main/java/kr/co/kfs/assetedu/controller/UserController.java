package kr.co.kfs.assetedu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.model.Sys01User;
import kr.co.kfs.assetedu.service.Sys01UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {
	@Autowired
	Sys01UserService sys01UserService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("list")
	public String list(@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			   		   @RequestParam(value = "currentPageNumber", required = false, defaultValue = "1") Integer currentPageNumber,
			   		   String searchText, 
			   		   Model model) {
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		log.debug("사용자리스트");
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		Condition condition = new Condition();
		condition.put("searchText", searchText);
		Long totalCount = sys01UserService.totalCount(condition);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr: {}", pageAttr);
		condition.put("pageAttr", pageAttr);
		List<Sys01User> list = sys01UserService.selectList(condition);
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		return "/admin/user/list";
	}

	@RequestMapping("insert")
	public String insert(Model model) {
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		log.debug("사용자 등록");
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		model.addAttribute("pageTitle", "사용자추가");
		model.addAttribute("user", new Sys01User());

		return "/admin/user/insert_form";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute Sys01User user, RedirectAttributes redirectAttr) {
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		log.debug("사용자 정보를 테이블에 저장하고 리스트로 이동");
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		log.debug("user: {}", user);
		String pwd = user.getSys01Pwd();
		user.setSys01Pwd(passwordEncoder.encode(pwd));
		int affectedCount = sys01UserService.insert(user);
		log.debug("DB에 적용된 갯수 : {}", affectedCount);
		String msg = String.format("사용자 %s님이 추가되었습니다.", user.getSys01UserNm());
		redirectAttr.addAttribute("mode", "insert");
		redirectAttr.addAttribute("msg", msg);

		return "redirect:/admin/user/success";
	}

	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String success(String msg, String mode, String userId, Model model) {
		model.addAttribute("pageTitle", "사용자추가");
		model.addAttribute("msg", msg);
		model.addAttribute("mode", mode);
		model.addAttribute("userId", userId);

		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		log.debug("사용자 추가 화면");
		log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");

		return "/admin/user/success";
	}
	
	@GetMapping("update")
	public String update(@ModelAttribute("user") Sys01User user, Model model) {
		log.debug("★★★★★☆☆☆☆☆☆★★★★★☆☆☆☆☆☆★★★★★☆☆☆☆☆☆★★★★★☆☆☆☆☆☆");
		log.debug("사용자정보 수정 form 표시");
		log.debug("user = {}", user);
		log.debug("★★★★★☆☆☆☆☆☆★★★★★☆☆☆☆☆☆★★★★★☆☆☆☆☆☆★★★★★☆☆☆☆☆☆");
		user =  sys01UserService.selectOne(user);
		model.addAttribute("user", user); /* 이 라인을 comment처리한 후 결과를 확인해 보세요 */
		return "/admin/user/update_form";
	}
	
	@PostMapping("update")
	public String update(@Valid @ModelAttribute Sys01User user, RedirectAttributes redirectAttr) {
		int updateCount = sys01UserService.update(user);
		String msg = String.format("사용자 %s님이 수정되었습니다.", user.getSys01UserNm());
		redirectAttr.addAttribute("mode", "insert");
		redirectAttr.addAttribute("msg", msg);
		return "redirect:/admin/user/success";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam String userId, HttpServletRequest request) {
		printParameters(request);
		int i = sys01UserService.delete(userId);
		log.debug("삭제된 행의 개수 = {}", i);
		
		return "redirect:/admin/user/list";

	}
	
	@ResponseBody
	@PostMapping("passwordMatch")
	public String passwordMatch(@RequestParam String userId, @RequestParam String userPw) {
		int i = sys01UserService.passwordMatch(userId, userPw);
		if(i == 1) {
			return "비밀번호 일치";
		}else {
			return "비밀번호 불일치";
		}
	}
}
















