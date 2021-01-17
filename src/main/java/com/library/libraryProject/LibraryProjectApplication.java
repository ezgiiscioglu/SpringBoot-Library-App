package com.library.libraryProject;

import com.library.libraryProject.model.Author;
import com.library.libraryProject.model.Book;
import com.library.libraryProject.model.Publisher;
import com.library.libraryProject.service.AuthorService;
import com.library.libraryProject.service.BookService;
import com.library.libraryProject.service.PublisherService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.transaction.Transactional;


@SpringBootApplication
public class LibraryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryProjectApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return new ModelMapper();
	}

	@Bean
	@Transactional
	public CommandLineRunner initialCreate(BookService bookService) {
		return (args) -> {

			Book book = new Book("Zıkkımın Kökü", "National Bestseller", "-", "9789754940480", "Muzaffer İzgü'nün Adana'daki bir gecekondu mahallesinde geçen çocukluğunu anlatan romanıdır");
			Author author = new Author("Muzaffer İzgü", "Muzaffer İzgü");
			Publisher publisher = new Publisher(" Bilgi Yayınevi", " Bilgi Yayınevi");

			//book.addAuthors(author);  //author.getBooks().add(book);
			//book.addPublishers(publisher);
			book.setAuthor(author);
			book.setPublisher(publisher);
			bookService.save(book);

			Book book1 = new Book("Queen's Gambit", "-", "-", "12365", "Vezir Gambiti, Walter Tevis'in 1983 tarihli Amerikan romanı olup, bir kadın satranç dahisinin hayatını anlatır.");
			Author author1 = new Author("Walter Tevis", "Walter Tevis");
			Publisher publisher1 = new Publisher("Ithaki Yayınları", "Ithaki Yayınları");

			book1.setAuthor(author1);
			book1.setPublisher(publisher1);
			bookService.save(book1);

			Book book2 = new Book("Bir yumak Mutluluk", "New York Times BestSeller", "-", "547457422", "From D. Macomber");
			Author author2 = new Author("Debbie Macomber", "Explore World");
			Publisher publisher2 = new Publisher("MARTI", "Martı");

			book2.setAuthor(author2);
			book2.setPublisher(publisher2);
			bookService.save(book2);

			Book book3 = new Book("Masumiyet Müzesi", "Aşk ve Müze üzerine Yazarın son sözüyle", "-", "978-975-08-2614-6", "Hayatımın en mutlu anıymış, bilmiyordum.");
			Author author3 = new Author("Orhan Pamuk", "Nobel ödüllü yazar");
			Publisher publisher3 = new Publisher("YKY", "YKY");

			book3.setAuthor(author3);
			book3.setPublisher(publisher3);
			bookService.save(book3);
		};
	}
}
