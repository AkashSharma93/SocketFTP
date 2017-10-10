package socketftp;

import utilities.Configurations;
import utilities.Util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by akash on 09/10/17.
 */
public class SocketFTP {
    private ServerSocket localSocket;
    private Socket remoteSocket;

    private Util util = new Util();

    public ServerSocket getLocalSocket() {
        return localSocket;
    }

    public Socket getRemoteSocket() {
        return remoteSocket;
    }

    public void connectTo(String remoteIP, int port) throws IOException {
        remoteSocket = new Socket(remoteIP, port);
    }

    private void createLocalSocket() throws IOException {
        localSocket = new ServerSocket(Configurations.LOCAL_SOCKET_PORT);
    }

    public void listenForRequests() throws IOException {
        createLocalSocket();
        localSocket.accept();
    }

    public void close() {
        util.closeStream(localSocket);
        util.closeStream(remoteSocket);
    }
}
