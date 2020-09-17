package edu.rpi.project.examdatabase.examdb.Controllers;

import edu.rpi.project.examdatabase.examdb.Controllers.HelperFunctions.SearchHelperFunctions;
import edu.rpi.project.examdatabase.examdb.Objects.Question.QuestionFactory;
import edu.rpi.project.examdatabase.examdb.Services.AuthenticationService;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.Services.ReadQuestionService;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



@Controller
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView Home(@CookieValue(value = "token", defaultValue = "") String session_token,
                             ModelMap model, HttpServletResponse response) {
        if (session_token.isEmpty()) {
            session_token = AuthenticationService.LoginAsVisitor();
        }
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null) {
            session_token = AuthenticationService.LoginAsVisitor();
            user = AuthenticationService.VerifyToken(session_token);
        }
        Cookie token_cookie = new Cookie("token", session_token);
        token_cookie.setHttpOnly(true);
        response.addCookie(token_cookie);
        model.addAttribute("permission", SearchHelperFunctions.getPermissionLevel(user.getUserType()));
        return new ModelAndView("Search", model);

    }

    @RequestMapping(value = "/search_by_key_word", method = RequestMethod.GET)
    public ModelAndView SearchByKeyWord(@CookieValue(value = "token", defaultValue = "") String session_token,
                                        @RequestParam(name = "key_word", required = false, defaultValue = "") String key_word,
                                        ModelMap model, HttpServletResponse response) {

        if (session_token.isEmpty()) {
            session_token = AuthenticationService.LoginAsVisitor();
        }
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null) {
            session_token = AuthenticationService.LoginAsVisitor();
            user = AuthenticationService.VerifyToken(session_token);
        }
        Cookie token_cookie = new Cookie("token", session_token);
        token_cookie.setHttpOnly(true);
        response.addCookie(token_cookie);

        List<Question> search_results;
        search_results = ReadQuestionService.GetQuestionsByKeyWord(user, key_word);

        model.addAttribute("permission", SearchHelperFunctions.getPermissionLevel(user.getUserType()));
        model.addAttribute("search_results", search_results);
        return new ModelAndView("SearchWithResults", model);
    }

    @RequestMapping(value = "/search_by_tags", method = RequestMethod.GET)
    public ModelAndView SearchByTags(@CookieValue(value = "token", defaultValue = "") String session_token,
                                     @RequestParam(name = "key_word", required = false, defaultValue = "") String key_word,
                                     ModelMap model, HttpServletResponse response) {
        if (session_token.isEmpty()) {
            session_token = AuthenticationService.LoginAsVisitor();
        }
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null) {
            session_token = AuthenticationService.LoginAsVisitor();
            user = AuthenticationService.VerifyToken(session_token);
        }
        Cookie token_cookie = new Cookie("token", session_token);
        token_cookie.setHttpOnly(true);
        response.addCookie(token_cookie);

        List<Question> search_results;
        List<String> tag_list = Arrays.asList(key_word.split(" "));
        search_results = ReadQuestionService.GetQuestionsByTags(user, tag_list);

        model.addAttribute("permission", SearchHelperFunctions.getPermissionLevel(user.getUserType()));
        model.addAttribute("search_results", search_results);
        return new ModelAndView("SearchWithResults", model);
    }

    @RequestMapping(value = "/search_by_id", method = RequestMethod.GET)
    public ModelAndView SearchByID(@CookieValue(value = "token", defaultValue = "") String session_token,
                                   @RequestParam(name = "key_word", required = false, defaultValue = "") String key_word,
                                   ModelMap model, HttpServletResponse response) {
        if (session_token.isEmpty()) {
            session_token = AuthenticationService.LoginAsVisitor();
        }
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null) {
            session_token = AuthenticationService.LoginAsVisitor();
            user = AuthenticationService.VerifyToken(session_token);
        }
        Cookie token_cookie = new Cookie("token", session_token);
        token_cookie.setHttpOnly(true);
        response.addCookie(token_cookie);

        List<Question> search_results = new LinkedList<Question>();
        search_results.add(ReadQuestionService.GetQuestionById(user, key_word));

        model.addAttribute("permission", SearchHelperFunctions.getPermissionLevel(user.getUserType()));
        model.addAttribute("search_results", search_results);
        return new ModelAndView("SearchWithResults", model);
    }

    @RequestMapping(value = "/search_result_demo", method = RequestMethod.GET)
    public ModelAndView SearchResultDemo(@CookieValue(value = "token", defaultValue = "") String session_token,
                                   ModelMap model, HttpServletResponse response) {
        if (session_token.isEmpty()) {
            session_token = AuthenticationService.LoginAsVisitor();
        }
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null) {
            session_token = AuthenticationService.LoginAsVisitor();
            user = AuthenticationService.VerifyToken(session_token);
        }
        Cookie token_cookie = new Cookie("token", session_token);
        token_cookie.setHttpOnly(true);
        response.addCookie(token_cookie);

        List<Question> search_results = new LinkedList<Question>();
        Question q = new Question("123456789", new LinkedList<String>(Arrays.asList("Greek myths", "Classic")),
                "", "visitor", "Who introduced the idea that dreams and myths were the projection" +
                " of what he called the \"collective unconscious\" based on archetypal characters and narrative patterns.",
                new LinkedList<String>(), "Carl Jung");
        //search_results.add(q);

        model.addAttribute("permission", SearchHelperFunctions.getPermissionLevel(user.getUserType()));
        model.addAttribute("q", q);
        return new ModelAndView("SearchWithResults", model);
    }
}
