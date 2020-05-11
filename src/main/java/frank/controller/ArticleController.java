package frank.controller;

import frank.model.Article;
import frank.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String index(Model model){
        List<Article> articles =articleService.queryArticles();//在根路径下查找所有的文章列表 然后返回回来
        model.addAttribute("articleList",null);

        return "index";
    }
}
