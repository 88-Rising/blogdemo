package frank.service;

import frank.mapper.ArticleMapper;
import frank.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    //mapper需要加上@Mapper,Mybatis才能将Mapper注册到Spring容器中
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    public List<Article> queryArticles() {//注入文章数据库的操作
      return articleMapper.selectAll();
    }
}
