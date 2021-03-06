package me.anant.PMS.controller;

import java.awt.Dialog.ModalExclusionType;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import me.anant.PMS.exceptions.OrderNotFoundException;
import me.anant.PMS.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.anant.PMS.model.Order;
import me.anant.PMS.model.OrderProduct;
import me.anant.PMS.model.Product;
import me.anant.PMS.model.User;

import me.anant.PMS.service.OrderService;
import me.anant.PMS.service.ProductService;
import me.anant.PMS.service.UserService;

@Controller
public class OrderController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/customer/order_place")
	public ModelAndView customerHome() {
		List<Product> pList =  productService.get();
		ModelAndView modelAndView = new ModelAndView("customer/home");
		modelAndView.addObject("pList", pList);
		return modelAndView;
	}

	/**
	 * Esse POST é responsável por realizar o pedido.
	 * @param request
	 * @param principal
	 * @return ModelAndView
	 */
	@PostMapping("/customer/order_place")
	public ModelAndView orderPlace(HttpServletRequest request, Principal principal) throws ProductNotFoundException{
		String[] pIds = request.getParameterValues("productId");
		Set<OrderProduct> opList = new HashSet<>();
		for(String pId: pIds) {
			long pid = Long.parseLong(pId);
			Product product = productService.findById(pid).get();
			int buyqty = Integer.parseInt(request.getParameter(pId));
			opList.add(new OrderProduct(product, buyqty));
			productService.deductQty(pid, buyqty);
		}
		User user = userService.findByEmail(principal.getName());
		orderService.save(new Order(user, "PROCESSANDO...", opList));
		
		String message = "Olá,<br><br>Seu pedio foi realizado! A seguir estão os detalhes da sua compra:<br><br>"
				+ "<table>" + 
				"<tr>" + 
				"<th>Name</th>" + 
				"<th>Price</th>" + 
				"<th>Qty</th>" + 
				"<th>Amount</th>" + 
				"</tr>";
		float sum = 0;
		for (OrderProduct op : opList)
		{
			sum = sum + op.getProduct().getProductPrice() * op.getBuyqty();
			message = message + "<tr>" + 
					"<td>"+op.getProduct().getProductName()+"</td>" + 
					"<td>Rs. "+op.getProduct().getProductPrice()+"</td>" + 
					"<td>"+op.getBuyqty()+"</td>" + 
					"<td>Rs. "+op.getProduct().getProductPrice() * op.getBuyqty()+"</td>" + 
					"</tr>";
		}
		/* message = message + "<tr><td  colspan=\"3\"><center><b>Total</b></center></td><td>Rs. "+sum+"</td></tr>" + 
				"</table>";
		emailService.send(principal.getName(), "Pedido realizado!", message);*/
		
		ModelAndView modelAndView = new ModelAndView("customer/order_place");
		modelAndView.addObject("opList", opList);
		return modelAndView;
	}

	/**
	 * Esse Get é responsável pela visualização do pedido do cliente.
	 *
	 * @param principal
	 * @return ModelAndView
	 */
	@GetMapping("customer/order/list")
	public ModelAndView viewMyOrder(Principal principal) {
		User user = userService.findByEmail(principal.getName());
		ModelAndView modelAndView = new ModelAndView("customer/order/list");
		modelAndView.addObject("orderList", user.getOrders());
		return modelAndView;
	}

	
	@GetMapping("admin/order/list")
	public ModelAndView viewAllOrder() {
		List<Order> oList = orderService.get();
		ModelAndView modelAndView = new ModelAndView("admin/order/list");
		modelAndView.addObject("oList", oList);
		return modelAndView;
	}

	
	@GetMapping("admin/order/detail")
	public ModelAndView orderDetailAdmin(@RequestParam("id") long id) throws ProductNotFoundException {
		ModelAndView modelAndView = new ModelAndView("admin/order/detail");
		modelAndView.addObject("order", orderService.findById(id).get());
		return modelAndView;
	}

	
	@GetMapping("customer/order/detail")
	public ModelAndView orderDetailCustomer(@RequestParam("id") long id) throws ProductNotFoundException{
		ModelAndView modelAndView = new ModelAndView("customer/order/detail");
		modelAndView.addObject("order", orderService.findById(id).get());
		return modelAndView;
	}

	@GetMapping("customer/order/cancel")
	public String orderCancel(@RequestParam("id") long id, final RedirectAttributes redirectAttributes) throws ProductNotFoundException {
		if(orderService.cancelOrder(id)) {
			redirectAttributes.addFlashAttribute("msg", "Pedido cancelado!");
			redirectAttributes.addFlashAttribute("class", "alert-success");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Pedido não cancelado. Você pode cancelar o PROCESSAMENTO ou o pedido CONFIRMADO em até 24 horas apenas.");
			redirectAttributes.addFlashAttribute("class", "alert-danger");
		}
		return "redirect:/customer/order/list";
	}


	@PostMapping("admin/order/status")
	public String changeStatus(@RequestParam("id") long id, @RequestParam("status") String status, final RedirectAttributes redirectAttributes) throws OrderNotFoundException, ProductNotFoundException {
		orderService.changeStatus(id, status);
		redirectAttributes.addFlashAttribute("msg", "Status do pedido modificado!");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/order/list";
	}
}
