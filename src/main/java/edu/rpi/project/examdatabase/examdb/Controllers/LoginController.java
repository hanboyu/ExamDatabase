package edu.rpi.project.examdatabase.examdb.Controllers;

import edu.rpi.project.examdatabase.examdb.Services.AuthenticationService;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView Login(ModelMap model, @CookieValue(value = "token", defaultValue = "")
            String session_token) {
        User user = AuthenticationService.VerifyToken(session_token);
        if (user.getUserType() != 0) {
            return new ModelAndView("redirect:/", model);
        }
        return new ModelAndView("Login", model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView LoginByPassword(ModelMap model, HttpServletResponse response,
                                        @RequestParam("uname") String username,
                                        @RequestParam("psw") String password) {
        String token;
        try {
            token = AuthenticationService.LoginByPassword(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            token = "";
        }

        if (token.isEmpty()) {
            model.addAttribute("username", username);

            //delete cookie
            Cookie empty_token = new Cookie("token", "");
            empty_token.setMaxAge(0);
            empty_token.setHttpOnly(true);
            response.addCookie(empty_token);

            model.addAttribute("error_message", true);
            return new ModelAndView("Login", model);
        } else {
            Cookie token_cookie = new Cookie("token", token);
            token_cookie.setHttpOnly(true);
            response.addCookie(token_cookie);
            return new ModelAndView("Search", model);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public RedirectView Logout(@CookieValue(value = "token", defaultValue = "")
                                           String session_token,
                               HttpServletResponse response, Model model){
        AuthenticationService.Logout(session_token);
        //delete cookie
        Cookie empty_token = new Cookie("token", "");
        empty_token.setMaxAge(0);
        empty_token.setHttpOnly(true);
        response.addCookie(empty_token);

        return new RedirectView("/");
    }
}
