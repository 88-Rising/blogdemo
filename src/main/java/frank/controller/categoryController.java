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
public class categoryController {//增加文章分类功能

    @Autowired
    private CategoryService categoryService;//增加文章类属性

    @RequestMapping(value="/c/add",method = RequestMethod.POST)//文章分类功能
    public String addCategory(HttpSession session, Category category){//userId从session里边取
        User user = (User)session.getAttribute("user");
        category.setUserId(user.getId());
        int num=categoryService.insert(category);//把新生成的文章分类名放入文章分类列表
        return "redirect:/writer";
    }

}
