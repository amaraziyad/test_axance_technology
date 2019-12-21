package fr.d2factory.libraryapp.book;

/**
 * A simple representation of a book
 */
public class Book {

    /*
        La classe ISBN n'a pas vraiment d'importance donc j'ai modifié le type de l'ISBN et j'ai utilisé directement un attribue
        long pour l'ISBN
     */
    private String title;
    private String author;
    private Long isbn;


    public Book(){

    }
    public Book(String title, String author, Long isbn) {
        this.title=title;
        this.author=author;
        this.isbn= isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
}
