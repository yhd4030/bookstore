package com.bookstore.haid.controller.admin;


import com.bookstore.haid.dto.BookMsgDTO;
import com.bookstore.haid.model.BookMsg;
import com.bookstore.haid.model.BookPublish;
import com.bookstore.haid.model.Category;
import com.bookstore.haid.model.ExecuteResult;
import com.bookstore.haid.service.ProductInfoService;
import com.bookstore.haid.service.ProductListService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
    @Autowired
    private ProductListService productListService;

    @Autowired
    private ProductInfoService productInfoService;


    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "static/upload/images";


    @RequestMapping("/list")
    public String productList(Model model,
                              @RequestParam(value = "errorMsg", required = false) String errorMsg,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                              @RequestParam(value = "keywords", defaultValue = "", required = false) String keywords,
                              HttpServletRequest request) {
        PageInfo<BookMsg> pageInfo = productListService.findAllProduct(pageNum, pageSize, keywords);
        if (keywords != null && !keywords.equals("")) {
            request.setAttribute("keywords", keywords);
        } else {
            request.setAttribute("keywords", "");
        }
        model.addAttribute("productList", pageInfo);
        return "admin/product/list";
    }

    @PostMapping("/updateIsShelf")
    @ResponseBody
    public boolean updateIsShelf(BookMsg bookMsg) {
        System.out.println(bookMsg);
        return productListService.updateIsShelf(bookMsg);

    }

    //
    @GetMapping("/edit")
    public String productEdit(Integer bookId, Model model) {
        BookMsgDTO bookMsgDTO = productInfoService.findProduct(bookId);
        List<Category> categories = productInfoService.findCategory();
        model.addAttribute("ProductInfo", bookMsgDTO);
        model.addAttribute("categories", categories);
        return "admin/product/edit";
    }

    @RequestMapping("/deletion")
    @ResponseBody
    public ExecuteResult delete(Integer id) {
        try {
            boolean delete = productListService.deleteBookById(id);
            if (delete) {
                return new ExecuteResult(true, "删除成功");
            }
            return new ExecuteResult(false, "删除失败");
        } catch (Exception e) {
            return new ExecuteResult(false, "删除异常");
        }
    }
//
    @GetMapping("/add")
    public String productAdd(Model model) {
        List<Category> categories = productInfoService.findCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("ProductInfo", new BookMsgDTO(new BookPublish(), new Category()));
        return "admin/product/edit";
    }
//
    @PostMapping("/editOrAdd")
    public String productUpdateOrSave(BookMsgDTO bookMsgDTO,
                                      MultipartFile pictureFile,
                                      HttpServletRequest request, Model model) {
        if (pictureFile != null && pictureFile.getSize() != 0) {
            String imgUrl = uploadPicture(pictureFile, request);
            bookMsgDTO.setImgUrl(imgUrl);
        }
        try {
            productListService.updateOrAddProduct(bookMsgDTO);
        } catch (Exception e) {
            List<Category> categories = productInfoService.findCategory();
            model.addAttribute("categories", categories);
            Category category = new Category();
            if (bookMsgDTO != null) {
                if (bookMsgDTO.getTypeId() != null) {
                    for (Category cItem : categories) {
                        if (cItem.getId() == bookMsgDTO.getTypeId()) {
                            category.setId(cItem.getId());
                            category.setType(cItem.getType());
                        }
                    }
                }
            }
            model.addAttribute("ProductInfo", new BookMsgDTO(new BookPublish(), category));
            model.addAttribute("errorMsg", e.getMessage());
            return "/admin/product/edit";
        }

        return "redirect:/admin/product/list";

    }


    private String uploadPicture(MultipartFile pictureFile, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File file = new File(realPath + format);

        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = pictureFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            pictureFile.transferTo(newFile);
            String filePath = "/upload/images" + format + newName;
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }


}
