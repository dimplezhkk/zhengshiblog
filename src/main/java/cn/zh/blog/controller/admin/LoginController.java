package cn.zh.blog.controller.admin;

import cn.zh.blog.pojo.User;
import cn.zh.blog.service.BlogService;
import cn.zh.blog.service.TagService;
import cn.zh.blog.service.TypeService;
import cn.zh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author 郑豪
 * @Date 2020/4/6 22:57
 **/
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping("/login")
    public String showPage(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null){
            return "admin/index";
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){

        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message","用户名或密码错误 -_-!!!");
            return "redirect:/admin";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("countUser",userService.countUser());
        model.addAttribute("countBlog",blogService.countBlog());
        model.addAttribute("countTag",tagService.countTag());
        model.addAttribute("countType",typeService.countType());
        return "admin/index";
    }
}
