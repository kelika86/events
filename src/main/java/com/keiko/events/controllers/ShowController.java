package com.keiko.events.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keiko.events.models.Event;
import com.keiko.events.models.Message;
import com.keiko.events.models.User;
import com.keiko.events.services.EventService;
import com.keiko.events.services.MessageService;
import com.keiko.events.services.UserService;

@Controller
public class ShowController {
	private final EventService eventService;
	private final UserService userService;
	private final MessageService messageService;
	
	public ShowController(EventService eventService, UserService userService, MessageService messageService) {
		this.eventService = eventService;
		this.userService = userService;
		this.messageService = messageService;
	}
	
	@RequestMapping("/show/{id}")
	public String show(HttpSession session, @PathVariable("id") Long id, Model model, @ModelAttribute("newcomment") Message message) {
		Event eventshow = eventService.findEvent(id);
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("event",eventshow);
		model.addAttribute("messageboard", eventshow.getMessages());
		model.addAttribute("eventuser", eventshow.getUsers());
		System.out.println(eventshow.getUsers());
		return "show.jsp";
	}
	@RequestMapping(value="/newcomment", method=RequestMethod.POST)
	public String comment(@Valid @ModelAttribute("newcomment") Message message, BindingResult result, @RequestParam("event") Long id) {
		if(result.hasErrors()) {
			return "show.jsp";
		} else {
			messageService.createMessage(message);
			return "redirect:/show/"+id;
		}
	}
}
