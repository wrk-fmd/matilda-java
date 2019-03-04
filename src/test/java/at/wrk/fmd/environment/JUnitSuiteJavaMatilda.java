package at.wrk.fmd.environment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import at.wrk.fmd.repository.BuchungRepositoryTest;
import at.wrk.fmd.web.EinheitentypControllerTest;
import at.wrk.fmd.web.LagerstandortControllerTest;
import at.wrk.fmd.web.MaterialControllerTest;
import at.wrk.fmd.web.MaterialTypControllerTest;
import at.wrk.fmd.web.MitarbeiterTypControllerTest;
import at.wrk.fmd.web.MitarbeiterverwaltungControllerTest;
import at.wrk.fmd.web.UserControllerTest;
import at.wrk.fmd.web.VeranstaltungsControllerTest;
import at.wrk.fmd.website.ApplicationPropertiesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ApplicationPropertiesTest.class, //
    BuchungRepositoryTest.class,
    MaterialTypControllerTest.class,
    EinheitentypControllerTest.class, //
    LagerstandortControllerTest.class, //
    MaterialControllerTest.class, //
    MitarbeiterTypControllerTest.class, //
    MitarbeiterverwaltungControllerTest.class,
    UserControllerTest.class,
    VeranstaltungsControllerTest.class //
})
public class JUnitSuiteJavaMatilda {
}
