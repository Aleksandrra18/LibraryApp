package app.project.library.io;

import app.project.library.model.Book;
import app.project.library.model.Magazine;

import java.util.Scanner;

public class DataReader {

    Scanner scanner = new Scanner(System.in);

    public void close() {
        scanner.close();
    }

    public int getInt() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public Book readAndCreateBook() {
        System.out.println("Tytuł: ");
        String title = scanner.nextLine();
        System.out.println("Autor: ");
        String author = scanner.nextLine();
        System.out.println("Wydawnictwo: ");
        String publisher = scanner.nextLine();
        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Rok wydania: ");
        int releaseDate = getInt();
        System.out.println("Ilość stron: ");
        int pages = getInt();

        return new Book(releaseDate, title, author, publisher, pages, isbn);
    }

    public Magazine readAndCreateMagazine() {
        System.out.println("Rok wydania: ");
        int releaseDate = getInt();
        System.out.println("Tytuł: ");
        String title = scanner.nextLine();
        System.out.println("Wydawnictwo: ");
        String publisher = scanner.nextLine();
        System.out.println("Month: ");
        int month = getInt();
        System.out.println("Day: ");
        int day = getInt();
        System.out.println("Language: ");
        String language = scanner.nextLine();

        return new Magazine(releaseDate, title, publisher, month, day, language);
    }

}
