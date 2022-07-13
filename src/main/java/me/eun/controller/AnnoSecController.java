package me.eun.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import me.eun.model.MemberVO;
import me.eun.security.CustomUser;

@Controller
public class AnnoSecController {

	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
	@GetMapping("/anno/member")
	public String memberPage() {
		return "member/member";
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
//	@GetMapping("/anno/myPage")
//	public String myPage(@AuthenticationPrincipal MemberVO vo) {
//		System.out.println(vo.getUserId());
//		return "myPage";
//		
		
//	}
	
	//마이페이지
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/anno/myPage/{userId}")
	public String myPage(@PathVariable String userId ,
						 @AuthenticationPrincipal CustomUser customUser ,  Model model) {
		MemberVO vo = customUser.getMemberVO();
		if(!vo.getUserId().equals(userId)) {
			System.out.println("예외발생");
			throw new NotMatchUserIdException();
		}
		
		model.addAttribute("member", vo);
		return "member/myPage"; 
	}
}
