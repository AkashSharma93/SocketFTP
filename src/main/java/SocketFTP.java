import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by akash on 09/10/17.
 */
public class SocketFTP {
    private ServerSocket localSocket;
    private Socket remoteSocket;

    public ServerSocket getLocalSocket() {
        return localSocket;
    }

    public Socket getRemoteSocket() {
        return remoteSocket;
    }
}
