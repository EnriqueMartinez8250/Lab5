package edu.ucdenver.libraryapp;
import edu.ucdenver.library.Library;
import java.util.Scanner;

public class MyLibraryApp {
    /**
     * This is a sample program that will use the Library.
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean isSignedIn = false;
        Library myLibrary = new Library("My Auraria Library");

        while(!isSignedIn){
            System.out.print("1. Sign in\n2. Admin Sign in\n3. Create account\n4. Exit\nSelect: ");
            int option = input.nextInt();

            input.nextLine();

            if (option == 1){
                System.out.println("----------\nUser Login\n----------");
                System.out.print("Username: ");
                String username = input.nextLine();

                System.out.print("Password: ");
                String password = input.nextLine();

                isSignedIn = myLibrary.signIn(username, password);

                while (isSignedIn) {
                    System.out.print("Type in a book name to search the library. Type 'exit' to quit.\n");

                    System.out.print("Book Name: ");
                    String query = input.nextLine();

                    if (query.equals("exit"))
                        isSignedIn = false;
                    else
                        myLibrary.searchLibrary(query);
                }

            }
            else if (option == 2) {
                System.out.println("-------------------\nAdministrator Login\n-------------------");
                System.out.print("Username: ");
                String username = input.nextLine();

                System.out.print("Password: ");
                String password = input.nextLine();

                isSignedIn = myLibrary.adminSignIn(username, password);

                while (isSignedIn){
                    System.out.println("\nThis is the Administrator Console. No unauthorized access.");
                    System.out.print("1. Add Book (Not Implemented)\n2. Remove Book (Not Implemented)\n3. Exit\nSelect: ");
                    option = input.nextInt();

                    if (option == 3){
                        isSignedIn = false;
                    }
                }
            }
            else if (option == 3) {
                System.out.println("-----------\nNew Account\n-----------");
                System.out.print("Username: ");
                String username = input.nextLine();

                System.out.print("Password: ");
                String password = input.nextLine();

                System.out.print("Email: ");
                String email = input.nextLine();

                myLibrary.addUser(username, password, email);
            }
            else if (option == 4) {
                break;
            }
        }


    }
}