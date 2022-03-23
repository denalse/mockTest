package sg.edu.nus.iss.app.mockTest.controller;

import java.util.List;
import org.slf4j.*;

// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.app.mockTest.model.Book;
import sg.edu.nus.iss.app.mockTest.service.BookRepo;
// import sg.edu.nus.iss.app.mockTest.service.RedisRepo;

@Controller
public class LibraryController {
    Logger logger = LoggerFactory.getLogger(LibraryController.class);


    @Autowired
    private BookRepo bookSvc;

    @GetMapping("/")
    public String showSearchPage() {
        
        return "searchPage";
    }

    @GetMapping(value = "/search", params = "searchAuthor")
    public String searchAuthor(Model model, @RequestParam("search") String author) {
        List<Book> result = bookSvc.findByAuthor(author);
        logger.info("Search author result>> " + result);
        model.addAttribute("bookList", result);
        return "showBookList";
    }

    @GetMapping(value = "/search", params = "searchTitle")
    public String searchTitle(Model model, @RequestParam("search") String title) {
        List<Book> result = bookSvc.findByTitle(title);
        logger.info("Search title result>> " + result);
        model.addAttribute("bookList", result);
        return "showBookList";
    }


    @GetMapping(value = "/search", params = "searchId")
    public String getBook(Model model, @RequestParam("search") String bookId){
        Book result = bookSvc.findById(bookId);
    
        logger.info("result >> " + result.getBookId());
        logger.info("result >> " + result.getTitle());
        logger.info("result >> " + result.getAuthor());
        model.addAttribute("book", result);

        return "showBook";
    }

    @GetMapping(value = "/search", params = "getAllBooks")
    public String getAllBook(Model model){
        List<Book> result = bookSvc.getAllBooks();
        logger.info("result >> " + result);
        model.addAttribute("bookList", result);
        return "showBookList";
    }

}