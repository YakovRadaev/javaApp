//package com.example.springSecurityApplication.controllers.user;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class UserController {
//    @GetMapping("/index")
//    public String index(){
//
//        // Получаем объект аутентификации - > с помощью Spring SecurityContextHolder обращаемся к контексту и на нем вызываем метод аутентификации.
//        // Из потока для текущего пользователя мы получаем объект, который был положен в сессию после аутентификации
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
////        System.out.println("ID пользователя: " + personDetails.getPerson().getId());
////        System.out.println("Логин пользователя: " + personDetails.getPerson().getLogin());
////        System.out.println("Пароль пользователя: " + personDetails.getPerson().getPassword());
//        return "user/index";
//    }
//}
package com.example.springSecurityApplication.controllers.user;

import com.example.springSecurityApplication.security.PersonDetails;
import com.example.springSecurityApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    private final ProductService productService;

    @Autowired
    public UserController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")
    public String index() {

        // Получаем объект аутентификации - > с помощью Spring SecurityContextHolder обращаемся к контексту и на нем вызываем метод аутентификации.
        // Из потока для текущего пользователя мы получаем объект, который был положен в сессию после аутентификации
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String role = personDetails.getPerson().getRole();
        if(role.equals("ROLE_ADMIN"))
        {
            return "redirect:/admin";
        }
        return "user/index";
    }

    @GetMapping("/product")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "/product/product";
    }

    @GetMapping("/product/info/{id}")
    public String infoUser(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product/infoProduct";
    }


}
