package at.wrk.fmd.matilda.environment;

import at.wrk.fmd.matilda.selenium.BenutzerregistrierungSeleniumTest;
import at.wrk.fmd.matilda.selenium.EinheitentypSeleniumTest;
import at.wrk.fmd.matilda.selenium.LagerstandortWebsiteSeleniumTest;
import at.wrk.fmd.matilda.selenium.LoginWebsiteSeleniumTest;
import at.wrk.fmd.matilda.selenium.MaterialSeleniumTest;
import at.wrk.fmd.matilda.selenium.MaterialtypSeleniumTest;
import at.wrk.fmd.matilda.selenium.MitarbeitertypSeleniumTest;
import at.wrk.fmd.matilda.selenium.MitarbeiterverwaltungWebsiteSeleniumTest;
import at.wrk.fmd.matilda.selenium.PasswordAenderungSeleniumTest;
import at.wrk.fmd.matilda.selenium.ReportWebsiteSeleniumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        LoginWebsiteSeleniumTest.class,
        MaterialSeleniumTest.class,
        LagerstandortWebsiteSeleniumTest.class,
        BenutzerregistrierungSeleniumTest.class,
        MitarbeitertypSeleniumTest.class,
        PasswordAenderungSeleniumTest.class,
        EinheitentypSeleniumTest.class,
        MaterialtypSeleniumTest.class,
        ReportWebsiteSeleniumTest.class,
        MitarbeiterverwaltungWebsiteSeleniumTest.class
})

public class SeleniumSuite {
}
