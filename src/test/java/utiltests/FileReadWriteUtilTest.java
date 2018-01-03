package utiltests;

import com.akash.kakashi.socketftp.SocketFTP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileReadWriteUtilTest {
    private SocketFTP socketFTP;

    @Before
    public void setUp() {
        socketFTP = new SocketFTP();
    }

    @After
    public void tearDown() {
        socketFTP.close();
    }

    @Test
    public void testReadFileData() {
        
    }
}
