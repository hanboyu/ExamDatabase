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
        Question q1 = new Question("123456789", new LinkedList<String>(Arrays.asList("Greek myths", "Classic")),
                "", "visitor", "Who introduced the idea that dreams and myths were the projection" +
                " of what he called the \"collective unconscious\" based on archetypal characters and narrative patterns.",
                new LinkedList<String>(Arrays.asList("Carl Jung", "B. Malinowski", "J. G. Frazer", "Joseph Campbell")),
                "Carl Jung");

        Question q2 = new Question("123456790", new LinkedList<String>(Arrays.asList("Operating systems", "Network")),
                "CSCI-4210", "visitor", "In network communication, what is encapsulation?",
                new LinkedList<String>(Arrays.asList("Setting the port number on a server socket", "Adding extra data (headers) at each layer of the network stack", "Using a struct to pass multiple arguments as a single void*", "Ordering data to be in network byte order",
                        "Removing extra data (headers) at each layer of the network stack")),
                "Adding extra data (headers) at each layer of the network stack");

        Question q3 = new Question("123456791", new LinkedList<String>(Arrays.asList("Operating systems", "Network", "Long Question")),
                "CSCI-4210", "visitor", "Again during the sleep() call above, assume that three different processes simultaneously run the code snippet from Q5a above, assuming again that stdout is non-buffered. What happens with the output of each of these three processes?",
                new LinkedList<String>(Arrays.asList("All three processes display the same output from Q5a",
                        "The three processes display the output from Q5a with each character potentially interleaved",
                        "The three processes each display four characters, but the characters differ depending on the order the processes run in",
                        "No output is produced by any of the three processes",
                        "One of the three processes displays the output from Q5a, while the other two do not display anything")),
                "One of the three processes displays the output from Q5a, while the other two do not display anything");

        Question q4 = new Question("123456792", new LinkedList<String>(Arrays.asList("Programing languages", "Question with latex")),
                "CSCI-4210", "visitor","Consider the expression grammar below. $$expr \\to expr\\ x\\ expr | expr\\ \\#\\ expr | id$$ How many parse trees are there for string id x id # id X id?",
                new LinkedList<String>(Arrays.asList("2", "0", "5", "1")),"5");

        Question q5 = new Question("123456793", new LinkedList<String>(Arrays.asList("MA 101", "Question with image")),
                "MA 101", "visitor", "Who is the man in the picture?<img src=\"question_images/dr_ma.PNG\" alt=\"question image\">",
                new LinkedList<String>(Arrays.asList("Dr. Shirley Ann Jackson", "Stephen van Rensselaer III", "Dr. Alex Ma", "Dr. Barbara Cutler", "Dr. Selmer Bringsjord")),
                "Dr. Alex Ma");
        search_results.add(q1);
        search_results.add(q2);
        search_results.add(q3);
        search_results.add(q4);
        search_results.add(q5);
        model.addAttribute("permission", SearchHelperFunctions.getPermissionLevel(user.getUserType()));
        model.addAttribute("search_results", search_results);
        return new ModelAndView("SearchWithResults", model);
    }
}
