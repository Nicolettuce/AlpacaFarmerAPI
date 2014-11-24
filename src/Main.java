import exceptions.NoInternetConnectionException;

/**
 * Created by ndh13 on 23/11/14.
 */
public class Main {

    public static void main(String[] args) throws NoInternetConnectionException {
        AlpacaFarmerAPI api = new AlpacaFarmerServerAPI("127.0.0.1", 7777);
        boolean success;
//        success = api.createNewUser("winstoncat", "ilovenaranker");
//        System.out.println(success);
//        success = api.createNewUser("winstoncat", "ilovenaranker");
//        System.out.println(success);
        success = api.createNewUser("nicolecat", "chinchilla");
        System.out.println(success);
        success = api.logInUser("nicolecat", "chinchilla");
        System.out.println(success);
        success = api.logInUser("tomcat", "MIAOW");
        System.out.println(success);
        success = api.logInUser("nicolecat", "poopoo");
        System.out.println(success);

    }

}
