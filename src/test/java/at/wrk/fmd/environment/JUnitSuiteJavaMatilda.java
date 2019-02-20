package at.wrk.fmd.environment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import at.wrk.fmd.web.EinheitentypControllerTest;
import at.wrk.fmd.web.LagerstandortControllerTest;
import at.wrk.fmd.web.MainControllerTest;
import at.wrk.fmd.web.MaterialControllerTest;
import at.wrk.fmd.web.MaterialTypControllerTest;
import at.wrk.fmd.web.MaterialtypEinheitentypControllerTest;
import at.wrk.fmd.web.MitarbeiterTypControllerTest;
import at.wrk.fmd.web.MitarbeitertypEinheitentypControllerTest;
import at.wrk.fmd.web.MitarbeiterverwaltungControllerTest;
import at.wrk.fmd.web.RestWebControllerTest;
import at.wrk.fmd.web.UserControllerTest;
import at.wrk.fmd.web.UserRegistrationControllerTest;
import at.wrk.fmd.web.UserRegistrationTest;
import at.wrk.fmd.web.VeranstaltungsControllerTest;
import at.wrk.fmd.website.ApplicationPropertiesTest;
import at.wrk.fmd.website.LoginWebsiteTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ApplicationPropertiesTest.class,
    LoginWebsiteTest.class,
    EinheitentypControllerTest.class,
    LagerstandortControllerTest.class,
    MainControllerTest.class,
    MaterialControllerTest.class,
    MaterialTypControllerTest.class,
    MaterialtypEinheitentypControllerTest.class,
    MitarbeiterTypControllerTest.class,
    MitarbeitertypEinheitentypControllerTest.class,
    MitarbeiterverwaltungControllerTest.class,
    RestWebControllerTest.class,
    UserControllerTest.class,
    UserRegistrationControllerTest.class,
    UserRegistrationTest.class,
    VeranstaltungsControllerTest.class
})
public class JUnitSuiteJavaMatilda {
}
