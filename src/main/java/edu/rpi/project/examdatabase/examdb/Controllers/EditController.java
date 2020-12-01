package edu.rpi.project.examdatabase.examdb.Controllers;

import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import edu.rpi.project.examdatabase.examdb.Controllers.HelperFunctions.FileModel;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.Objects.Question.QuestionFactory;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;
import edu.rpi.project.examdatabase.examdb.Services.AuthenticationService;

import edu.rpi.project.examdatabase.examdb.Services.EditQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/edit")
public class EditController {

    @Autowired
    ServletContext context;

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

        QuestionFactory myQuestionFactory = QuestionFactory.getInstance();
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
                                      ModelMap model) {
        model.addAttribute("permission", 0);
        FileModel file = new FileModel();
        return new ModelAndView("BulkUpload", "command", file);
    }

    @RequestMapping(value = "/bulk_upload", method = RequestMethod.POST)
    public ModelAndView submitBulkUpload(@CookieValue(value = "token", defaultValue = "") String session_token,
                                         @Validated FileModel file, BindingResult result, ModelMap model,
                                         HttpServletResponse response) throws IOException {
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

        if (result.hasErrors()) {
            System.out.println("validation errors");
            model.addAttribute("permission", 0);
            FileModel new_file = new FileModel();
            return new ModelAndView("BulkUpload", "command", new_file);
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = file.getFile();
            String uploadPath = context.getRealPath("") + "temp" + File.separator;
            //Now do something with file...
            File temp_file = new File(uploadPath + file.getFile().getOriginalFilename());
            byte[] temp_byte = file.getFile().getBytes();
            EditQuestionService.parseBulkUploadFile(user, temp_byte);
            //FileCopyUtils.copy(temp_byte, temp_file);
            //String fileName = multipartFile.getOriginalFilename();
            //model.addAttribute("fileName", fileName);
            return new ModelAndView("redirect:/", model);
        }
    }
}
