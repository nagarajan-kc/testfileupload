package demo.file.testfileupload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String processupload(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        byte[] content = file.getBytes();
        String name = file.getName();
        String filetype = file.getContentType();
        fileupload Fileupload = new fileupload(0, content, name, filetype);
        fileService.saveFile(Fileupload);
        model.addAttribute("filedownload", fileService.getAllFiles());
        return "Downloadfile";
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getfiles(@PathVariable Long id){
        fileupload file = fileService.getFiles(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,file.getFiletype());
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachements; filename="+file.getFiletype());
        return ResponseEntity.ok()
        .headers(headers)
        .body(file.getContent());
    }
}
