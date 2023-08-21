package demo.file.testfileupload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import demo.file.testfileupload.pojo.fileupload;
import demo.file.testfileupload.service.FileServiceImpl;

@Controller
public class FileController {
    
    @Autowired
    public FileServiceImpl fileService;

    @GetMapping("/")
    public String getUploadPage(){
        return "uploadfile";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String processupload(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        byte[] content = file.getBytes();
        String name = file.getName();
        String filetype = file.getContentType();
        fileupload Fileupload = new fileupload(0, content, name, filetype);
        fileService.saveFile(Fileupload);
        return "filetype";
    }
}
