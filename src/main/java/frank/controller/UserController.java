package frank.controller;

import com.sun.org.apache.regexp.internal.RE;
import frank.model.User;
import frank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
* 用户登录操作
* */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request){
        //校验用户名和密码(不进行复杂校验 只做空的判断) 如果为空就跳转到login页面
        if(username == null || password == null)
            return "login";//用户登录返回模板页面
        //设置session操作
        User user = userService.login(username,password);
        if(user == null){
            return "login";
        }else{
            HttpSession session = request.getSession();//如果没有session那就创建一个
            session.setAttribute("user",user);//通过user这个键可以获取这个对象
            return "/";
        }
    }
}
