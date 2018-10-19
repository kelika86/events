package com.keiko.events.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.keiko.events.models.State;
import com.keiko.events.models.User;
import com.keiko.events.services.EventService;
import com.keiko.events.services.UserService;

@Controller
public class DashboardController {
	private final EventService eventService;
	private final UserService userService;
	
	public DashboardController(EventService eventService, UserService userService) {
	     this.eventService = eventService;
	     this.userService = userService;
	 }
	
	 @RequestMapping("/dashboard")
	 public String home(HttpSession session, Model model, @ModelAttribute("newevent") Event event) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		List<State> newustate = userService.allState();
	    model.addAttribute("states", newustate);
        model.addAttribute("user", user);
       // model.addAttribute("event", eventService.inMyState(user.getState().getState()));
        //model.addAttribute("eventout", eventService.NotMyState(user.getState().getState()));
        return "dashboard.jsp";
	 }
	 @RequestMapping("/logout")
	 public String logout(HttpSession session) {
	     session.invalidate();
	     return "redirect:/";
	 }
	 @RequestMapping("/newevent")
	 public String newevent(@Valid @ModelAttribute("newevent") Event event, BindingResult result, HttpSession session, @RequestParam("eventdate") String date) {
		 if(result.hasErrors()) {
			 return "dashboard.jsp";
		 } else {
			 SimpleDateFormat newdate = new SimpleDateFormat("yyyy-MM-dd");
			 try {
				Date createdate = newdate.parse(date);
				event.setDate(createdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 eventService.createEvent(event);
			 return "redirect:/dashboard";
		 }
	 }
	 @RequestMapping("/edit/{id}")
	 public String edit(@PathVariable("id") Long id, Model model, @ModelAttribute("editevent") Event event) {
		 Event eventedit = eventService.findEvent(id);
		 List<State> editustate = userService.allState();
		 model.addAttribute("states", editustate);
		 model.addAttribute("event", eventedit);
		 return "edit.jsp";
	 }
	 @RequestMapping(value="/edit/{id}", method=RequestMethod.PUT)
	 public String update(@Valid @ModelAttribute("editevent") Event event, BindingResult result, @RequestParam("eventdate") String date) {
		 if (result.hasErrors()) {
			 return "edit.jsp";
		 } else {
			 SimpleDateFormat newdate = new SimpleDateFormat("yyyy-MM-dd");
			 try {
				Date createdate = newdate.parse(date);
				event.setDate(createdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 eventService.updateEvent(event);
			 return "redirect:/dashboard";
		 }
	 }
	 @RequestMapping(value="/delete/{id}")
	 public String destory(@PathVariable("id") Long id) {
		 eventService.deleteEvent(id);
		 return "redirect:/dashboard";
	 }
	 @RequestMapping("/join/{id}")
	 public String join(HttpSession session, @PathVariable("id") Long id) {
		 Long userId = (Long) session.getAttribute("userId");
		 User user = userService.findUserById(userId);
		 Event event = eventService.findEvent(id);
		 event.getUsers().add(user);
		 eventService.updateEvent(event);
		 return "redirect:/dashboard";
	 }
	 @RequestMapping("/cancel/{id}")
	 public String cancel(HttpSession session, @PathVariable("id") Long id) {
	     Long userId = (Long) session.getAttribute("userId");
	     User user = userService.findUserById(userId);
	     Event event = eventService.findEvent(id);
	     event.getUsers().remove(user);
		 eventService.updateEvent(event);
	     return "redirect:/dashboard";
	 }
}
