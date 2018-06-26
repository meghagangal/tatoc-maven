package com.org.learningMaven.myMavenProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AssignmentTest{
	
	WebDriver driver;
	HomePageActions homepage;
	GridGatePageAction gridpage;
	FrameDungeonPageActions frameDungeonPage;
	DragAroundPageActions dragAroundPage;
	PopupWindowsPageActions popupWindowsPage;
	CookieHandlingPageActions cookieHandlingPage;
	
	@BeforeTest
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		driver.manage().window().maximize();
		homepage = new HomePageActions(driver);
		gridpage = new GridGatePageAction(driver);
		frameDungeonPage = new FrameDungeonPageActions(driver);
		dragAroundPage = new DragAroundPageActions(driver);
		popupWindowsPage = new PopupWindowsPageActions(driver);
		cookieHandlingPage = new CookieHandlingPageActions(driver);
	}
	
	@Test
	public void Step_01_Launch_Basic_Course() {
		homepage.launchPage("http://10.0.1.86/tatoc");
		homepage.verifyPageLauched();
		homepage.verifylinkIsDisplayed();
		homepage.clickOnBasicCourseLink();
	}
	
	@Test(dependsOnMethods = {"Step_01_Launch_Basic_Course"})
	public void Step_02_Launch_GridGate_Page() {
		gridpage.verifyPageLaunched();
		gridpage.verifyBoxesAreDisplayed();
		gridpage.clickGreenBox();
		gridpage.verifyGreenBoxIsClicked("http://10.0.1.86/tatoc/basic/frame/dungeon");
	}
	@Test(dependsOnMethods = {"Step_02_Launch_GridGate_Page"})
	public void Step_03_Launch_Frame_Dungeon_Page() {
		frameDungeonPage.verifyFrameDungeonPageLaunched();
		frameDungeonPage.verifyBoxesAreDisplayed();
		frameDungeonPage.verifyLinksAreDisplayed();
		frameDungeonPage.matchBoxesColorByClickingRepaintBoxLink();
		frameDungeonPage.clickProceedLink();
		frameDungeonPage.verifyProceedLinkIsClicked("http://10.0.1.86/tatoc/basic/drag");
	}
	@Test(dependsOnMethods = {"Step_03_Launch_Frame_Dungeon_Page"})  
	public void Step_04_Launch_Drag_Around_page() {
		dragAroundPage.verifyDragAroundPageLaunched();
		dragAroundPage.verifyBoxesAreDisplayed();
		dragAroundPage.verifyLinksAreDisplayed();
		dragAroundPage.performDragOperation();
		dragAroundPage.clickProceedLink();
		dragAroundPage.verifyProceedLinkIsClicked("http://10.0.1.86/tatoc/basic/windows");
	
		}
	@Test(dependsOnMethods = {"Step_04_Launch_Drag_Around_page"})
	public void Step_05_Launch_Pop_Up_Windows_page() {
		popupWindowsPage.verifyPopupWindowsPageLaunched();
		popupWindowsPage.verifyLinksAreDisplayed();
		popupWindowsPage.clickOnLaunchPopupWindowLink();
		popupWindowsPage.verifyNewWindowLaunched("http://10.0.1.86/tatoc/basic/windows/popup");
		popupWindowsPage.sendKeysToNewWindowsTextFieldAndClickSubmit();
		popupWindowsPage.clickProceedLinkOnPreviousWindow();
		popupWindowsPage.verifyProceedLinkIsClicked("http://10.0.1.86/tatoc/basic/cookie");
	}
	@Test(dependsOnMethods = {"Step_05_Launch_Pop_Up_Windows_page"})
	public void Step_06_Launch_Cookie_Handling_Page() {
		cookieHandlingPage.verifyCookieHandlingPageLaunched();
		cookieHandlingPage.verifyLinksAreDisplayed();
		cookieHandlingPage.verifyTokenValueFieldIsDisplayed();
		cookieHandlingPage.clickGenerateTokenLink();
		cookieHandlingPage.verifyTokenIsGenerated();
		cookieHandlingPage.createCookie();
		cookieHandlingPage.clickProceedLink();
		cookieHandlingPage.verifyProceedLinkIsClicked("http://10.0.1.86/tatoc/end");
		
	}
			
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}

}
