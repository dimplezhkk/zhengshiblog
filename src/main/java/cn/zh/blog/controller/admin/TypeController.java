package cn.zh.blog.controller.admin;

import cn.zh.blog.pojo.Type;
import cn.zh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Author 郑豪
 * @Date 2020/4/7 1:29
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(
            @PageableDefault(size = 7,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
            String name,Model model) {
        model.addAttribute("page",typeService.listType(pageable,name));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type",new Type());
        return "admin/type-inputs";
    }

    @PostMapping("/types")
    public String postSaveType(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null) {
            result.rejectValue("name","nameError","您想要添加的博文分类已经存在,不能重复添加");
        }
        if (result.hasErrors()) {
            return "admin/type-inputs";
        }
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message","新增博文分类失败-_-!!!");
        } else {
            attributes.addFlashAttribute("message","新增博文分类成功^.^");

        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-inputs";
    }

    /**
     *
     * @param type
     * @param result
     * @param attributes
     * Type type与BindingResult result的位置必须是一起的才能有作用
     * @return
     */
    @PostMapping("/types/{id}")
    public String postEditType(@Valid Type type, BindingResult result, @PathVariable Long id,RedirectAttributes attributes) {
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null) {
            result.rejectValue("name","nameError","您想要更新的博文分类已经存在,请修改");
        }
        if (result.hasErrors()) {
            return "admin/type-inputs";
        }
        Type t = typeService.updateType(id,type);
        if (t == null) {
            attributes.addFlashAttribute("message","更新博文分类失败-_-!!!");
        } else {
            attributes.addFlashAttribute("message","更新博文分类成功^.^");

        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除博文分类成功^.^");
        return "redirect:/admin/types";
    }

    @PostMapping("/types/search")
    public String search(@PageableDefault(size = 7,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
                         String name, Model model) {
        model.addAttribute("page",typeService.listType(pageable,name));
        return "admin/types :: typeList";
    }
}
