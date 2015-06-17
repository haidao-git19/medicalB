package com.netbull.shop.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Log logger = LogFactory.getLog(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		return "index";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {

		return "redirect:/";
	}


	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "login";
	}

	@RequestMapping(value = "/menu.do", method = RequestMethod.GET)
	public String menu() {

		return "menu";
	}

	@RequestMapping(value = "/dashboard.do", method = RequestMethod.GET)
	public String dashboard() {

		return "dashboard";
	}

	@RequestMapping(value = "/top.do", method = RequestMethod.GET)
	public String top() {

		return "top";
	}

	@RequestMapping(value = "/switchImg.do", method = RequestMethod.GET)
	public String switchImg() {

		return "centerBar";
	}

	@RequestMapping(value = "/topBar.do", method = RequestMethod.GET)
	public String topBar() {

		return "topBar";
	}

	@RequestMapping(value = "/bottom.do", method = RequestMethod.GET)
	public String bottom() {

		return "bottom";
	}

}
