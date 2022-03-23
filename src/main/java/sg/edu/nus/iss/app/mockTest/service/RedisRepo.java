package sg.edu.nus.iss.app.mockTest.service;

// import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.mockTest.model.Book;
import java.util.List;

public interface RedisRepo {
    public List<Book> findByTitle(String title); //(method name)
    public List<Book> findByAuthor(String author);
    public Book findById(String Id);

    public List<Book> getAllBooks();
    public void storeBooks(Book book);

    // public List<book> findbyAuthorAsc(String author);
   // public List<book> findbyAuthorDes(String author);
}
