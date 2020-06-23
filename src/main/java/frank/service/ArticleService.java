package frank.service;

import com.sun.org.apache.regexp.internal.RE;
import frank.mapper.ArticleMapper;
import frank.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    //mapper需要加上@Mapper,Mybatis才能将Mapper注册到Spring容器中
    @Autowired//注入ArticleMapper类之后才能使用
    private ArticleMapper articleMapper;

    @Autowired
    public List<Article> queryArticles() {//注入文章数据库操作
        return articleMapper.selectAll();
    }

    public Article queryArticle(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public List<Article> queryArticlesByUserId(Long id) {
        return articleMapper.queryArticlesByUserId(id);
    }

    public int insert(Article article) {
        return articleMapper.insert(article);
    }
}
