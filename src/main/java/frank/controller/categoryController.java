package frank.controller;

import frank.model.Category;
import frank.model.User;
import frank.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class categoryController {//增加发布文章功能

    @Autowired
    private CategoryService categoryService;//增加文章类属性

    @RequestMapping(value="/c/add",method = RequestMethod.POST)//发布文章功能
    public String addCategory(HttpSession session, Category category){//userId从session里边取
        User user = (User)session.getAttribute("user");
        category.setId(user.getId());
        int num=categoryService.insert(category);//把新生成的文章放入文章列表
        return "redirect:/writer";
    }

}
