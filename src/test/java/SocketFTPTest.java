import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by akash on 09/10/17.
 */
public class SocketFTPTest {
    SocketFTP socketFTP;

    @Before
    public void setUp() {
        socketFTP = new SocketFTP();
    }

    @Test
    public void testConstructor() {
        Assert.assertNull(socketFTP.getLocalSocket());
        Assert.assertNull(socketFTP.getRemoteSocket());
    }
}