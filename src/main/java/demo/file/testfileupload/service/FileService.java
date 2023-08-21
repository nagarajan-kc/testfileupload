package demo.file.testfileupload.service;


import java.util.List;

import demo.file.testfileupload.pojo.fileupload;

public interface FileService {
    public void saveFile(fileupload Fileupload);
    public List<fileupload>getAllFiles();
    public fileupload getFiles(Long id);

}
