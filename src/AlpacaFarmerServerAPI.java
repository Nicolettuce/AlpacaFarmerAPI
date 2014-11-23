import exceptions.NoInternetConnectionException;

import java.io.*;
import java.net.Socket;

/**
 * Created by ndh13 on 23/11/14.
 */
public class AlpacaFarmerServerAPI implements AlpacaFarmerAPI {

    private String serverName;
    private int port;

    public AlpacaFarmerServerAPI(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    @Override
    public boolean createNewUser(String username, String password) throws NoInternetConnectionException {
        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("blah blah to server");
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            if (in.readBoolean()) {
                return true;
            }
        } catch (IOException e) {
            throw new NoInternetConnectionException();
        }
        return false;
    }

    @Override
    public boolean logInUser(String username, String password) {
        return false;
    }
}
