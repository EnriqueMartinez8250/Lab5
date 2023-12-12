package edu.ucdenver.library;
import edu.ucdenver.libraryapp.Connect;

public class Library {


    private final Connect database;

    public Library(String name) {
        this.database = new Connect();
    }

    public void searchLibrary(String query){
        this.database.searchBooks(query);
    }

    public void addUser(String username, String password, String email)
            throws IllegalArgumentException{
        this.database.addUser(new User(username, password, email));
    }

    public boolean signIn(String username, String password) throws IllegalArgumentException{
        User user = this.database.getUser(username, password);
        if (user != null)
            return true;
        return false;
    }
    public boolean adminSignIn(String username, String password) {
        User user = this.database.getAdmin(username, password);

        if (user != null)
            return true;
        return false;
    }




}