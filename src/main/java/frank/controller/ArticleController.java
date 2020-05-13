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


    @Autowired
    private ArticleService articleService;//调用其中的方法来处理一些逻辑

    @RequestMapping("/")
    public String index(Model model){
        List<Article> articles =articleService.queryArticles();//在根路径下查找所有的文章列表 然后返回回来
        model.addAttribute("articleList",null);

        return "index";
    }
}
