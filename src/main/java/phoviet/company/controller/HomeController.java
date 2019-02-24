package phoviet.company.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import phoviet.company.dao.ProductDao;
import phoviet.company.entity.Product;
import phoviet.company.entity.User;


@Controller
public class HomeController {	
	
	@Autowired
	ProductDao productDao;
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) throws IOException {
		User user = (User) session.getAttribute("user");
		
		List<Product> products = (List<Product>) productDao.listProduct();
		
		model.addAttribute("products", products);
		model.addAttribute("module", "home");
		model.addAttribute("user", user);
		return "index";
	}
	
	@RequestMapping(value = "/thiet-ke-nha-o", method = RequestMethod.GET)
	public String designHouse(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Thiết kế nhà ở");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "design-house");
		return "design/design-house";
	}
	
	@RequestMapping(value = "/thiet-ke-van-phong", method = RequestMethod.GET)
	public String designOffice(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Thiết kế văn phòng");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "design-office");
		return "design/design-house";
	}
	
	@RequestMapping(value = "/thiet-ke-noi-that", method = RequestMethod.GET)
	public String designFurniture(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Thiết kế nội thất");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "design-furniture");
		return "design/design-house";
	}
	
	@RequestMapping(value = "/du-an-nha-o", method = RequestMethod.GET)
	public String projectHouse(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Dự án nhà ở");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "project-house");
		return "design/project-house";
	}
	
	@RequestMapping(value = "/du-an-van-phong", method = RequestMethod.GET)
	public String projectOffice(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Dự án văn phòng");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "project-office");
		return "design/project-house";
	}
	
	@RequestMapping(value = "/du-an-nha-xuong", method = RequestMethod.GET)
	public String projectFactory(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Dự án nhà xưởng");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "project-furniture");
		return "design/project-house";
	}
	
	@RequestMapping(value = "/trang-tri-noi-that-nha-o", method = RequestMethod.GET)
	public String decorateHouse(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Trang trí nội thất nhà ở");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "decorate-house");
		return "design/decorate-house";
	}
	
	@RequestMapping(value = "/trang-tri-noi-that-van-phong", method = RequestMethod.GET)
	public String decorateOffice(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Trang trí nội thất văn phòng");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "decorate-office");
		return "design/decorate-house";
	}
	
	@RequestMapping(value = "/trang-tri-noi-that-xuong", method = RequestMethod.GET)
	public String decorateFactory(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Trang trí nội thất xưởng");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "decorate-furniture");
		return "design/decorate-house";
	}
	
	@RequestMapping(value = "/xuong-san-xuat-noi-that", method = RequestMethod.GET)
	public String factoryFurniture(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");		
		List<Product> products = (List<Product>) productDao.findByType("Xưởng sản xuất nội thất");
		
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("module", "factory-furniture");
		return "design/decorate-house";
	}
	
	
	@RequestMapping(value = "/house-details", method = RequestMethod.GET)
	public String designHouseDetail(Model model, HttpSession session, @RequestParam("id") int productId) {
		User user = (User) session.getAttribute("user");
		
		Product product = productDao.findById(productId);
		
		model.addAttribute("product", product);		
		model.addAttribute("user", user);
		return "design/design-detail-item";
	}
}
