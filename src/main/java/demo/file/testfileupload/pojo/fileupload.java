package demo.file.testfileupload.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="file")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class fileupload {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    @Lob
    private byte[] content;

    @Column
    private String name;

    @Column
    private String filetype;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return this.content;
    }

    public void setFile(byte[] file) {
        this.content = file;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFiletype() {
        return this.filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }


}
