package demo.file.testfileupload.controller;

import java.io.IOException;
// import java.util.Optional;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import demo.file.testfileupload.pojo.fileupload;
import demo.file.testfileupload.repository.FileRepository;
import demo.file.testfileupload.service.FileServiceImpl;

@Controller
public class FileController {
    
    @Autowired
    public FileServiceImpl fileService;

    @Autowired
    public FileRepository filerepo;

    @GetMapping("/")
    public String getUploadPage(){
        return "uploadfile";
    }

    @PostMapping("/upload")
    public String processupload(@RequestParam("file") MultipartFile file,Model model) throws IOException{
         byte[] content = file.getBytes();
         byte[] image = file.getBytes();
        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        String filetype = file.getContentType();
        fileupload Fileupload = new fileupload(0, content,image, name, filetype);
        Fileupload.setName(originalFilename);
        fileService.saveFile(Fileupload);
        model.addAttribute("filedownload", fileService.getAllFiles());
        // Create a new Document entity and set the filename
        // fileupload document = new fileupload();
        // Save the document entity to the database
        // filerepo.save(document);
        return "Downloadfile";
    }

    @GetMapping("/{id}")
    public void Downloadfile (@PathVariable Long id,HttpServletResponse response) throws IOException{

        // Optional<fileupload> documentOptional = filerepo.findById(id);   

        // if (documentOptional.isPresent()) {
            // fileupload document = documentOptional.get();
            fileupload file = fileService.getFiles(id);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.getOutputStream().write(file.getContent());
            response.getOutputStream().close(); 
        // fileupload file = fileService.getFiles(id);
        // HttpHeaders headers = new HttpHeaders();
        // headers.add(HttpHeaders.CONTENT_TYPE,file.getFiletype());
        // headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachements; filetype="+file.getFiletype());
        // return ResponseEntity.ok()
        // .headers(headers)
        // .body(file.getContent());
    }
@GetMapping("/uploads")
    public String uploadImageForm(Model model) {
        model.addAttribute("image", new fileupload());
        return "upload";
    }

    @PostMapping("/uploades")
    public String uploadImage(@ModelAttribute fileupload image) {
        filerepo.save(image);
        return "redirect:/images";
    }

    @GetMapping("/images")
    public String listImages(Model model) {
        List<fileupload> images = filerepo.findAll();
        model.addAttribute("images", images);
        return "imagefile";
    }

    @GetMapping("/image/{id}")
public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
    Optional<fileupload> image = filerepo.findById(id);
    if (image.isPresent()) {
        byte[] imageData = image.get().getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Change content type as needed
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
