package sg.edu.nus.iss.app.mockTest.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.mockTest.model.Book;

@Service
public class BookRepo implements RedisRepo {
    private static final Logger logger = LoggerFactory.getLogger(BookRepo.class);
    private static final String BOOK_ENTITY = "bookList";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Book findById (String bookId) {
        Book resultById = (Book) redisTemplate.opsForHash().get(BOOK_ENTITY + "_Map" + bookId, bookId);
        logger.info("resultById >>" + resultById);
        return resultById;
    
    }
    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = (List<Book>)(Object) redisTemplate.opsForHash().values(BOOK_ENTITY+"_Map");
        List<Book> findByAuthor = new ArrayList<Book>();
        for (Book book : books){

            if (book.getAuthor().toLowerCase().equals(author.toLowerCase())){
                findByAuthor.add(book);
                logger.info("Author found>> " + book.getAuthor());
            }
        }
        return findByAuthor;
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = (List<Book>)(Object) redisTemplate.opsForHash().values(BOOK_ENTITY + "_Map");
        List<Book> findByTitle = new ArrayList<Book>();
        for (Book book : books){
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())){
                findByTitle.add(book);
            }
        }
        return findByTitle;
    }

    @Override
    public List<Book> getAllBooks() {
        // List<Object> fromBookList = redisTemplate.opsForList().range(BOOK_ENTITY, 0, 10);
        // List<Book> books = (List<Book>) redisTemplate.opsForHash().multiGet(BOOK_ENTITY + "_Map",fromBookList)
        //                 .stream().filter(Book.class::isInstance).map(Book.class::cast).toList();
        List<Book> books = (List<Book>)(Object) redisTemplate.opsForHash().values(BOOK_ENTITY + "_Map");
        return books;
    }

    @Override
    public void storeBooks(Book book) {
        redisTemplate.opsForList().leftPush(BOOK_ENTITY, book);
        redisTemplate.opsForHash().put(BOOK_ENTITY + "_Map", book.getBookId(), book);
    }
    
}
