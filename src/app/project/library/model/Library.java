package app.project.library.model;

public class Library {

    private static final int MAX_BOOKS = 1000;
    private static final int MAX_MAGAZINE = 1000;
    private Book[] books = new Book[MAX_BOOKS];
    private Magazine[] magazines = new Magazine[MAX_MAGAZINE];
    private int booksNumber;
    private int magazineNumber;

    public void addBook(Book book){
        if(booksNumber < MAX_BOOKS){
            books[booksNumber] = book;
            booksNumber++;
        }else{
            System.out.println("Maksymalna liczna książek została osiągnięta");
        }
    }

    public void addMagazine(Magazine magazine){
        if(magazineNumber < MAX_MAGAZINE){
            magazines[magazineNumber] = magazine;
            magazineNumber++;
        }else{
            System.out.println("Maksymalna liczna magazynów została osiągnięta");
        }
    }

    public void printBooks(){
        if(booksNumber == 0){
            System.out.println("Brak książek w bibliotece");
        }
        for(int i = 0; i < booksNumber; i++){
            books[i].getBookInfo();
        }
    }

    public void printMagazine(){
        if(magazineNumber == 0){
            System.out.println("Brak magazynów w bibliotece");
        }
        for(int i = 0; i < magazineNumber; i++){
            magazines[i].getMagazineInfo();
        }
    }
}
