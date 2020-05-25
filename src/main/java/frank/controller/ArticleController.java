package frank.controller;

import frank.model.Article;
import frank.model.Comment;
import frank.model.User;
import frank.service.ArticleService;
import frank.service.CommentService;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
/*
* controller需要调用service里边的东西
* */
@Controller
public class ArticleController {

    @Autowired//注入ArticleService类
    private ArticleService articleService;//调用其中的方法来处理一些逻辑

    @Autowired
    private CommentService commentService;

    @RequestMapping("/")//根路径 返回index页面
    public String index(Model model){
        //用户登录以后 user对象要从session获取，并且设置到页面属性中
        List<Article> articles =articleService.queryArticles();//在根路径下查找所有的文章列表 然后返回回来
        model.addAttribute("articleList",articles);

        return "index";
    }

    @RequestMapping("/a/{id}")//返回文章详情页面
    public String detail(@PathVariable("id") Long id,Model model){//返回文章详情

        Article article = articleService.queryArticle(id); //数据库中查询出来article对象
        List<Comment> comments=commentService.queryComments(id);//查询评论列表
        article.setCommentList(comments);
        model.addAttribute("article",article);
        return "info";
    }

    @RequestMapping("/writer")//页面需要什么属性就注入什么属性l
    public String writer(HttpSession session,Model model){//根据session获取到User 返回文章列表
        User user=(User) session.getAttribute("user");
        List<Article> articles=articleService.queryArticlesByUserId(user.getId());
        model.addAttribute("articleList",articles);//添加文章列表

        return "writer";
    }
}
