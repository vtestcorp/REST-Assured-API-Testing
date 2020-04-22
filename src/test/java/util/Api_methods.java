package util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jxl.write.WriteException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Api_methods extends Util {

	public static Long inventory;

	public static void GetAllUsers(int row)
			throws WriteException, Exception {

		flag = false;
		
		System.out.println("started Executing");

		hitGETapi("https://reqres.in/api/users?page=2");
			if(rescode==200) { flag=true;}
			else {flag=false;}
			
		
		if (flag == true) {
			writeStatus(7, row, "PASSED");
			writeStatus(8, row, responsebody);
			
		} else {
			writeStatus(7, row, "FAILED");
			writeStatus(8, row, responsebody);
		}
		System.out.println("Execution done");
	}

	public static void singleuser(int row)
			throws WriteException, Exception {
		System.out.println("started Executing");
		hitGETapi("https://reqres.in/api/users/2");
		if(rescode==200) {flag=true;}
		else {flag=false;}
		
	//}
	
	
	//}
	
	if (flag == true) {
		writeStatus(7, row, "PASSED");
		writeStatus(8, row, responsebody);
	} else {
		writeStatus(7, row, "FAILED");
		writeStatus(8, row, responsebody);
	}
	System.out.println("Execution done");
	}
	public static void Get_userinfo(int row)
			throws WriteException, Exception {
		System.out.println("started Executing");
		hitGETapi("https://reqres.in/api/unknown");
		
	
		if (rescode == 200) {
			flag = true;
		//}
	} 
	//}
	
	if (flag == true) {
		writeStatus(7, row, "PASSED");
		writeStatus(8, row, responsebody);
		
	} else {
		writeStatus(7, row, "FAILED");
		writeStatus(8, row, responsebody);
	}
	System.out.println("Execution done");
	}


		public static void Post_login( int row) throws WriteException, Exception {
		// Replace variables with actual parameter values before passing the URL to GET
		// api

				flag = true;

		if (flag == true) {
			System.out.println("started Executing");
			hitPOSTapi(Filepaths.Login_creds, "https://reqres.in/api/login");
			if (rescode == 200)
				flag = true;
		} 

		if (flag == true) {
			writeStatus(7, row, "PASSED");
			writeStatus(8, row, responsebody);}
		else if (flag == false) {
			writeStatus(7, row, "FAILED");
			writeStatus(8, row, responsebody);}
		System.out.println("Execution done");
	}

		public static void PutUserData(int row)
			throws WriteException, Exception {
		// Replace variables with actual parameter values before passing the URL to GET
		// api
				flag = true;
		if (flag == true) {
			System.out.println("started Executing");
			hitPUTapi(Filepaths.PUT_User_DATA, "https://reqres.in/api/users/2");

			if (rescode == 200) {
				writeStatus(7, row, "PASSED");
				writeStatus(8, row, responsebody);
				
			}else {
				writeStatus(7, row, "FAILED");
				writeStatus(8, row, responsebody);
				
			}
		}
		System.out.println("Execution done");
	}

		public static void DeleteUsers(int row)
				throws WriteException, Exception {

			flag = false;
			
			System.out.println("started Executing");

			hitDELETEapi("https://petstore.swagger.io/v2/user/user1");
				if(rescode==404) { flag=true;}
				else {flag=false;}
				
			
			if (flag == true) {
				writeStatus(7, row, "PASSED");
				writeStatus(8, row, responsebody);
				
			} else {
				writeStatus(7, row, "FAILED");
				writeStatus(8, row, responsebody);
			}
			System.out.println("Execution done");
		}
}