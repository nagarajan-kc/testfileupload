package demo.file.testfileupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.file.testfileupload.pojo.fileupload;
import demo.file.testfileupload.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    public FileRepository fileRepository;

    @Override
    public void saveFile(fileupload Fileupload) {
        fileRepository.save(Fileupload);
    }

    @Override
    public List<fileupload> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public fileupload getFiles(Long id) {
      return fileRepository.findById(id).get();
    }
    
}
