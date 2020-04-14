package cn.zh.blog.controller.admin;

import cn.zh.blog.dto.ResponseResult;
import cn.zh.blog.pojo.Blog;
import cn.zh.blog.pojo.User;
import cn.zh.blog.queryvo.BlogQueryVo;
import cn.zh.blog.service.BlogService;
import cn.zh.blog.service.TagService;
import cn.zh.blog.service.TypeService;
import cn.zh.blog.util.AliyunOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author 郑豪
 * @Date 2020/4/6 23:49
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 7,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        BlogQueryVo blog, Model model) {
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 7,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        BlogQueryVo blog, Model model) {
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null){
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(),blog);
        }

        if (b == null) {
            attributes.addFlashAttribute("message","操作博文失败-_-!!!");
        } else {
            attributes.addFlashAttribute("message","操作博文成功^.^");

        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/input")
    public String editBlog(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;

    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除博文成功^.^");

        return REDIRECT_LIST;
    }

    @RequestMapping("/blogs/imgUpdate")
    @ResponseBody
    public ResponseResult imageUpload(@RequestParam("editormd-image-file") MultipartFile file){
        ResponseResult responseResult = new ResponseResult();
        String filename = file.getOriginalFilename();
        try {
            if (file != null){
                if (!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream outputStream = new FileOutputStream(newFile);
                    outputStream.write(file.getBytes());
                    outputStream.close();
                    file.transferTo(newFile);
                    String url = AliyunOssUtil.upLoad(newFile);
                    responseResult.setSuccess(1);
                    responseResult.setUrl(url);
                    responseResult.setMessage("上传成功");
                }
            }
        } catch (FileNotFoundException e) {
            responseResult.setSuccess(0);
            responseResult.setMessage("上传失败");
            e.printStackTrace();
        } catch (IOException e){
            responseResult.setSuccess(0);
            responseResult.setMessage("上传失败");
            e.printStackTrace();
        }
        return responseResult;
    }
}
