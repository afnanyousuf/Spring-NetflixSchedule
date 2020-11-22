package ca.sheridancollege.yousuf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.yousuf.beans.ShowAppt;
import ca.sheridancollege.yousuf.database.DatabaseAccess;

@Controller
public class NetflixController {
	
	Model model;
	ShowAppt ShowAppt;
	
	
	@Autowired
	private DatabaseAccess da;
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("ShowAppt", new ShowAppt());
		model.addAttribute("apptList", da.getShows());
		return "index";
	}
	
	@GetMapping("/deleteAppointment/{id}")
	public String deleteAppointment(Model model, @PathVariable int id) {
		da.deleteAppointment(id);
		model.addAttribute("ShowAppt", new ShowAppt());
		model.addAttribute("apptList", da.getShows());
		return "index";
	}
	@GetMapping("/editAppointment/{id}")
	public String editAppointment(Model model, @PathVariable int id) {
		ShowAppt app = da.getApptById(id).get(0);
		model.addAttribute("ShowAppt", app);
		da.deleteAppointment(id);
		model.addAttribute("apptList", da.getShows());
		return "index";
	}
	
	@PostMapping("/insertAppointment")
	public String insertAppointment(Model model, @ModelAttribute ShowAppt app) {
		da.insertAppointment(app);
		model.addAttribute("ShowAppt", new ShowAppt());
		model.addAttribute("apptList", da.getShows());
		return "index";
	}
}
