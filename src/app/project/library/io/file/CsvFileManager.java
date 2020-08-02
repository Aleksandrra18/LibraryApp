package app.project.library.io.file;

import app.project.library.exception.DataExportException;
import app.project.library.exception.DataImportException;
import app.project.library.exception.InvalidDataException;
import app.project.library.model.Book;
import app.project.library.model.Library;
import app.project.library.model.Magazine;
import app.project.library.model.Publication;

import java.io.*;

public class CsvFileManager implements FileManager {
    private static final String FILE_NAME = "Library.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        boolean eof = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            while (!eof) {
                try {
                    String line = bufferedReader.readLine();
                    Publication publication = createObjectFromString(line);
                    library.addPublication(publication);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        }
        return library;
    }

    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazine(split);
        }
        throw new InvalidDataException("Nieznany typ publikacji: " + type);
    }

    private Magazine createMagazine(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        int month = Integer.valueOf(data[4]);
        int day = Integer.valueOf(data[5]);
        String language = data[6];
        return new Magazine(year, title, publisher, month, day, language);
    }

    private Book createBook(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        String author = data[4];
        int pages = Integer.valueOf(data[5]);
        String isbn = data[6];
        return new Book(year, title, publisher, author, pages, isbn);
    }

    @Override
    public void exportData(Library library) {
        Publication[] publications = library.getPublications();
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Publication publication : publications) {
                reader.write(publication.toCSV());
                reader.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME);
        }
    }
}
