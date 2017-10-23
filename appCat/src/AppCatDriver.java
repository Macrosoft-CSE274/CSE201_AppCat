import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AppCatDriver implements Serializable{

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{

		AppCat appcat = new AppCat();
		File pastFile = new File("AppCatData.dat");
		
		if (pastFile.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pastFile));
			try {
				appcat = (AppCat) ois.readObject();
			} catch (FileNotFoundException e) {
				System.out.println("'" + pastFile + "' was not found.");
			} catch (IOException e) {
				System.out.println("Error: IO Exception");
			}
			ois.close();	
		}
		
		else {
			appcat = new AppCat();
		}
		
		Scanner menuChoice = new Scanner(System.in);
		int choice;
		ArrayList<String> users = new ArrayList<String>();

		do {
			System.out.println("\t+-------------------------------------------+"
			            + "\n\t||                                         ||"
					    + "\n\t||   Choose from the Menu Options Below:   ||"
					    + "\n\t||                                         ||"
						+ "\n\t||  1  -- Create a User                    ||"
						+ "\n\t||  2  -- Create a Moderator               ||"
						+ "\n\t||  3  -- Create an Administrator          ||"
						+ "\n\t||  4  -- List all Users                   ||"
						+ "\n\t||  5  -- Exit the Program                 ||"
			            + "\n\t||                                         ||"
			            + "\n\t||         Type a number from the          ||"
		                + "\n\t||         list and press \"enter\"          ||"
			            + "\n\t||                                         ||"
		                + "\n\t+-------------------------------------------+");
		    try{System.out.print("           ----> ");
		    }
		    	catch(InputMismatchException ex){
		    	System.out.println("Invalid input. Choice must be an integer between 1 and 5");}
				choice = menuChoice.nextInt();
				
			    switch (choice) {
			    case 1:
		    		/*   create a User   */
			    	UserAccount ua = new UserAccount("Dingbang", "123", 2);
			    	users.add("Dingbang");
			    	System.out.println("You added '" + ua.getUsername() + "' as a User.");
		        	System.out.print("\n");
		    		break;
		    	case 2:
		    		/*   create a Moderator   */
		    		Moderator mod = new Moderator("Jimmy", "123", 3);
		    		users.add("Jimmy");
		    		System.out.println("You added '" + mod.getUsername() + "' as a Moderator.");
		        	System.out.print("\n");
		    		break;
		        case 3:
		            /*   create an Administrator   */
		        	Administrator admin = new Administrator("Kunting", "123", 4);
		        	users.add("Kunting");
		        	System.out.println("You added '" + admin.getUsername() +"' as an Admin.");
		        	System.out.print("\n");
		            break;
		        case 4:
		        	/*   lists all of the users in the system   */
		        	for (int i = 0; i < users.size(); i++) {
		        		System.out.println(i+1 +") " + users.get(i));
		        	}
		        	break;
		        case 5:
		        	/*   quit program and serialize code   */
		        	System.out.println("\nYou have chosen to exit the program."
		        			+ "\nAppCatUser information has been saved.");
		            break;
		            
		        default:
		        	System.out.println("Choice must be a value between 1 and 4.");
			    }
		}while (choice != 5);
		
		String updatedAppCat = "AppCatData.dat";
		menuChoice.close();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(updatedAppCat));
		try {
			oos.writeObject(appcat);
		} catch (FileNotFoundException e) {
			System.out.println("'" + pastFile +"' was not found.");
		} catch (IOException e) {
			System.out.println("Error: IOException");
		}
		oos.close();

		}
}
