package by.frost.http.controller;

import by.frost.database.entity.Role;
import by.frost.database.repository.CompanyRepository;
import by.frost.dto.UserReadDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model,
                        CompanyRepository companyRepository,
                        HttpServletRequest request,
                        UserReadDto userReadDto) {
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public String bye(@SessionAttribute("user") UserReadDto user) {
        return "greeting/bye";
    }
}
