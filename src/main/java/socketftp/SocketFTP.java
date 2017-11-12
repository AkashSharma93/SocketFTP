package socketftp;

import helper.FileData;
import utilities.Configurations;
import utilities.Util;

import java.io.*;
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
        remoteSocket = localSocket.accept();
    }

    public void close() {
        util.closeStream(localSocket);
        util.closeStream(remoteSocket);
    }

    public void sendFile(String filePath) throws IOException {
        FileData fileData = util.readFileData(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(remoteSocket.getOutputStream());
        oos.writeObject(fileData);

        // Wait for ACK about read completion, then close the stream.
        ObjectInputStream ois = new ObjectInputStream(remoteSocket.getInputStream());
        ois.readInt();  // Dummy read.

        util.closeStream(oos);
        util.closeStream(ois);
    }

    public void receiveFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(remoteSocket.getInputStream());
        FileData fileData = (FileData) ois.readObject();
        util.writeFileData(fileData);

        // Send ACK and close the stream.
        ObjectOutputStream oos = new ObjectOutputStream(remoteSocket.getOutputStream());
        oos.writeInt(0);    // Dummy write.

        util.closeStream(ois);
        util.closeStream(oos);
    }
}
