package app.project.library.model;

public class Book extends Publication {

    private String author;
    private int pages;
    private String isbn;

    public Book(int year, String title, String publisher, String author, int pages, String isbn) {
        super(year, title, publisher);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void getBookInfo() {
        String info = getTitle() + "; " + author + "; " + getYear() + "; " + pages + "; " + getPublisher() + "; " + isbn;
        System.out.println(info);
    }
}
