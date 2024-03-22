package com.electricity.controller;

import com.electricity.model.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Title: FileUploadController
 * @Author JiaoWei
 * @Package com.myblog.controller
 * @Date 2024/3/12 17:01
 * @description: 文件上传（本地）
 */

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @PostMapping("/local/img")
    public Result<String> upload(MultipartFile file) throws IOException {
        //把文件的内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        //保证文件的名字唯一而防止被覆盖
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("C:\\Users\\JiaoWei\\Desktop\\img\\" + filename));
        return Result.success("url访问地址...");
    }
}
