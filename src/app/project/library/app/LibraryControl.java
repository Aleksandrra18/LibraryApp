package app.project.library.app;

import app.project.library.exception.DataExportException;
import app.project.library.exception.DataImportException;
import app.project.library.exception.InvalidDataException;
import app.project.library.exception.NoSuchOptionException;
import app.project.library.io.ConsolePrinter;
import app.project.library.io.DataReader;
import app.project.library.io.file.FileManager;
import app.project.library.io.file.FileManagerBuilder;
import app.project.library.model.Book;
import app.project.library.model.Library;
import app.project.library.model.Magazine;
import app.project.library.model.Publication;

import java.io.InvalidClassException;
import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader reader = new DataReader(printer);
    private FileManager fileManager;
    private Library library;

    public LibraryControl() {
        fileManager = new FileManagerBuilder(printer, reader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Zaimportowano dane z pliku");
        }catch (DataImportException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę");
            library = new Library();
        }
    }

    void controlLoop() {
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
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Unable to create magazine, entered invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Limit of publications exceeded");
        }
    }

    private void printMagazine() {
        Publication[] publications = library.getPublications();
        printer.printMagazine(publications);
        printer.printLine("=============================================================================");
    }

    private void addBook() {
        try {
            Book book = reader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Unable to create book, entered invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Limit of publications exceeded");
        }
    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
        printer.printLine("=============================================================================");
    }

    private void printOptions() {
        printer.printLine("Wybierz opcję:");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void exit() {
        try {
            fileManager.exportData(library);
            printer.printLine("Eksport danych do pliku zakończony powodzeniem");
        }catch (DataExportException e){
            printer.printLine(e.getMessage());
        }
        printer.printLine("Koniec programu, papa");
        reader.close();
    }

    private enum Option {
        EXIT(0, "wyjście z programu"),
        ADD_BOOK(1, "dodanie nowej książki"),
        ADD_MAGAZINE(2, "dodanie nowego magazynu"),
        PRINT_BOOKS(3, "wyświetl dostępne książki"),
        PRINT_MAGAZINE(4, "wyświetl dostępne magazyny");

        private int value;
        private String optionInfo;

        Option(int value, String optionInfo) {
            this.value = value;
            this.optionInfo = optionInfo;
        }

        public int getValue() {
            return value;
        }

        public String getOptionInfo() {
            return optionInfo;
        }

        static Option createFromInt(int userOption) throws NoSuchOptionException {
            try {
                return Option.values()[userOption];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("No option with id " + userOption);
            }
        }

        @Override
        public String toString() {
            return value + " - " + getOptionInfo();
        }
    }
}
