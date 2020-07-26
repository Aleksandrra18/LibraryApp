package app.project.library.app;

import app.project.library.exception.NoSuchOptionException;
import app.project.library.io.ConsolePrinter;
import app.project.library.io.DataReader;
import app.project.library.model.Book;
import app.project.library.model.Library;
import app.project.library.model.Magazine;
import app.project.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader reader = new DataReader(printer);
    private Library library = new Library();

    public void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
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
                    printer.printLine("Nie ma takiej opcji, wprowadź ponownie");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(reader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("Invalid value, enter a number");
            }
        }
        return option;
    }

    private void addMagazine() {
        try {
            Magazine magazine = reader.readAndCreateMagazine();
            library.addMagazine(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Unable to create magazine, entered invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Limit of publications exceeded");
        }
    }

    private void printMagazine() {
        Publication[] publications = library.getPublications();
        printer.printMagazine(publications);
    }

    private void addBook() {
        try {
            Book book = reader.readAndCreateBook();
            library.addBook(book);
        } catch (InputMismatchException e) {
            printer.printLine("Unable to create book, entered invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Limit of publications exceeded");
        }
    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void printOptions() {
        printer.printLine("Wybierz opcję:");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void exit() {
        System.out.println("Koniec programu, papa");
        reader.close();
    }
}
