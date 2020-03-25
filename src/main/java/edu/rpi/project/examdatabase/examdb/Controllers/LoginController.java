package edu.rpi.project.examdatabase.examdb.Controllers;

import edu.rpi.project.examdatabase.examdb.Services.AuthenticationService;
import edu.rpi.project.examdatabase.examdb.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    static final int TOKEN_DURATION = 7 * 24 * 60 * 60; // login token expires in 7 days;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RedirectView Home(@CookieValue(value = "token", defaultValue = "") String session_token,
                             RedirectAttributes redirect_attributes, Model model){
        if (session_token.isEmpty()){
            return new RedirectView("/admin");
        }
        else{
            User user = AuthenticationService.VerifyToken(session_token);
            if (user == null){
                return new RedirectView("/admin");
            }
            else{
                return new RedirectView("/search");
            }
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView Admin(ModelMap model, @CookieValue(value = "token", defaultValue = "")
            String session_token){
        User user = AuthenticationService.VerifyToken(session_token);
        if (user != null){
            return new ModelAndView("redirect:/search", model);
        }
        return new ModelAndView("Login", model);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView LoginByPassword(ModelMap model, HttpServletResponse response,
                                        @RequestParam("uname") String username,
                                        @RequestParam("psw") String password){
        String token = AuthenticationService.LoginByPassword(username, password);
        if (token.isEmpty()){
            model.addAttribute("username", username);
            return new ModelAndView( "Login", model);
        }
        else{
            Cookie token_cookie = new Cookie("token", token);
            //token_cookie.setMaxAge(TOKEN_DURATION);
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
