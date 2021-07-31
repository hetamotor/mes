package top.dreamyy.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.dreamyy.hrm.domain.Job;
import top.dreamyy.hrm.service.HrmService;
import top.dreamyy.hrm.util.tag.PageModel;

import java.util.List;

@Controller
public class JobController {
    /**
     * 自动注入UserService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理/login请求
     */
    @RequestMapping(value = "/job/selectJob")
    public String selectJob(Model model, Integer pageIndex,
                            @ModelAttribute Job job) {
        System.out.println("selectJob -->> " + job);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Job> jobs = hrmService.findJob(job, pageModel);  // 查询用户信息
        model.addAttribute("jobs", jobs);
        model.addAttribute("pageModel", pageModel);
        return "job/job";
    }

    /**
     * 处理删除职位请求
     */
    @RequestMapping(value = "/job/removeJob")
    public ModelAndView removeJob(String ids, ModelAndView mv) {

        String[] idArray = ids.split(","); // 分解id字符串
        for (String id : idArray) {
            hrmService.removeJobById(Integer.parseInt(id));  // 根据id删除职位
        }
        mv.setViewName("redirect:/job/selectJob");   // 设置客户端跳转到查询请求
        return mv; // 返回ModelAndView
    }

    /**
     * 处理添加请求
     */
    @RequestMapping(value = "/job/addJob")
    public ModelAndView addJob(
            String flag,
            @ModelAttribute Job job,
            ModelAndView mv) {
        if (flag.equals("1")) {
            mv.setViewName("job/showAddJob"); // 设置跳转到添加页面
        } else {
            hrmService.addJob(job); // 执行添加操作
            mv.setViewName("redirect:/job/selectJob");  // 设置客户端跳转到查询请求
        }
        return mv; // 返回
    }


    /**
     * 处理修改职位请求
     */
    @RequestMapping(value = "/job/updateJob")
    public ModelAndView updateDpet(
            String flag,
            @ModelAttribute Job job,
            ModelAndView mv) {
        if (flag.equals("1")) {
            Job target = hrmService.findJobById(job.getId());  // 根据id查询部门
            mv.addObject("job", target);  // 设置Model数据
            mv.setViewName("job/showUpdateJob"); // 设置跳转到修改页面
        } else {
            hrmService.modifyJob(job); // 执行修改操作
            mv.setViewName("redirect:/job/selectJob"); // 设置客户端跳转到查询请求
        }
        return mv; // 返回
    }
}

