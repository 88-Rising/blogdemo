package frank.controller;

import frank.model.Article;
import frank.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/*
* controller需要调用service里边的东西
* */
@Controller
public class ArticleController {

    @Autowired//注入ArticleService类
    private ArticleService articleService;//调用其中的方法来处理一些逻辑

    @RequestMapping("/")//根路径 返回index页面
    public String index(Model model){
        //用户登录以后 user对象要从session获取，并且设置到页面属性中
        List<Article> articles =articleService.queryArticles();//在根路径下查找所有的文章列表 然后返回回来
        model.addAttribute("articleList",articles);

        return "index";
    }
}
