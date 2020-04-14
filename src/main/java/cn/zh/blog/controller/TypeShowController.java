package cn.zh.blog.controller;

import cn.zh.blog.pojo.Type;
import cn.zh.blog.queryvo.BlogQueryVo;
import cn.zh.blog.service.BlogService;
import cn.zh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author 郑豪
 * @Date 2020/4/8 20:56
 **/
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
   @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        Model model,@PathVariable Long id){
       List<Type> types = typeService.listTypeTop(100);
       if (id == -1) {
           id = types.get(0).getId();
       }
       BlogQueryVo blogQueryVo = new BlogQueryVo();
       blogQueryVo.setTypeId(id);
       model.addAttribute("types",types);
       model.addAttribute("page",blogService.listBlog(pageable,blogQueryVo));
       model.addAttribute("activeTypeId",id);
       return "types";

   }
}
