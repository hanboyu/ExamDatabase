package edu.rpi.project.examdatabase.examdb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView Home(@CookieValue(value = "token", defaultValue = "") String session_token,
                       ModelMap model){
        /*
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null){
            return new ModelAndView("redirect:/", model);
        }
        */
        model.addAttribute("permission", 0);
        return new ModelAndView("Search", model);

    }

    @RequestMapping(value = "/by_key_word", method = RequestMethod.GET)
    public ModelAndView ByKeyWord(@CookieValue(value = "token", defaultValue = "") String session_token,
                              @RequestParam(name="key_word", required=false, defaultValue="") String key_word,
                              ModelMap model){
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null){
            return new ModelAndView("redirect:/", model);
        }
        model.addAttribute("permission", 0);
        List<Question> search_results;
        search_results = ReadQuestionService.GetQuestionsByKeyWord(user, key_word);
        model.addAttribute("search_results", search_results);
        return new ModelAndView("SearchWithResults", model);
    }

    @RequestMapping(value = "/by_tags", method = RequestMethod.GET)
    public ModelAndView ByTags(@CookieValue(value = "token", defaultValue = "") String session_token,
                               @RequestParam(name="key_word", required=false, defaultValue="") String key_word,
                               ModelMap model){
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null){
            return new ModelAndView("redirect:/", model);
        }
        model.addAttribute("permission", 0);
        List<Question> search_results;
        List<String> tag_list = Arrays.asList(key_word.split(" "));
        search_results = ReadQuestionService.GetQuestionsByTags(user, tag_list);
        model.addAttribute("search_results", search_results);
        return new ModelAndView("SearchWithResults", model);
    }

    @RequestMapping(value = "/by_id", method = RequestMethod.GET)
    public ModelAndView ByID(@CookieValue(value = "token", defaultValue = "") String session_token,
                        @RequestParam(name="key_word", required=false, defaultValue="") String key_word,
                        ModelMap model){
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null){
            return new ModelAndView("redirect:/", model);
        }
        model.addAttribute("permission", 0);
        List<Question> search_results = new LinkedList<Question>();
        search_results.add(ReadQuestionService.GetQuestionById(user, key_word ));
        model.addAttribute("search_results", search_results);
        return new ModelAndView("SearchWithResults", model);
    }
}
