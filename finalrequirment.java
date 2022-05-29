import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.APPEND;
import java.io.*;

public class covidSystem {
	static String name,fdate,fbrand,sdate,sbrand,_temp = null;
	static String [] records = new String[9999];
	static int refNum;
	
	public static void main(String[] args) throws java.lang.Exception {
		
		try (Scanner sc = new Scanner(System.in)) {
			while(true) {
				System.out.println("--Welcome To The COVID 19 Vaccination System--");
				System.out.println("Select Options: ");
				System.out.println("\t[1] Add Record\n\t[2] Display Details\n\t[3] Modify Record\n\t[4] Delete Records");
				System.out.print("\n> ");
				int choice = sc.nextInt();
				System.out.println();
				
					switch(choice) {
					case 1:
						addAccount();
						break;
					case 2:
						display();
						break;
					case 3:
						ModifyRecord();
						break;
					case 4:
						DeleteRecord();
						break;
					default:
						System.out.println("Input Error");
				}
			}
		}
	}
	
	static void addAccount() throws IOException {
   	 @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
   	 File acc = new File("records.txt");
   	 Random rand = new Random();
		
   	while(true) {
   		System.out.print("Enter Your Full Name: ");
			name = sc.nextLine();
		
			System.out.print("Date of the First Dose (MM/DD/YYYY): ");
			fdate = sc.nextLine();
		
			System.out.print("Brand of Vaccine: ");
			fbrand = sc.nextLine();
		
			System.out.print("Date of the Second Dose (MM/DD/YYYY): ");
			sdate = sc.nextLine();
		
			System.out.print("Brand of Vaccine: ");
			sbrand = sc.nextLine();
			
			int minRange = 1000, maxRange= 99999;
	        refNum = rand.nextInt(maxRange - minRange) + minRange;
		
			System.out.println("\n-=Displaying Inputted Information=-");
			System.out.println("Full Name: " + name + "\n" + "First Dose: " + fdate + "\n" + "Brand: " + fbrand + "\n" + "Second Dose: " + sdate 
					+"\n"+ "Brand: " + sbrand +"\nReference Number: " + refNum + "\n");
		
			System.out.println("Confirm details? y/n");
			System.out.print("> ");
			String ans = sc.nextLine().toLowerCase();
			
			String ph = null;
			switch(ans) {
				case "y":
					acc.createNewFile();
					Path path = Paths.get(acc.toString());
					OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, APPEND));
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
					
					writer.write(name.toLowerCase() + ", " + fdate + ", " + fbrand.toLowerCase() + ", " + sdate + ", " + sbrand.toLowerCase() + ", " + refNum);
					writer.newLine();
					writer.close();
					output.close();
					ph = "stop";
					break;
				case "n":
					System.out.println();
					ph = "return";
					break;
				default:
					System.out.println("\nInput Eror\n");
					break;
			}
			if(ph.contentEquals("stop")) {
				System.out.println("\nAccount added\n");
				break;
			} else {
				break;
			}
		}
    }
    
	static void display() throws IOException {
   	 
    	File acc = new File("records.txt");
    	Path path = Paths.get(acc.toString());
		InputStream input = Files.newInputStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		int i = 0;

    	while((_temp=reader.readLine()) != null)
			{
				String[] account = _temp.split(",");
				System.out.println(++i + ".) " + "Name: " + account[0] + "\nDate of First Vaccine: " + account[1] + "\nBrand of First Vaccine" + account[2] 
						+ "\nDate of Second Vaccine: " + account[3] + "\nBrand of Second Vaccine: " + account[4] + "\nReference Number: " + account[5] + "\n");
			}
    	if(reader.readLine() == null) {
    		System.out.println("\tNo Records Found\n");
    	}reader.close(); 

 	}
 	
	static void ModifyRecord() throws IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String acc = ("records.txt");
		Path path = Paths.get(acc.toString());
		InputStream input = Files.newInputStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    	display();
    	
    	System.out.println("Enter Reference Number of account you wish to modify");
    	System.out.print("> ");
    	String crefNum = sc.nextLine();
    	System.out.println();
    	
    	int i = 0;
    	int editIndex = 0;
    	Boolean check = false; 
    	String thisrefNum = null;
    	ArrayList<String> oldRecords = new ArrayList<>();
    	
    	while((_temp=reader.readLine()) != null)
		{
    		oldRecords.add(_temp);
    		
			String[] account = _temp.split(",");
			thisrefNum = account[5];
			
			if(thisrefNum.contains(crefNum)){
				check = true;
				editIndex = i;
			}
			i++;
		}
    	if(check == true) {
    		System.out.print("Enter Your Full Name: ");
			String newName = sc.nextLine();
		
			System.out.print("Date of the First Dose (MM/DD/YYYY): ");
			String newFdate = sc.nextLine();
		
			System.out.print("Brand of Vaccine: ");
			String newFbrand = sc.nextLine();
		
			System.out.print("Date of the Second Dose (MM/DD/YYYY): ");
			String newSdate = sc.nextLine();
		
			System.out.print("Brand of Vaccine: ");
			String newSbrand = sc.nextLine();

			System.out.println("\n-=Displaying Inputted Information=-");
 			System.out.println("Full Name: " + newName + "\n" + "First Dose: " + newFdate + "\n" + "Brand: " + newFbrand + "\n" + "Second Dose: " + newSdate 
 					+"\n"+ "Brand: " + newSbrand +"\nReference Number: " + crefNum + "\n");
 		
 			System.out.println("Confirm details? y/n");
 			System.out.print("> ");
 			String ans = sc.nextLine().toLowerCase();
 			
 			String ph = null;
 			switch(ans) {
 				case "y":
					FileWriter writer = new FileWriter(new File("E:\\ActivityICT", "records.txt"));
 					
					
 					oldRecords.set(editIndex, (newName.toLowerCase() + ", " + 
 							newFdate + ", " + 
 							newFbrand.toLowerCase() + ", " + 
 							newSdate + ", " + 
 							newSbrand.toLowerCase() + ", " + 
 							thisrefNum));
 					
 					for(String record : oldRecords) {
 						writer.write(record + "\n");
 					}
 					writer.close();
 					
 					ph = "stop";
 					break;
 				case "n":
 					System.out.println();
 					ph = "return";
 					break;
 				default:
 					System.out.println("\nInput Eror\n");
 					break;
 			}
 			if(ph.contentEquals("stop")) {
 				System.out.println("\nSuccessfuly modified\n");
 			} else {
 				System.out.println("\nModification error\n");
 			}
    	}
	}

	static void DeleteRecord() throws IOException {
		File acc = new File("records.txt");
		FileWriter thisFile = new FileWriter(acc, false); 
        PrintWriter printFile = new PrintWriter(thisFile, false);
        printFile.flush();
        printFile.close();
        thisFile.close();
	}
}
	
