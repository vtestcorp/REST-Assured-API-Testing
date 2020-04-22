package util;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;


public class Util {

	public static WebDriver driver = null;
	public static Properties prop; // Property file initialization
	public static int totalNoOfRows, rescode;
	public static Sheet sh;
	public static JsonPath jsonPathEvaluator;
	public static String responsebody;
	public static boolean flag, flag1, flag2, flag3, flag4;
	public static Response response;
	public static XmlPath jsonPathEvaluator1;
	public static String response1, TokenValue;
	public static StringBuffer response123;
	public static HSSFWorkbook wb, workbookinput, workbookoutput;
	public static HSSFSheet sh1;
	public static FileOutputStream fout;
	public static File src;
	public static FileInputStream fis, fs, ip;
	public static Workbook wb1;
	

	public Util() {
		try {
			prop = new Properties();
			ip = new FileInputStream("src/test/java/util/data.properties");
			prop.load(ip);
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}
	}

	public static void ReadData() throws BiffException, IOException {

		String FilePath = prop.getProperty("INPUT_FILE_NAME");
		fs = new FileInputStream(FilePath);
		wb1 = Workbook.getWorkbook(fs);

		// TO get the access to the sheet
		sh = wb1.getSheet(prop.getProperty("inputSheet"));

		// To get the number of rows present in sheet
		totalNoOfRows = sh.getRows();

	}

	public static void createSheetCopy() throws WriteException {
		try {
			// input source excel file which contains sheets to be copied
			FileInputStream file = new FileInputStream(prop.getProperty("INPUT_FILE_NAME"));
			workbookinput = new HSSFWorkbook(file);

			// output new excel file to which we need to copy the above sheets
			// this would copy entire workbook from source
			workbookoutput = workbookinput;

			// To write your changes to new workbook
			FileOutputStream out = new FileOutputStream(prop.getProperty("OUTPUT_FILE_NAME"));
			workbookoutput.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public static void beforeSuite() throws Exception, WriteException {

		try {

			// Specify the file path which you want to create or write
			src = new File(prop.getProperty("OUTPUT_FILE_NAME"));

			// Load the file
			fis = new FileInputStream(src);

			// load the workbook
			wb = new HSSFWorkbook(fis);

			// get the sheet which you want to modify or create
			sh1 = wb.getSheet("Sheet1");
			} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@AfterSuite
	public static void afterSuite() throws Exception, WriteException {

		try {
			fout.flush();
			fout.close();
			} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void writeStatus(int col, int row, String data) throws Exception, WriteException {

		try {

			sh1.getRow(row).createCell(col).setCellValue(data);
			

			// here we need to specify where you want to save file
			fout = new FileOutputStream(new File("Result\\API_Result.xls"));

			// finally write content
			wb.write(fout);
			System.out.println("Status written"+ data);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void hitGETapi(String URI) {
		RestAssured.baseURI = URI;

		response = RestAssured.given().get(URI);

		// RequestSpecification httprequest = RestAssured.given();
		// response = httprequest.request(Method.GET, URI);
		jsonPathEvaluator = response.jsonPath();
		rescode = response.getStatusCode();
		System.out.println(rescode);
		responsebody = response.getBody().asString();
		System.out.println(responsebody);

	}
	
	

	public static void hitPOSTapi(File FileName, String URI) {
		RestAssured.baseURI = URI;

		response = given().contentType(ContentType.JSON).body(FileName).post(URI);
		jsonPathEvaluator = response.jsonPath();
		rescode = response.getStatusCode();
		System.out.println(rescode);
		responsebody = response.getBody().asString();
		System.out.println(responsebody);
	}
	
	

	

	public static void hitPUTapi(File FileName, String URI) {
		RestAssured.baseURI = URI;

		response = given().contentType(ContentType.JSON).body(FileName).put(URI);
		jsonPathEvaluator = response.jsonPath();
		rescode = response.getStatusCode();
		System.out.println(rescode);
		responsebody = response.getBody().asString();
		System.out.println(responsebody);

	}

	

	public static void hitDELETEapi(String URI) {
		RestAssured.baseURI = URI;

		response = given().contentType(ContentType.JSON).delete(URI);
		jsonPathEvaluator = response.jsonPath();
		rescode = response.getStatusCode();
		responsebody = response.getBody().asString();
	}

	
	public static class Filepaths {

		static final File Login_creds = new File("./JsonFiles/Login_creds.json");
		static final File PUT_User_DATA = new File("./JsonFiles/PutUserData.json");
			}
	
	public static void hitAPIwithAccessToken(String URI, String TokenValue) {
		RestAssured.baseURI = URI;
		response = RestAssured.given().auth().oauth2(TokenValue).get(URI);
		jsonPathEvaluator = response.jsonPath();
		rescode = response.getStatusCode();
		responsebody = response.getBody().asString();
	}
public static void GetActiveToken() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
	    driver = new ChromeDriver(chromeOptions);
		driver.get("enter URL here");
		String title = driver.getTitle();
		Thread.sleep(3000);
		//add sign IN if Required
		if (title.equals("Sign in to your account")) {
			driver.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys("email");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@value=\"Next\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("password");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@value=\"Sign in\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@value=\"Yes\"]")).click();
			Thread.sleep(5000);
		}

		TokenValue = (String) ((JavascriptExecutor) driver)
				.executeScript("return window.sessionStorage.getItem(\"token\");");

		driver.close();
		driver.quit();
	}



	}
