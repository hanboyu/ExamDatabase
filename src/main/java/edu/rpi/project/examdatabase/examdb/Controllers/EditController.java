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

    @RequestMapping(value="/add_question", method=RequestMethod.POST)
    public ModelAndView AddQuestion(@CookieValue(value="token", defaultValue="") String session_token,
                                    ModelMap model){
        User user = AuthenticationService.VerifyToken(session_token);
        if (user == null){
            return new ModelAndView("redirect:/", model);
        }
        throw new RuntimeException("/edit/add_question request handler is not implemented yet");
    }
}
