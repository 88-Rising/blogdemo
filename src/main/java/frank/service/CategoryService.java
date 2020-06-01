package frank.service;

import frank.mapper.CategoryMapper;
import frank.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoriesByUserId(Long id) {
        return categoryMapper.queryCategoriesByUserId(id);
    }
}