package demo.file.testfileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.file.testfileupload.pojo.fileupload;

@Repository
public interface FileRepository extends JpaRepository<fileupload,Long>{
    
}
