package frank.service;

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
}
