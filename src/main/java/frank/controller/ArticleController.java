package frank.controller;

import frank.model.Article;
import frank.model.Category;
import frank.model.Comment;
import frank.model.User;
import frank.service.ArticleService;
import frank.service.CategoryService;
import frank.service.CommentService;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @Autowired
    private CategoryService categoryService;//文章分类

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

    @RequestMapping("/writer")//页面需要什么属性就注入什么属性
    public String writer(HttpSession session,Long activeCid,Model model){//根据session获取到User 返回文章列表
        User user=(User) session.getAttribute("user");
        List<Article> articles=articleService.queryArticlesByUserId(user.getId());//通过用户id查找出来所有的文章
        model.addAttribute("articleList",articles);//添加文章列表
        List<Category> categories=categoryService.queryCategoriesByUserId(user.getId());//通过用户id获取到分类列表
        model.addAttribute("categoryList",categories);
        model.addAttribute("activeCid",
                activeCid == null ? categories.get(0).getId():activeCid);//从已经写好的分类列表中获取 ，前两个属性从数据苦衷获取
        return "writer";
    }

    /**
     * 从writer页面进行的跳转
     *跳转到新建文章/修改页面（同一个页面editor）仅是跳转页面
     * id:新增的时候为categoryId,修改的时候是articleId
     * model:editor页面需要type属性，都需要category,新增时需要activeCid,修改的时候需要article
     * */
    @RequestMapping("/writer/forward/{type}/{id}/editor")//新增文章页面和文章修改页面跳转 type字段表示地是如果为1则是新增如果为2则是修改
    public String editor(@PathVariable("type") Long type,
                         @PathVariable("id") Long id,Model model){//页面需要传入地参数
        Category category;//因为文章属性这两个功能都要用到 所以首先定义
        //首先判断是新增还是修改
        if(type==1){//完成editor页面新增的属性设置 自动拆装箱
            category=categoryService.queryCategoryById(id);
            model.addAttribute("activeCid",id);
        }else{//完成editor页面修改的属性设置
            Article article=articleService.queryArticle(id);//可以使用一次sql查询出article和category的数据，Article定义Category为一个属性
            //sql中使用assocition标签作为查询结果一对一关联关系
            model.addAttribute("article",article);
            category=categoryService.queryCategoryById(new Long(article.getCategoryId()));
        }
        model.addAttribute("type",type);
        model.addAttribute("category",category);
        return "editor";
    }

    /**
     * 文章新增以及修改的操作
     * article:发布的文章信息
     * */
    @RequestMapping(value = "/writer/article/{type}/{id}",method = RequestMethod.POST)
    public String publish(@PathVariable("type") Long type,
                          @PathVariable("id") Integer id,Article article,
                          HttpSession session){

        article.setUpdatedAt(new Date());
        if(type==1){//新增的时候插入文章数据
            article.setCategoryId(id);//放入文章id
            User user=(User)session.getAttribute("user");
            article.setUserId(user.getId());
            article.setCoverImage("https://picsum.photos/id/1/400/300");//图片设置成死的
            article.setCreatedAt(new Date());
            article.setStatus((byte)0);
            article.setViewCount(0L);//浏览量
            article.setCommentCount(0);//评论量
            int num=articleService.insert(article);
            id = article.getId().intValue();
        }else{//修改的时候，修改文章数据
            article.setId(new Long(id));
            int num=articleService.updateByCondition(article);
        }
        return String.format("redirect:/writer/forward/2/%s/editor",id);
    }

}
