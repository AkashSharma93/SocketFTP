package utilities;

import helper.FileData;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by akash on 09/10/17.
 */
public class Util {
    public void closeStream(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: Move this to FileUtil class.
    public FileData readFileData(String filePath) throws IOException {
        FileChannel fileChannel = new FileInputStream(filePath).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
        fileChannel.read(buffer);
        FileData fileData = new FileData(filePath, buffer);
        closeStream(fileChannel);

        return fileData;
    }

    // TODO: Move this to FileUtil class.
    public void writeFileData(FileData fileData) throws IOException {
        File file = new File(fileData.getFilePath());
        file.getParentFile().mkdirs();
        FileChannel fileChannel = new FileOutputStream(file).getChannel();
        fileChannel.write(fileData.getData());
        closeStream(fileChannel);
    }
}
