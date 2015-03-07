package demo.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.OrderService;
import demo.model.Order;

@Controller
public class DemoController {
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("orders", orderService.findAll());
		return "index";
	}
	

}
