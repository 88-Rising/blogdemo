package frank.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ControllerInterceptor {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e){//如果报错的话 直接把报错信息返回到页面
        StringWriter sw=new StringWriter();
        PrintWriter pw=new PrintWriter(sw);
        e.printStackTrace(pw);
        ModelAndView mv=new ModelAndView();
        mv.addObject("message",e.getMessage());//异常信息
        mv.addObject("stackTrace",sw.toString());//错误的堆栈信息
        mv.setViewName("error");
        return mv;

    }
}
