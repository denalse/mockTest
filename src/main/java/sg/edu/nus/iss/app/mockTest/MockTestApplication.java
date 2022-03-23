package sg.edu.nus.iss.app.mockTest;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;	
import org.springframework.data.redis.core.RedisTemplate;


import sg.edu.nus.iss.app.mockTest.model.Book;
import sg.edu.nus.iss.app.mockTest.service.BookRepo;

@SpringBootApplication
public class MockTestApplication {

	@Autowired
    RedisTemplate<String,Object> redisTemplate;

	@Autowired
    BookRepo service;

	Logger logger = LoggerFactory.getLogger(MockTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MockTestApplication.class, args);
	}

	@Bean
    CommandLineRunner runner(){
        return args ->{
            Integer count = (Integer)redisTemplate.opsForValue().get("count");
            if (count == null ){
                redisTemplate.opsForValue().set("count",1);
				Book book1 = new Book("1", "Ella the rose fairy", "Daisy Meadows" , "thumbnails/ella_the_rose_fairy.jpg" );
				service.storeBooks(book1);
				logger.info("book 1 >> " + book1.getTitle());
				logger.info("book 1 >> " + book1.getBookId());
				logger.info("book 1 >> " + book1.getAuthor());

				Book book2 = new Book("2", "Harry Potter and the Philosopher's Stone", "J.K. Rowling" , "thumbnails/harry-potter-p-stone.jpg" );
				service.storeBooks(book2);
				logger.info("book 2 >> " + book2.getTitle());
				logger.info("book 2 >> " + book2.getBookId());
				logger.info("book 2 >> " + book2.getAuthor());

				Book book3 = new Book("3", "The Haunted Tower", "Susannah Leigh" , "thumbnails/the_haunted_tower.jpg" );
				service.storeBooks(book3);
				logger.info("book 3 >> " + book3.getTitle());
				logger.info("book 3 >> " + book3.getBookId());
				logger.info("book 3 >> " + book3.getAuthor());
            }
        };
	}
}