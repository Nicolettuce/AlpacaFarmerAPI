import exceptions.*;
import org.junit.Test;

/**
 * Created by ndh13 on 28/11/14.
 */
public class AlpacaFarmerServerAPITests {

    AlpacaFarmerAPI api = new AlpacaFarmerServerAPI("127.0.0.1", 7777);

    @Test(expected= UnsuitableUsernameException.class)
    public void creatingANonAlphaNumericUsernameThrowsAnException() throws AlpacaFarmerAPIException {
        api.createNewUser("**Headphones Guy**", "iluvanimeporn");
    }

    @Test(expected= UnsuitableUsernameException.class)
    public void creatingAVeryShortUsernameThrowsAnException() throws AlpacaFarmerAPIException {
        api.createNewUser("PG", "pissguyrules");
    }

    @Test(expected= UnsuitableUsernameException.class)
    public void creatingAVeryLongUsernameThrowsAnException() throws AlpacaFarmerAPIException {
        api.createNewUser("HeadphonesGuyTheWorldsCoolestGuy", "lololol777");
    }

    @Test(expected= UnsuitablePasswordException.class)
    public void creatingANonAlphaNumericPasswordThrowsAnException() throws AlpacaFarmerAPIException {
        api.createNewUser("Dickhead", "i_ruv_dickz£££");
    }

    @Test(expected= UnsuitablePasswordException.class)
    public void creatingAVeryShortPasswordThrowsAnException() throws AlpacaFarmerAPIException {
        api.createNewUser("Dickhead", "a");
    }

    @Test(expected= UnsuitablePasswordException.class)
    public void creatingAVeryLongPasswordThrowsAnException() throws AlpacaFarmerAPIException {
        api.createNewUser("Dickhead", "thequickbrownfoxjumpsoverthelazydog");
    }

    @Test(expected= UsernameAlreadyExistsException.class)
    public void tryingToCreateUserWhenUsernameAlreadyExistsThrowsAnException() throws AlpacaFarmerAPIException {
        api.createNewUser("headphonesguy", "iluvanimeporn");
        api.createNewUser("headphonesguy", "animebabez");
    }

    @Test(expected= NoExistingUsernameException.class)
    public void tryingToLogInUserWhenUsernameDoesNotExistThrowsAnException() throws AlpacaFarmerAPIException {
        api.logInUser("winston", "narankerishot");
    }

    @Test(expected= IncorrectPasswordException.class)
    public void tryingToLogInUserWithIncorrectPasswordThrowsAnException() throws AlpacaFarmerAPIException {
        api.logInUser("headphonesguy", "animebabez");
    }

}
