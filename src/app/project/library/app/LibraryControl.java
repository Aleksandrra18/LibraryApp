package app.project.library.app;

import app.project.library.io.DataReader;
import app.project.library.model.Book;
import app.project.library.model.Library;
import app.project.library.model.Magazine;

public class LibraryControl {

    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int ADD_MAGAZINE = 2;
    private static final int PRINT_BOOKS = 3;
    private static final int PRINT_MAGAZINE = 4;

    private DataReader reader = new DataReader();

    private Library library = new Library();

    public void controlLoop() {
        int option;
        do {
            printOptions();
            option = reader.getInt();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINE:
                    printMagazine();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie");
            }
        } while (option != EXIT);

    }

    private void printMagazine() {
        library.printMagazine();
    }

    private void addMagazine() {
        Magazine magazine = reader.readAndCreateMagazine();
        library.addMagazine(magazine);
    }

    private void printOptions() {
        System.out.println("Wybierz opcję:\n" +
                EXIT + " - wyjście z programu\n" +
                ADD_BOOK + " - dodanie nowej książki\n" +
                ADD_MAGAZINE + " - dodanie nowego magazynu\n" +
                PRINT_BOOKS + " - wyświetl dostępne książki\n" +
                PRINT_MAGAZINE + " - wyświetl dostępne magazyny");
    }

    private void addBook() {
        Book book = reader.readAndCreateBook();
        library.addBook(book);
    }

    private void printBooks() {
        library.printBooks();
    }

    private void exit() {
        System.out.println("Koniec programu, papa");
        reader.close();
    }
}
