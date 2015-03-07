package demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import demo.OrderService;
import demo.model.JSON;
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
	
	@JsonView(JSON.Orders.class)
	@RequestMapping("/orders")
	public @ResponseBody Collection<Order> orders() {
		return orderService.findAll();
	}
	
}
