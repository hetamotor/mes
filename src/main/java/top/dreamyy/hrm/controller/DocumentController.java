package top.dreamyy.hrm.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.dreamyy.hrm.domain.Document;
import top.dreamyy.hrm.domain.Notice;
import top.dreamyy.hrm.domain.User;
import top.dreamyy.hrm.service.HrmService;
import top.dreamyy.hrm.util.common.HrmConstants;
import top.dreamyy.hrm.util.tag.PageModel;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class DocumentController {
    /**
     * 自动注入UserService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理/login请求
     */
    @RequestMapping(value = "/document/selectDocument")
    public String selectDocument(
            Model model, Integer pageIndex,
            @ModelAttribute Document document) {
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        List<Document> documents = hrmService.findDocument(document, pageModel); // 查询用户信息

        model.addAttribute("documents", documents);
        model.addAttribute("pageModel", pageModel);
        return "document/document";
    }

    /**
     * 处理添加请求
     */
    @RequestMapping(value = "/document/addDocument")
    public ModelAndView addDocument(
            String flag,
            @ModelAttribute Document document,
            ModelAndView mv,
            HttpSession session) throws Exception {
        if (flag.equals("1")) {
            // 根据id查询编码规则
            Notice target = hrmService.findNoticeById(document.getId());
            mv.addObject("notice", target); // 设置Model数据

            // 查找该编码规则的最大文档值
            String maxfn = hrmService.findMaxDocumentByTitle(target.getTitle());  // 根据id查询文档
            mv.addObject("dbf", maxfn);
            mv.setViewName("document/showAddDocument");
        } else {
            /*String path = session.getServletContext().getRealPath("/upload/");// 上传文件路径
            System.out.println(path);
            String fileName = document.getFile().getOriginalFilename(); // 上传文件名
            document.getFile().transferTo(new File(path + File.separator + fileName)); // 将上传文件保存到一个目标文件当中*/

            // 插入数据库
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION); // 设置关联的User对象
            document.setUser(user);
            hrmService.addDocument(document); // 插入数据库
            mv.setViewName("redirect:/document/selectDocument");  // 返回
        }

        return mv;  // 返回
    }

    /**
     * 处理删除文档请求
     */
    @RequestMapping(value = "/document/removeDocument")
    public ModelAndView removeDocument(String ids, ModelAndView mv) {
        String[] idArray = ids.split(",");  // 分解id字符串
        for (String id : idArray) {
            hrmService.removeDocumentById(Integer.parseInt(id)); // 根据id删除文档
        }
        mv.setViewName("redirect:/document/selectDocument"); // 设置客户端跳转到查询请求
        return mv; // 返回ModelAndView
    }

    /**
     * 处理修改文档请求
     */
    @RequestMapping(value = "/document/updateDocument")
    public ModelAndView updateDocument(
            String flag,
            @ModelAttribute Document document,
            ModelAndView mv) {
        if (flag.equals("1")) {
            Document target = hrmService.findDocumentById(document.getId());  // 根据id查询文档
            mv.addObject("document", target); // 设置Model数据
            mv.setViewName("document/showUpdateDocument"); // 设置跳转到修改页面
        } else {
            hrmService.modifyDocument(document);  // 执行修改操作
            mv.setViewName("redirect:/document/selectDocument"); // 设置客户端跳转到查询请求
        }
        return mv; // 返回
    }

    /**
     * 处理文档下载请求
     */
    @RequestMapping(value = "/document/downLoad")
    public ResponseEntity<byte[]> downLoad(
            Integer id,
            HttpSession session) throws Exception {
        Document target = hrmService.findDocumentById(id);  // 根据id查询文档
        String fileName = target.getFileName();
        String path = session.getServletContext().getRealPath("/upload/"); // 上传文件路径
        File file = new File(path + File.separator + fileName); // 获得要下载文件的File对象

        HttpHeaders headers = new HttpHeaders(); // 创建springframework的HttpHeaders对象
        String downloadFielName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1); // 下载显示的文件名，解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", downloadFielName); // 通知浏览器以attachment（下载方式）打开图片
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // application/octet-stream ： 二进制流数据（最常见的文件下载）。

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED); // 201 HttpStatus.CREATED
    }
}

