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

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ModelAndView Query(@CookieValue(value = "token", defaultValue = "") String session_token,
                              @RequestParam(name="key_word", required=false, defaultValue="") String key_word,
                              @RequestParam(name="search_type", required=false, defaultValue="-1")Integer search_type,
                              ModelMap model){
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null){
            return new ModelAndView("redirect:/", model);
        }
        model.addAttribute("permission", 0);
        List<Question> search_results;
        switch (search_type){
            case 0://key word
                search_results = ReadQuestionService.GetQuestionsByKeyWord(user, key_word);
                break;
            case 1://tag
                List<String> tag_list = Arrays.asList(key_word.split(" "));
                search_results = ReadQuestionService.GetQuestionsByTags(user, tag_list);
                break;
            case 2://question id
                search_results = new LinkedList<Question>();
                int q_id = Integer.parseInt(key_word);
                search_results.add(ReadQuestionService.GetQuestionById(user,q_id ));
                break;
            default:
                search_results = new LinkedList<Question>();
                break;
        }
        model.addAttribute("search_results", search_results);
        return new ModelAndView("SearchWithResults", model);
    }
}
