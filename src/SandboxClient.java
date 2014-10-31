import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

public class SandboxClient {
	private Scanner input = new Scanner(System.in);
	private String userInput = null;
	private UserBean aUser = new UserBean();
	
	public static void main(String [] arg) {
		SandboxClient sandboxClient = new SandboxClient();
		try {
			Socket serverSocket = new Socket("localhost", 9292);
			// setup the JSON streams to be used later.
			final JSONInputStream inFromServer = new JSONInputStream(
					serverSocket.getInputStream());
			final JSONOutputStream outToServer = new JSONOutputStream(
					serverSocket.getOutputStream());
			
			sandboxClient.menu(outToServer, inFromServer, serverSocket);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
	        System.out.println("Server: " + in.readLine());
		} catch (JSONException e) {
			e.printStackTrace();
			
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}

	private void menu(JSONOutputStream outToServer, JSONInputStream inFromServer, Socket serverSocket) throws JSONException, IOException {
		System.out.println("Choose an option:\n"
				+ "\t1:Login\n"
				+ "\t2:Create New User\n"
				+ "\tX:Exit program");
	
	
		this.userInput = this.input.next();
		this.userInput.toLowerCase();

		PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
        out.println(this.userInput);
		
			switch (this.userInput){
				case "1":
					loginMenu(outToServer, inFromServer);
					break;
				case "2":
					createNewUserMenu(outToServer);
					break;
				case "x":
					System.exit(1);
			} 
}

	private void createNewUserMenu(JSONOutputStream outToServer) throws JSONException {
		System.out.println("Create username: ");
		aUser.setUname(this.input.next());
	
		System.out.println("Create password for " + aUser.getUname() + ": ");
		aUser.setPword(this.input.next());
		
		outToServer.writeObject(aUser);
	}

	private void loginMenu(JSONOutputStream outToServer, JSONInputStream inFromServer) throws JSONException {
		System.out.print("Username: ");
		aUser.setUname(this.input.next());
	
		System.out.print("Password: ");
		aUser.setPword(this.input.next());
	
		outToServer.writeObject(aUser);
	}
}
