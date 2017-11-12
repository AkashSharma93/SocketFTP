package helper;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by akash on 12/11/17.
 */
public class FileData implements Serializable {
    private String filePath;
    private ByteBuffer data;

    public FileData(String filePath, ByteBuffer data) {
        this.filePath = filePath;
        this.data = data;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ByteBuffer getData() {
        return data;
    }

    public void setData(ByteBuffer data) {
        this.data = data;
    }
}
