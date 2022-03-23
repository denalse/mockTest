package sg.edu.nus.iss.app.mockTest.model;

import java.io.Serializable;

public class Book implements Serializable{
    private String bookId;
    private String imageUrl;
    private String title;
    private String author;

    public Book(String bookId, String imageUrl, String title, String author) {
        this.bookId = bookId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //findbyTitle
    //findbyAuthorAsc
    //findbyAuthorDes
}
