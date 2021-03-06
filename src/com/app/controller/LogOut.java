package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.service.AdminPageAllInfo;

/**
 * The LogOut class allows the user to logout.
 */
@Controller
public class LogOut {
	
	@Autowired
	AdminPageAllInfo info;
	
	/**
	 *  logout method provides user to see his default address.
	 * @param session of type HttpSession. It is used to provide all the required session scope variables
	 */
	
	@RequestMapping(value="/logout" ,  method=RequestMethod.GET , produces = MediaType.TEXT_HTML_VALUE)
	public String logout(HttpSession session)
	{
		info.removeUserOnline((int)session.getAttribute("userID"));
		session.invalidate();
		return "redirect:index.html";
	}
}
