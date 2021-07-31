package top.dreamyy.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import top.dreamyy.hrm.domain.User;
import top.dreamyy.hrm.service.HrmService;
import top.dreamyy.hrm.util.common.HrmConstants;
import top.dreamyy.hrm.util.tag.PageModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 处理用户请求控制器
 */
@Controller
public class UserController {
    /**
     * 自动注入UserService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理登录请求
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("loginname") String loginname,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv) {
        User user = hrmService.login(loginname, password);  // 调用业务逻辑组件判断用户是否可以登录
        if (user != null) {
            session.setAttribute(HrmConstants.USER_SESSION, user);  // 将用户保存到HttpSession当中
            mv.setViewName("redirect:/main");  // 客户端跳转到main页面
        } else {
            mv.addObject("message", "登录名或密码错误!请重新输入");   // 设置登录失败提示信息
            mv.setViewName("forward:/loginForm");  // 服务器内部跳转到登录页面
        }
        return mv;
    }

    /**
     * 处理查询请求
     */
    @RequestMapping(value = "/user/selectUser")
    public String selectUser(Integer pageIndex,
                             @ModelAttribute User user,
                             Model model) {
        System.out.println("user = " + user);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        List<User> users = hrmService.findUser(user, pageModel); // 查询用户信息
        model.addAttribute("users", users);
        model.addAttribute("pageModel", pageModel);
        return "user/user";
    }

    /**
     * 处理删除用户请求
     */
    @RequestMapping(value = "/user/removeUser")
    public ModelAndView removeUser(String ids, ModelAndView mv) {
        String[] idArray = ids.split(","); // 分解id字符串
        for (String id : idArray) {
            hrmService.removeUserById(Integer.parseInt(id)); // 根据id删除员工
        }
        mv.setViewName("redirect:/user/selectUser"); // 设置客户端跳转到查询请求
        return mv;  // 返回ModelAndView
    }

    /**
     * 处理修改用户请求
     */
    @RequestMapping(value = "/user/updateUser")
    public ModelAndView updateUser(
            String flag,
            @ModelAttribute User user,
            ModelAndView mv) {
        if (flag.equals("1")) {
            User target = hrmService.findUserById(user.getId());  // 根据id查询用户
            mv.addObject("user", target);   // 设置Model数据
            mv.setViewName("user/showUpdateUser");   // 返回修改员工页面
        } else {
            System.out.println(user);
            hrmService.modifyUser(user);       // 执行修改操作
            mv.setViewName("redirect:/user/selectUser");    // 设置客户端跳转到查询请求
        }
        return mv;  // 返回
    }

    /**
     * 处理添加请求
     */
    @RequestMapping(value = "/user/addUser")
    public ModelAndView addUser(
            String flag,
            @ModelAttribute("User") User user,
            ModelAndView mv) {
        if (flag.equals("1")) {
            mv.setViewName("user/showAddUser"); // 设置跳转到添加页面
        } else {
            hrmService.addUser(user); // 执行添加操作
            mv.setViewName("redirect:/user/selectUser"); // 设置客户端跳转到查询请求
        }
        return mv; // 返回
    }
}
