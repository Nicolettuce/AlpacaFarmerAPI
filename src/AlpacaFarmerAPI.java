import exceptions.NoInternetConnectionException;

/**
 * Created by ndh13 on 23/11/14.
 */
public interface AlpacaFarmerAPI {

    boolean createNewUser(String username, String password) throws NoInternetConnectionException;

    boolean logInUser(String username, String password) throws NoInternetConnectionException;


}
