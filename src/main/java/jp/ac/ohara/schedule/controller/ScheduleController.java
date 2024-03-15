package jp.ac.ohara.schedule.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.ohara.schedule.model.ScheduleBook;
import jp.ac.ohara.schedule.model.UserBook;
import jp.ac.ohara.schedule.service.ScheduleBookService;
@Controller
public class ScheduleController {
	@Autowired
	private ScheduleBookService scheduleBookService;
	
	@GetMapping("/top/")
	public String index(Model model, @AuthenticationPrincipal UserBook userBook) {
		// TODO: model.addAttributeに一覧取得結果を追加
		model.addAttribute("list",this.scheduleBookService.getScheduleList(userBook));
		return "top";
	}
  @GetMapping("/add/")
  	public ModelAndView add(ScheduleBook scheduleBook, ModelAndView model) {
  		model.addObject("scheduleBook", scheduleBook);
  		model.setViewName("form");
  		return model;
  	}
  @GetMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable(name = "id") Long id, ScheduleBook scheduleBook, ModelAndView model) {
		model.addObject("scheduleBook",this.scheduleBookService.get(id));
		model.setViewName("detail");
		return model;
	}
  @GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable(name = "id") Long id, ScheduleBook scheduleBook, ModelAndView model) {
		this.scheduleBookService.delete(id);
		model.setViewName("delete");
		return model;
	}
  
  @PostMapping("/add/")
  	public String add(@Validated @ModelAttribute @NonNull ScheduleBook scheduleBook, RedirectAttributes result,
  			ModelAndView model,
  			RedirectAttributes redirectAttributes) {
  		try {
  			this.scheduleBookService.save(scheduleBook);
  			redirectAttributes.addFlashAttribute("exception", "");
   
  		} catch (Exception e) {
  			redirectAttributes.addFlashAttribute("exception", e.getMessage());
  		}
  		return "redirect:/top/";
  }
  @RequestMapping("/logout/")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null) {
          new SecurityContextLogoutHandler().logout(request, response, auth);
      }
      return "logout"; // Redirect to the home page after logout
  }
}
