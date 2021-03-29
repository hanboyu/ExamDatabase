package edu.rpi.project.examdatabase.examdb.Controllers;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.Objects.Question.QuestionFactory;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;
import edu.rpi.project.examdatabase.examdb.Services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/edit")
public class EditController {

    @RequestMapping(value = "/add_new_question", method = RequestMethod.GET)
    public ModelAndView getAddNewQuestion(@CookieValue(value = "token", defaultValue = "") String session_token,
                                          ModelMap model) {

        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null) {
            return new ModelAndView("redirect:/", model);
        }

        return new ModelAndView("EditQuestion", model);
    }

    @RequestMapping(value = "/add_new_question", method = RequestMethod.POST)
    public ModelAndView submitNewQuestion(@CookieValue(value = "token", defaultValue = "") String session_token,
                                          ModelMap model, @RequestParam("tags") List<String> tags,
                                          @RequestParam("class_code") String class_code,
                                          @RequestParam("permission") String permission,
                                          @RequestParam("question_body") String question_body,
                                          @RequestParam("choices") List<String> choices,
                                          @RequestParam("answer") String answer) {

        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null) {
            return new ModelAndView("redirect:/", model);
        }

        model.addAttribute("tags", tags);
        model.addAttribute("class_code", class_code);
        model.addAttribute("permission", permission);
        model.addAttribute("question_body", question_body);
        model.addAttribute("choices", choices);
        model.addAttribute("answer", answer);

        QuestionFactory myQuestionFactory = new QuestionFactory();
        /*
        try {
            Question q = myQuestionFactory.makeQuestion(tags, class_code, permission, question_body, choices, answer);
            user.addQuestion(q);
        } catch (Exception e) {
            return new ModelAndView("EditQuestion", model);
        }
        */
        return new ModelAndView("EditQuestionSuccess", model);
    }

    @RequestMapping(value="/edit_question/{q_id}", method=RequestMethod.GET)
    public ModelAndView getEditQuestion(@CookieValue(value="token", defaultValue="") String session_token,
                                     @PathVariable(value="q_id") String question_id,
                                     ModelMap model){
        throw new RuntimeException("/edit_question GET request handler is not implemented yet");
    }

    @RequestMapping(value="/edit_question/{q_id}", method=RequestMethod.POST)
    public ModelAndView submitEditQuestion(@CookieValue(value="token", defaultValue="") String session_token,
                                           @PathVariable(value="q_id") String question_id,
                                           ModelMap model){
        throw new RuntimeException("/edit_question POST request handler is not implemented yet");
    }

    @RequestMapping(value="/bulk_upload", method=RequestMethod.GET)
    public ModelAndView getBulkUpload(@CookieValue(value="token", defaultValue="") String session_token,
                                      ModelMap model){
        return new ModelAndView("BulkUpload", model);
    }

    @RequestMapping(value="/bulk_upload", method=RequestMethod.POST)
    public ModelAndView submitBulkUpload(@CookieValue(value="token", defaultValue="") String session_token,
                                         ModelMap model){
        throw new RuntimeException("/bulk_upload POST request handler is not implemented yet");
    }
}
