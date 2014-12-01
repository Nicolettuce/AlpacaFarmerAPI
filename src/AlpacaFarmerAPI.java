import exceptions.AlpacaFarmerAPIException;

/**
 * Created by ndh13 on 23/11/14.
 */
public interface AlpacaFarmerAPI {

    void createNewUser(String username, String password) throws AlpacaFarmerAPIException;

    String logInUser(String username, String password) throws AlpacaFarmerAPIException;

    void logOutUser(String sessionToken) throws AlpacaFarmerAPIException;


}
