package app.project.library.io;

import app.project.library.model.Book;
import app.project.library.model.Magazine;
import app.project.library.model.Publication;

public class ConsolePrinter {

    public void printBooks(Publication[] publications) {
        int countBooks = 0;
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
                countBooks++;
            }
        }
        if (countBooks == 0) {
            printLine("Brak książek w bibliotece");
        }
    }

    public void printMagazine(Publication[] publications) {
        int countMagazine = 0;
        for (Publication publication : publications) {
            if (publication instanceof Magazine) {
                printLine(publication.toString());
                countMagazine++;
            }
        }
        if (countMagazine == 0) {
            printLine("Brak magazynów w bibliotece");
        }
    }

    public void printLine(String text) {
        System.out.println(text);
    }
}
