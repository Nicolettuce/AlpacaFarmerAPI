import exceptions.AlpacaFarmerAPIException;

/**
 * Created by ndh13 on 23/11/14.
 */
public class Main {

    public static void main(String[] args) throws AlpacaFarmerAPIException {
        AlpacaFarmerAPI api = new AlpacaFarmerServerAPI("127.0.0.1", 7777);
    }

}
