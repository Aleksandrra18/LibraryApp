package app.project.library.io;

import app.project.library.model.Book;
import app.project.library.model.Magazine;

import java.util.Scanner;

public class DataReader {

    Scanner scanner = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void close() {
        scanner.close();
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public Book readAndCreateBook() {
        printer.printLine("Tytuł: ");
        String title = scanner.nextLine();
        printer.printLine("Autor: ");
        String author = scanner.nextLine();
        printer.printLine("Wydawnictwo: ");
        String publisher = scanner.nextLine();
        printer.printLine("ISBN: ");
        String isbn = scanner.nextLine();
        printer.printLine("Rok wydania: ");
        int releaseDate = getInt();
        printer.printLine("Ilość stron: ");
        int pages = getInt();

        return new Book(releaseDate, title, author, publisher, pages, isbn);
    }

    public Magazine readAndCreateMagazine() {
        printer.printLine("Rok wydania: ");
        int releaseDate = getInt();
        printer.printLine("Tytuł: ");
        String title = scanner.nextLine();
        printer.printLine("Wydawnictwo: ");
        String publisher = scanner.nextLine();
        printer.printLine("Month: ");
        int month = getInt();
        printer.printLine("Day: ");
        int day = getInt();
        printer.printLine("Language: ");
        String language = scanner.nextLine();

        return new Magazine(releaseDate, title, publisher, month, day, language);
    }

}
