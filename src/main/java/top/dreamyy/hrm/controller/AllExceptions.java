package top.dreamyy.hrm.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AllExceptions {
    @ExceptionHandler(value={Exception.class})

    public ModelAndView ExceptionPage (Exception exception) {
        ModelAndView mv = new ModelAndView("errorpage");
        mv.addObject("exception", exception);
        return mv;
    }
}
