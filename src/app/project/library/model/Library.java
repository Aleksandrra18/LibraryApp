package app.project.library.model;

public class Library {

    private static final int MAX_PUBLICATIONS = 2000;
    private int publicationNumber;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];

    public void addBook(Book book) {
        if (publicationNumber < MAX_PUBLICATIONS) {
            publications[publicationNumber] = book;
            publicationNumber++;
        } else {
            System.out.println("Maksymalna liczna książek została osiągnięta");
        }
    }

    public void addMagazine(Magazine magazine) {
        if (publicationNumber < MAX_PUBLICATIONS) {
            publications[publicationNumber] = magazine;
            publicationNumber++;
        } else {
            System.out.println("Maksymalna liczna magazynów została osiągnięta");
        }
    }

    public void printBooks() {
        int countBooks = 0;
        for (int i = 0; i < publicationNumber; i++) {
            if (publications[i] instanceof Book) {
                System.out.println(publications[i]);
                countBooks++;
            }
        }
        if (countBooks == 0) {
            System.out.println("Brak książek w bibliotece");
        }
    }


    public void printMagazine() {
        int countMagazine = 0;
        for (int i = 0; i < publicationNumber; i++) {
            if (publications[i] instanceof Magazine) {
                System.out.println(publications[i]);
                countMagazine++;
            }
        }
        if (countMagazine == 0) {
            System.out.println("Brak magazynów w bibliotece");
        }
    }
}
