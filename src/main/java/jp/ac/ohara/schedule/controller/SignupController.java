package jp.ac.ohara.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.ac.ohara.schedule.model.SignupForm;
import jp.ac.ohara.schedule.model.UserBook;
import jp.ac.ohara.schedule.service.UserBookService;

@Controller
public class SignupController {
	@Autowired
	UserBookService userBookService;
	@GetMapping("/signup/")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
		model.addAttribute("signupForm", form);
		return "signup";
	}

	@GetMapping("/logincomplete/")
	public String getSignupComplete(@ModelAttribute SignupForm form, Model model) {
		model.addAttribute("signupForm", form);
		return "logincomplete";
	}

	@PostMapping("/signup/")
	public String postSignUp(@ModelAttribute @Validated UserBook userBook, SignupForm form, BindingResult bindingResult, Model model) {
		this.userBookService.save(userBook);
		//入力を失敗した場合、ユーザー登録画面に戻る
		if (bindingResult.hasErrors()) {
			return getSignUp(form, model);
		}
		//formの中身をコンソールに表示
		System.out.println(form);
		return "redirect:/logincomplete/";
	}
}