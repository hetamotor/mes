package top.dreamyy.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.dreamyy.hrm.domain.Dept;
import top.dreamyy.hrm.domain.Employee;
import top.dreamyy.hrm.domain.Job;
import top.dreamyy.hrm.service.HrmService;
import top.dreamyy.hrm.util.tag.PageModel;

import java.util.List;

@Controller
public class EmployeeController {
    /**
     * 自动注入hrmService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理查询请求
     */
    @RequestMapping(value = "/employee/selectEmployee")
    public String selectEmployee(Integer pageIndex,
                                 Integer job_id, Integer dept_id,
                                 @ModelAttribute Employee employee,
                                 Model model) {
        this.genericAssociation(job_id, dept_id, employee); // 模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
        PageModel pageModel = new PageModel(); // 创建分页对象
        if (pageIndex != null) { // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
            pageModel.setPageIndex(pageIndex);
        }

        List<Job> jobs = hrmService.findAllJob();  // 查询职位信息，用于模糊查询
        List<Dept> depts = hrmService.findAllDept();  // 查询部门信息 ，用于模糊查询
        List<Employee> employees = hrmService.findEmployee(employee, pageModel);  // 查询员工信息

        model.addAttribute("employees", employees);  // 设置Model数据
        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);

        return "employee/employee";  // 返回员工页面
    }

    /**
     * 处理添加员工请求
     */
    @RequestMapping(value = "/employee/addEmployee")
    public ModelAndView addEmployee(
            String flag,
            Integer job_id, Integer dept_id,
            @ModelAttribute Employee employee,
            ModelAndView mv) {
        if (flag.equals("1")) {
            List<Job> jobs = hrmService.findAllJob();  // 查询职位信息
            List<Dept> depts = hrmService.findAllDept(); // 查询部门信息
            mv.addObject("jobs", jobs); // 设置Model数据
            mv.addObject("depts", depts);
            mv.setViewName("employee/showAddEmployee"); // 返回添加员工页面
        } else {
            this.genericAssociation(job_id, dept_id, employee); // 判断是否有关联对象传递，如果有，创建关联对象
            hrmService.addEmployee(employee);  // 添加操作
            mv.setViewName("redirect:/employee/selectEmployee"); // 设置客户端跳转到查询请求
        }

        return mv;  // 返回

    }

    /**
     * 处理删除员工请求
     */
    @RequestMapping(value = "/employee/removeEmployee")
    public ModelAndView removeEmployee(String ids, ModelAndView mv) {
        String[] idArray = ids.split(","); // 分解id字符串
        for (String id : idArray) {
            hrmService.removeEmployeeById(Integer.parseInt(id));  // 根据id删除员工
        }
        // 设置客户端跳转到查询请求
        // mv.setView(new RedirectView("/hrmapp/employee/selectEmployee"));
        // mv.setViewName("forward:/employee/selectEmployee");
        mv.setViewName("redirect:/employee/selectEmployee");

        return mv; // 返回ModelAndView
    }

    /**
     * 处理修改员工请求
     */
    @RequestMapping(value = "/employee/updateEmployee")
    public ModelAndView updateEmployee(
            String flag,
            Integer job_id, Integer dept_id,
            @ModelAttribute Employee employee,
            ModelAndView mv) {
        if (flag.equals("1")) {
            Employee target = hrmService.findEmployeeById(employee.getId());  // 根据id查询员工
            List<Job> jobs = hrmService.findAllJob(); // 需要查询职位信息
            List<Dept> depts = hrmService.findAllDept(); // 需要查询部门信息
            mv.addObject("jobs", jobs);  // 设置Model数据
            mv.addObject("depts", depts);
            mv.addObject("employee", target);
            mv.setViewName("employee/showUpdateEmployee"); // 返回修改员工页面
        } else {
            this.genericAssociation(job_id, dept_id, employee);  // 创建并封装关联对象
            System.out.println("updateEmployee -->> " + employee);
            hrmService.modifyEmployee(employee);  // 执行修改操作
            mv.setViewName("redirect:/employee/selectEmployee");  // 设置客户端跳转到查询请求
        }

        return mv; // 返回
    }

    /**
     * 由于部门和职位在Employee中是对象关联映射，
     * 所以不能直接接收参数，需要创建Job对象和Dept对象
     */
    private void genericAssociation(Integer job_id,
                                    Integer dept_id, Employee employee) {
        if (job_id != null) {
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if (dept_id != null) {
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }

}

