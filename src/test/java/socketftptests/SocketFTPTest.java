package socketftptests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import socketftp.SocketFTP;
import utilities.Configurations;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by akash on 09/10/17.
 */
public class SocketFTPTest {
    private SocketFTP socketFTP;

    private void waitForServerThread() {
        try {
            Thread.sleep(500);  // To allow the localSocketThread to run first at all costs.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        socketFTP = new SocketFTP();
    }

    @After
    public void tearDown() {
        socketFTP.close();
    }

    @Test
    public void testConstructor() {
        Assert.assertNull(socketFTP.getLocalSocket());
        Assert.assertNull(socketFTP.getRemoteSocket());
    }

    @Test
    public void testConnectTo() {
        String randomIP = "127.0.0.1";
        int port = 12345;
        try {
            socketFTP.connectTo(randomIP, port);
            Assert.fail("Expected IOException, but found none.");
        } catch (IOException e) {}
    }

    @Test
    public void testListenToRequests() throws Exception {
        Thread localSocketThread = new Thread(() -> socketFTP.listenForRequests());
        localSocketThread.start();
        waitForServerThread();

        Assert.assertNotNull(socketFTP.getLocalSocket());
        try {
            // This should throw an exception as port will already be in use.
            new ServerSocket(Configurations.LOCAL_SOCKET_PORT);
            Assert.fail("Expected IOException, but found none.");
        } catch (IOException e) {}
    }

    @Test
    public void testConnectivity() {
        Thread serverThread = new Thread(() -> {
            socketFTP.listenForRequests();
            Assert.assertNotNull(socketFTP.getRemoteSocket());
        });
        serverThread.start();
        waitForServerThread();

        try {
            socketFTP.connectTo("127.0.0.1", Configurations.LOCAL_SOCKET_PORT);
        } catch (IOException e) {
            Assert.fail("IOException was not expected");
            e.printStackTrace();
        }
    }
}