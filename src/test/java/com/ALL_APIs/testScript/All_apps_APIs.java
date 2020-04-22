package com.ALL_APIs.testScript;

import org.testng.annotations.Test;



import util.Api_methods;

import util.Util;

public class All_apps_APIs extends Util {
	public static int row;
	public static String exe, method, URI, Param1, Param2, Param3;

	@Test
	public void API_Test() throws Exception, ArrayIndexOutOfBoundsException {
		
				createSheetCopy();
		
		
		//System.out.println("Token Value ==> " + TokenValue);
		
		try {

			ReadData();

			for (row = 1; row < totalNoOfRows; row++) {
				exe = sh.getCell(0, row).getContents();
				method = sh.getCell(1, row).getContents();
				URI = sh.getCell(2, row).getContents();
				Param1 = sh.getCell(3, row).getContents();
				Param2 = sh.getCell(4, row).getContents();
				Param3 = sh.getCell(5, row).getContents();

				if (exe.equalsIgnoreCase("Y") || exe.equalsIgnoreCase("Yes")) {
					System.out.println("Y");
					
					
			
					
					
					
					
					switch (method + URI) {
					//<---------------------------------------------------------------- APIS ---------------------------------------------------------------->
					
					case "GET" + "https://reqres.in/api/users?page=2":
						System.out.println(method + URI);
						Api_methods.GetAllUsers(row);
						break;
					
					case "GET" + "https://reqres.in/api/users/2":
						System.out.println(method + URI);

						Api_methods.singleuser(row);
						break;
					
					
					case "POST" + "https://reqres.in/api/login":
						System.out.println(method + URI);

						Api_methods.Post_login(row);
						break;

					case "GET" + "https://reqres.in/api/unknown":
						System.out.println(method + URI);

						Api_methods.Get_userinfo(row);
						break;


					case "PUT" + "https://reqres.in/api/users/2":
						System.out.println(method + URI);

						Api_methods.PutUserData(row);
						break;

					case "DELETE" + "https://petstore.swagger.io/v2/user/user1": 
						System.out.println(method + URI);

						Api_methods.DeleteUsers(row);
						break;
						
					
					}	
					
					
					
					
					
				} else if (exe.equalsIgnoreCase("N") || exe.equalsIgnoreCase("No")) {
					writeStatus(7, row, "NOT EXECUTED");
				}
			}
		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

}
