package utilities;

import java.io.Closeable;
import java.io.IOException;

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
}
