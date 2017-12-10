package org.arc.ytpet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    /*
    * @ method uploadImage
    * @ return boolean
    * @ imagepath '/attached/petimg'
    *
    * */
    @RequestMapping("")
    public String uploadImage(CommonsMultipartResolver file, HttpServletRequest request){
        //首先获取文件名  获取文件  准备好文件存储的位置,将文件存储进去.
        return "";
    }

}
