package assertion;

import model.User;
import org.testng.asserts.SoftAssert;

public class UserAssertion {

    private UserAssertion() {

    }

    public static void checkValidUser(final User actualUser, final User expectedUser) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUser.getUsername(), expectedUser.getUsername(), "Error - incorrect username");
        softAssert.assertEquals(actualUser.getFirstName(), expectedUser.getFirstName(), "Error - incorrect first name");
        softAssert.assertEquals(actualUser.getLastName(), expectedUser.getLastName(), "Error - incorrect last name");
        softAssert.assertEquals(actualUser.getEmail(), expectedUser.getEmail(), "Error - incorrect email");
        softAssert.assertEquals(actualUser.getPhone(), expectedUser.getPhone(), "Error - incorrect phone");
        softAssert.assertAll();
    }

    public static void checkInvalidUser(final User actualUser) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNull(actualUser.getUsername(), "Error - incorrect username");
        softAssert.assertEquals(actualUser.getFirstName(), "Error - incorrect first name");
        softAssert.assertEquals(actualUser.getLastName(), "Error - incorrect last name");
        softAssert.assertEquals(actualUser.getEmail(), "Error - incorrect email");
        softAssert.assertEquals(actualUser.getPhone(), "Error - incorrect phone");
        softAssert.assertAll();
    }
}
