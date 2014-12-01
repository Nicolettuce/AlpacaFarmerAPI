import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import exceptions.*;

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
    private Map<Integer, AlpacaFarmerAPIException> errorMap;

    public AlpacaFarmerServerAPI(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
        errorMap = new HashMap<Integer, AlpacaFarmerAPIException>();
        populateErrorMap();
    }

    private void populateErrorMap() {
        errorMap.put(0, new UnsuitableUsernameException());
        errorMap.put(1, new UnsuitablePasswordException());
        errorMap.put(2, new UsernameAlreadyExistsException());
        errorMap.put(3, new NoExistingUsernameException());
        errorMap.put(4, new IncorrectPasswordException());
        errorMap.put(5, new InvalidSessionTokenException());

    }

    @Override
    public void createNewUser(String username, String password) throws AlpacaFarmerAPIException {
        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            JsonObject request = new JsonObject();
            request.add("Method", new JsonPrimitive("createNewUser"));
            request.add("Username", new JsonPrimitive(username));
            request.add("Password", new JsonPrimitive(password));
            out.writeUTF(request.toString());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            JsonObject success = new Gson().fromJson(in.readUTF(), JsonObject.class);
            if (!success.get("Success").getAsBoolean()) {
                throw errorMap.get(success.get("Error").getAsInt());
            }
        } catch (IOException e) {
            throw new NoInternetConnectionException();
        }
    }

    @Override
    public String logInUser(String username, String password) throws AlpacaFarmerAPIException {
        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            JsonObject request = new JsonObject();
            request.add("Method", new JsonPrimitive("logInUser"));
            request.add("Username", new JsonPrimitive(username));
            request.add("Password", new JsonPrimitive(password));
            out.writeUTF(request.toString());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            JsonObject token = new Gson().fromJson(in.readUTF(), JsonObject.class);
            if (token.get("Success").getAsBoolean()) {
                return token.get("Session_token").getAsString();
            }
            throw errorMap.get(token.get("Error").getAsInt());
        } catch (IOException e) {
            throw new NoInternetConnectionException();
        }
    }

    @Override
    public void logOutUser(String sessionToken) throws AlpacaFarmerAPIException {
        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            JsonObject request = new JsonObject();
            request.add("Method", new JsonPrimitive("logInUser"));
            request.add("Session_token", new JsonPrimitive(sessionToken));
            out.writeUTF(request.toString());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            JsonObject success = new Gson().fromJson(in.readUTF(), JsonObject.class);
            if (!success.get("Success").getAsBoolean()) {
                throw errorMap.get(success.get("Error").getAsInt());
            }
        } catch (IOException e) {
            throw new NoInternetConnectionException();
        }

    }
}
