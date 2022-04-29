
/*  Assignment 02: Implement a Book Information System
	Author: Roger Tazza
	Student Id: 300341127
	Date: February 11, 2022
	File name: BookInfoSys class
*/


// Import the require packages for creating this application

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

//Create BookInfoSys class
public class BookInfoSys {

	//Creation of a scanner variable to read user input that works along all the class:
	private static Scanner input = new Scanner(System.in);
	//Creation of a variable to hold the path of the file that works along all the class:
	private static final String INPUT_PATH = "./";
	//Creation of a bookList to store each book read in the file that works along all the class:
	private static ArrayList<Book> bookList = new ArrayList<Book>();

	public static void main(String[] args) {

		//Creation of a do-while loop to allow the user a second chance
		//for access to the system, where at least once time the program is executed without
		//answer of the user.
		String answer2;
		do {
			//Method to display general information of the program
			displayInstructions();
			//Welcome message and creation of a method to display options for the user
			System.out.println("Welcome to Book Info. System.");
			displayMenu();
			String answer = input.nextLine();

			//Validation of the user answer
			while (!answer.equals("E") && !answer.equals("R") && !answer.equals("S") && !answer.equals("F")) {
				System.out.println("Wrong answer!. Please enter a valid letter >>");
				answer = input.nextLine();
			}

			//Loop for allowing the user to use the system multiple times without the necessity
			//to open the program again. Only when the user wants to Exit the program is closed.
			boolean again = true;
			while (again) {
				//Conditional statements that redirect certain actions based on user answer
				if (answer.equals("R")) {
					//Method for reading the content of the file and adding each book to a list of books.
					readBookFromInputFile();
					//Method to display options again for the user
					displayMenu();
					answer = input.nextLine();
				} else if (answer.equals("S")) {
					//Sub conditionals for allowing the user display the content of the file
					//once he/she reads at least 1 book from the file.
					if (bookList.size() == 0) {
						System.out.println("To display all the books, you must enter at least 1 book.");
						System.out.print("Please select another option >> ");
						answer = input.nextLine();
					} else {
						//Method for displaying all the books from the book list
						displayBooksFromList();
						//Method to display options again for the user
						displayMenu();
						answer = input.nextLine();
					}
				} else if (answer.equals("F")) {
					//Method for searching a/some books from the book list based on a keyword
					searchABookFromList();
					//Method to display options again for the user
					displayMenu();
					answer = input.nextLine();
				} else if (answer.equals("E")) {
					//If the user enters E, exit the system.
					System.out.println("Thank you for using!");
					again = false;
				} else {
					//Validation to ensure the user only enters E,F,R or S.
					System.out.println("Wrong answer!. Please enter a valid letter >>");
					answer = input.nextLine();
				}

			}
			// Allowing a second chance to the user for using the system.
			System.out.println("Do you want to use the book system again? (Y:yes or N:no) >>");
			answer2 = input.nextLine();
			//Validation of the user answer
			while (!answer2.equals("Y") && !answer2.equals("N")) {
				System.out.println("Wrong answer!. Please enter a valid letter >>");
				answer2 = input.nextLine();
			}

		} while (!answer2.equals("N"));
		System.out.println("Thank you for using the book system!");
	}

	//Method for searching a/some books from the book list based on a keyword
	public static void searchABookFromList() {
		//Ask the user for the keyword
		System.out.print("Enter the key >> ");
		String key = input.nextLine();
		int countTitle = 0;

		//Store each title from the book list in an array
		String[] titleArray = new String[bookList.size()];
		for (int i = 0; i < bookList.size(); i++) {
			titleArray[i] = bookList.get(i).getTitle();
		}
		//Create an array that stores each word of each title separated
		//Then, compare each word with the keyword (ignoring case) to find 
		//which books meet the previous condition. Finally, display those books.
		String[] temp = null;
		for (int i = 0; i < titleArray.length; i++) {
			temp = titleArray[i].split(" ");
			for (int j = 0; j < temp.length; j++) {
				String pattern = "(?i).*" + key + ".*(?i)";
				Boolean matches = Pattern.matches(pattern, temp[j]);
				if (matches) {
					countTitle++;
					displayBooksFromList(countTitle, i);
				}
			}
		}
		//Indicate how many books match
		System.out.println(countTitle + " records found.");
	}

	//Method for displaying all the books from the book list (overloaded)
	public static void displayBooksFromList(int counter, int index) {
		System.out.println("Book #" + (counter) + ": ");
		System.out.println(bookList.get(index));

	}
	//Method for displaying all the books from the book list
	public static void displayBooksFromList() {
		for (int i = 0; i < bookList.size(); i++) {
			System.out.println("Book #" + (i + 1) + ": ");
			System.out.println(bookList.get(i));
		}
	}
	//Method for reading the content of the file and adding each book to a list of books.
	public static void readBookFromInputFile() {
		//Create the fileReader object for allowing read each line from the file
		BufferedReader fileReader = null;
		//Ask the user for the file name
		System.out.print("Enter the filename >> ");
		String option = input.nextLine();
		// Clear the object's field for a new file.
		Book bookObject = new Book();
		// Clear the list for a new file.
		bookList.clear();
		
		//Allows to read each line of the file based on the user input about the name of the file
		try {
			fileReader = new BufferedReader(new FileReader(INPUT_PATH + option + ".txt"));
			String readLine = "";
			//Each line read is stored in a list of books objects
			while ((readLine = fileReader.readLine()) != null) {
				String[] tempArray;
				tempArray = readLine.split(",");
				bookObject = new Book(tempArray[0], tempArray[1], Integer.parseInt((tempArray[2])));
				bookList.add(bookObject);
			}
			//Close the file
			fileReader.close();
			//If any error occurs while the file is being read, an exception is thrown.
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Method to display options for the user
	public static void displayMenu() {
		System.out.println("Please select an option:" + "\n" + "(R)ead books from file" + "\n" + "(S)how all books"
				+ "\n" + "(F)ind books by title" + "\n" + "(E)xit" + "\n" + "Enter your option >> ");

	}
	
	//Method to display general information of the program
	public static void displayInstructions() {
		System.out.print("The following program allows customers to access the book information system " + "\n"
				+ "The program will give 4 options to the customer which are: " + "\n"
				+ "i) Read books from file, ii) Show all books, iii) Find books by title, and iv) Exit " + "\n"
				+ "The program will require the following inputs from the customer: " + "\n"
				+ "a. Filename" + "\n" + "b. Insert the correct letters based on the options available");
		System.out.println("\n");
	}

}
