package forum.messenger.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public String handleErrors(Exception exceptionMessage, Model model) {
        model.addAttribute("exceptionMessage", exceptionMessage.getMessage());
        StackTraceElement[] a = exceptionMessage.getStackTrace();

        model.addAttribute("stack", a);
        logger.error("exception error ", exceptionMessage);
        return "custom_error";
    }
}
