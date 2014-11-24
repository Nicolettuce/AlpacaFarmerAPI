import com.google.gson.Gson;
import exceptions.NoInternetConnectionException;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
            Map requestMap = new HashMap<String, Object>();
            requestMap.put("Method", "createNewUser");
            requestMap.put("Username", username);
            requestMap.put("Password", password);
            Gson gson = new Gson();
            out.writeUTF(gson.toJson(requestMap));
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            if (in.readUTF().equals("true")) {
                return true;
            }
        } catch (IOException e) {
            throw new NoInternetConnectionException();
        }
        return false;
    }

    @Override
    public boolean logInUser(String username, String password) throws NoInternetConnectionException {
        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            Map requestMap = new HashMap<String, Object>();
            requestMap.put("Method", "logInUser");
            requestMap.put("Username", username);
            requestMap.put("Password", password);
            Gson gson = new Gson();
            out.writeUTF(gson.toJson(requestMap));
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            if (in.readUTF().equals("true")) {
                return true;
            }
        } catch (IOException e) {
            throw new NoInternetConnectionException();
        }
        return false;
    }
}
