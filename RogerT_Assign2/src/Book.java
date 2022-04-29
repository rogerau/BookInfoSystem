
/*  Assignment 02: Implement a Book Information System
	Author: Roger Tazza
	Student Id: 300341127
	Date: February 11, 2022
	File name: Book class
*/

//Create a Book class
public class Book {

	//Create instance variables: 
	private String title;
	private String authors;
	private int numPages;
	
	//Create getters/setters or instance methods:
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public int getNumPages() {
		return numPages;
	}
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	
	//Create constructors
	//Parameterless constructor
	public Book() {
		title = "";
		authors = "";
		numPages = 0;
	}
	
	//3 parameter constructor
	public Book(String title, String authors, int numPages) {
		this.title = title;
		this.authors = authors;
		this.numPages = numPages;
	}
	
	//Tostring method
	public String toString() {
		String output = "Title: "+ title + "\n" + 
						"Authors: " + authors + "\n" + 
						"Pages: " + numPages;
		return output;
	}
	
	
	
}
