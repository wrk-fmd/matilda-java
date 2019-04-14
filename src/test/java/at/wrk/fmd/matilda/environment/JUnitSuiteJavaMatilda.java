package at.wrk.fmd.matilda.environment;

import at.wrk.fmd.matilda.repository.BuchungRepositoryTest;
import at.wrk.fmd.matilda.web.EinheitentypControllerTest;
import at.wrk.fmd.matilda.web.LagerstandortControllerTest;
import at.wrk.fmd.matilda.web.MaterialControllerTest;
import at.wrk.fmd.matilda.web.MaterialTypControllerTest;
import at.wrk.fmd.matilda.web.MitarbeiterTypControllerTest;
import at.wrk.fmd.matilda.web.MitarbeiterverwaltungControllerTest;
import at.wrk.fmd.matilda.web.UserControllerTest;
import at.wrk.fmd.matilda.web.VeranstaltungsControllerTest;
import at.wrk.fmd.matilda.website.ApplicationPropertiesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
