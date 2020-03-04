package edu.rpi.project.examdatabase.examdb.Controllers;

import edu.rpi.project.examdatabase.examdb.Services.AuthenticationService;
import edu.rpi.project.examdatabase.examdb.Question;
import edu.rpi.project.examdatabase.examdb.Services.ReadQuestionService;
import edu.rpi.project.examdatabase.examdb.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/edit")
public class EditController {

    @RequestMapping(value="/add_new_question", method=RequestMethod.GET)
    public ModelAndView getAddNewQuestion(@CookieValue(value="token", defaultValue="") String session_token,
                                    ModelMap model){
        /*
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null){
            return new ModelAndView("redirect:/", model);
        }
        */
        return new ModelAndView("EditQuestion", model);
    }

    @RequestMapping(value="/add_new_question", method=RequestMethod.POST)
    public ModelAndView submitNewQuestion(@CookieValue(value="token", defaultValue="") String session_token,
                                          ModelMap model){
        throw new RuntimeException("/edit_question POST request handler is not implemented yet");
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
        throw new RuntimeException("/bulk_upload GET request handler is not implemented yet");
    }

    @RequestMapping(value="/bulk_upload", method=RequestMethod.POST)
    public ModelAndView submitBulkUpload(@CookieValue(value="token", defaultValue="") String session_token,
                                         ModelMap model){
        throw new RuntimeException("/bulk_upload POST request handler is not implemented yet");
    }
}
