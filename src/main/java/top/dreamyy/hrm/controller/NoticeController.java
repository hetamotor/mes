package top.dreamyy.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.dreamyy.hrm.domain.Notice;
import top.dreamyy.hrm.domain.User;
import top.dreamyy.hrm.service.HrmService;
import top.dreamyy.hrm.util.common.HrmConstants;
import top.dreamyy.hrm.util.tag.PageModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    /**
     * 自动注入UserService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理/login请求
     */
    @RequestMapping(value = "/notice/selectNotice")
    public String selectNotice(Model model, Integer pageIndex,
                               @ModelAttribute Notice notice, HttpServletRequest request) throws ParseException {
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        String starttime = (String) request.getParameter("starttime");
        String endtime = (String) request.getParameter("endtime");
        if (starttime != null && !starttime.trim().equals("")) {
            starttime = starttime + " 00:00:00";
        }
        if (endtime != null && !endtime.trim().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date end = sdf.parse(endtime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(end);
            cal.add(Calendar.DATE, 1);
            Date newend = cal.getTime();
            endtime = sdf.format(newend) + " 00:00:00";
        }
        System.out.println("request starttime = " + starttime);
        System.out.println("request endtime = " + endtime);

        List<Notice> notices = hrmService.findNotice(notice, pageModel, starttime, endtime); // 查询用户信息
        model.addAttribute("notices", notices);
        model.addAttribute("pageModel", pageModel);

        return "notice/notice";
    }

    /**
     * 处理添加请求
     */
    @RequestMapping(value = "/notice/previewNotice")
    public String previewNotice(
            Integer id, Model model) {
        Notice notice = hrmService.findNoticeById(id);
        model.addAttribute("notice", notice);
        return "previewPart";  // 返回
    }

    /**
     * 处理删除公告请求
     */
    @RequestMapping(value = "/notice/removeNotice")
    public ModelAndView removeNotice(String ids, ModelAndView mv) {
        String[] idArray = ids.split(","); // 分解id字符串
        for (String id : idArray) {
            hrmService.removeNoticeById(Integer.parseInt(id)); // 根据id删除公告
        }
        mv.setViewName("redirect:/notice/selectNotice");  // 设置客户端跳转到查询请求
        return mv; // 返回ModelAndView
    }

    /**
     * 处理添加请求
     */
    @RequestMapping(value = "/notice/addNotice")
    public ModelAndView addNotice(
            String flag,
            @ModelAttribute Notice notice,
            ModelAndView mv,
            HttpSession session) {
        if (flag.equals("1")) {
            mv.setViewName("notice/showAddNotice");
        } else {
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            notice.setUser(user);
            hrmService.addNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        return mv; // 返回
    }

    /**
     * 处理添加请求
     */
    @RequestMapping(value = "/notice/updateNotice")
    public ModelAndView updateNotice(
            String flag,
            @ModelAttribute Notice notice,
            ModelAndView mv) {
        if (flag.equals("1")) {
            Notice target = hrmService.findNoticeById(notice.getId());
            mv.addObject("notice", target);
            mv.setViewName("notice/showUpdateNotice");
        } else {
            hrmService.modifyNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        return mv;   // 返回
    }
}
