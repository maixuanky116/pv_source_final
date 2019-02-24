package phoviet.company.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import phoviet.company.dao.ProductDao;
import phoviet.company.dao.TypeDao;
import phoviet.company.dao.UserDao;
import phoviet.company.entity.Product;
import phoviet.company.entity.User;

@Controller
public class AdminContronller {

	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;

	@Autowired
	TypeDao typeDao;

	@RequestMapping(value="/product-post", method=RequestMethod.GET)
	public String post(Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "login/login";

		Product product = new Product(); 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("module", "post");
		model.addAttribute("product", product);
		return "user/post";
	}

	@RequestMapping(value="/product-manage", method=RequestMethod.GET)
	public String productManage(Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "login/login";

		User user = (User) session.getAttribute("user");

		List<Product> products = (List<Product>) productDao.findAll();

		model.addAttribute("user", user);
		model.addAttribute("products", products);
		model.addAttribute("module", "product-manage");
		return "user/product_manage";
	}

	// delete
	@RequestMapping(value = "delete-product", method = RequestMethod.GET)
	public String deleteProduct(RedirectAttributes redirectAttributes, @RequestParam("id") int id) {

		productDao.delete(id);

		redirectAttributes.addFlashAttribute("manager_module", "instruction_manager");
		return "redirect:/product-manage";
	}


	@RequestMapping(value = "/update-user-info", method=RequestMethod.POST)
	public String updateInfo (Model model, HttpSession session, @ModelAttribute("user") User user) {
		if (session.getAttribute("user") == null)
			return "login/login";

		User entity = (User) session.getAttribute("user");
		if (entity != null) {
			entity.setName(user.getName());
			entity.setUsername(user.getUsername());
			entity.setPassword(user.getPassword());
			entity.setEmail(user.getEmail());;
			entity.setStatus(user.getStatus());
			entity.setCreateday(user.getCreateday());
			userDao.save(entity);
		}		
		model.addAttribute("user", user);
		return "redirect:/user-info";
	}

	@RequestMapping(value="/change-pass", method=RequestMethod.GET)
	public String changePass(Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "login/login";

		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("module", "change-pass");
		return "user/change_pass";
	}

	@RequestMapping(value="/save-pass", method=RequestMethod.POST)
	public String savePass(Model model, HttpSession session, @RequestParam("old-psw") String oldPassword,
			@RequestParam("new-psw") String newPassword, @RequestParam("re-new-psw") String reNewPassword) {
		if(session.getAttribute("user") == null)
			return "login/login";
		User user = (User) session.getAttribute("user");

		if(!(oldPassword.equalsIgnoreCase(user.getPassword()))) {
			//			model.addAttribute("info", 1);
			return "redirect:user/change_pass";
		} else if(!(newPassword.equalsIgnoreCase(reNewPassword))) {
			model.addAttribute("info", 2);
			return "redirect:user/change_pass";
		}				
		user.setPassword(newPassword);
		userDao.save(user);

		model.addAttribute("user", user);
		model.addAttribute("module", "change-pass");
		return "redirect:user/change_pass";
	}

	@RequestMapping(value = "/save-post", method = RequestMethod.POST)
	public String savePost(HttpServletRequest request, HttpSession session, Model model, @ModelAttribute("product") Product product,
			@RequestParam("product-img") MultipartFile productImage) {
		if (session.getAttribute("user") == null)
			return "login/login";

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String dateCreate = dateFormat.format(date);
		product.setCreatepostday(dateCreate);

		User user = (User) session.getAttribute("user");
		product.setAuthor(user.getUsername());

		productDao.save(product);

		String path = request.getRealPath("/") + "/image/" + "image_" + product.getId() + ".jpg";
		storeFile(productImage, path);

		product.setImage("/image/" + "image_" + product.getId() + ".jpg");
		productDao.save(product);

		model.addAttribute("product", product);		

		return "redirect:/product-post";
	}

	// update exercise info
	@RequestMapping(value = "/update-post", method = RequestMethod.GET)
	public String updateExerciseInfo(HttpSession session, @RequestParam("id") int productId, Model model) {

		Product product = productDao.findById(productId);
		User user = (User) session.getAttribute("user");

		model.addAttribute("user", user);
		model.addAttribute("module", "exercise-info");
		model.addAttribute("product", product);

		return "user/update_post";
	}

	@RequestMapping(value = "/update-post", method = RequestMethod.POST)
	public String updatePost(HttpServletRequest request, HttpSession session, Model model, @ModelAttribute("product") Product product,
			@RequestParam("product-img") MultipartFile productImage) {
		if (session.getAttribute("user") == null)
			return "login/login";

		Product entity = productDao.findById(product.getId());
		if(entity != null) {
			entity.setName(product.getName());
			entity.setContent(product.getContent());
			entity.setAuthor(product.getAuthor());
			entity.setType(product.getType());
			entity.setCreateday(product.getCreateday());
			entity.setCreatepostday(product.getCreatepostday());
			entity.setFinishday(product.getFinishday());
			entity.setImage(product.getImage());
			entity.setMoney(product.getMoney());
			entity.setScale(product.getScale());
			entity.setSize(product.getSize());
			entity.setStatus(product.getStatus());
			productDao.save(entity);

			String path = request.getRealPath("/") + "/image/" + "image_" + product.getId() + ".jpg";
			storeFile(productImage, path);

			product.setImage("/image/" + "image_" + product.getId() + ".jpg");
			productDao.save(entity);

		}
		model.addAttribute("product", product);		

		return "redirect:/product-post";
	}

	// store file function
	private void storeFile(MultipartFile file, String path) {

		if (!file.isEmpty()) {
			try {
				// fileName = file.getOriginalFilename();
				// System.out.println("path: "+path);
				byte[] bytes = file.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
				buffStream.write(bytes);
				buffStream.close();
				// System.out.println("store file success: ");
			} catch (Exception e) {
				// System.out.println("store file not success "+ e.getMessage());
			}
		} else {
			// return "Unable to upload. File is empty.";
		}
	}

}
