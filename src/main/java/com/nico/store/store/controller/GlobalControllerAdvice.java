package com.nico.store.store.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nico.store.store.domain.CartItem;
import com.nico.store.store.domain.Category;
import com.nico.store.store.domain.ShoppingCart;
import com.nico.store.store.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nico.store.store.domain.User;
import com.nico.store.store.service.ShoppingCartService;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	public static final String DEFAULT_ERROR_VIEW = "error";
		
	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ArticleService articleService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@ModelAttribute
	public void populateModel(Model model) {	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {				
			User user =  (User) auth.getPrincipal(); 
			if (user != null) {
				model.addAttribute("shoppingCartItemNumber", shoppingCartService.getItemsNumber(user) );
				ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);
				List<CartItem> cartItemList =  shoppingCart.getCartItems();
				if(cartItemList.size() > 4) {
					cartItemList = cartItemList.subList(0, 4);
				}
				model.addAttribute("cartItemList", cartItemList);
			}
		} else { 
			model.addAttribute("shoppingCartItemNumber", 0);
			model.addAttribute("cartItemList", new ArrayList<>());
		}
		List<Category> test = articleService.getAllCategories();
		model.addAttribute("lstCategory", articleService.getAllCategories());
	}
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;		
		ModelAndView mav = new ModelAndView();
		mav.addObject("timestamp", new Date(System.currentTimeMillis()));
		mav.addObject("path", req.getRequestURL());
		mav.addObject("message", e.getMessage());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
	
	
}
