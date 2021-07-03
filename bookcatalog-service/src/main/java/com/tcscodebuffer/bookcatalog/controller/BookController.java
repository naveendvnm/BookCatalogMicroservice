package com.tcscodebuffer.bookcatalog.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcscodebuffer.bookcatalog.entity.Book;
import com.tcscodebuffer.bookcatalog.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bookcatalog")
@Slf4j
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book) {
		
		//log.info("Inside addBook method of BookController");
		return service.saveBook(book);
	}

	@PostMapping("/addBooks")
	public List<Book> addAllBooks(@RequestBody List<Book> books) {
		return service.saveBooks(books);
	}

	@GetMapping("/books")
	public List<Book> findAllBooks() {
		return service.getBooks();
	}

	@GetMapping("/bookById/{id}")
	public Book findBookById(@PathVariable int id) {
		return service.getBookById(id);
	}

	@GetMapping("/bookByTitle/{title}")
	public List<Book> findBookByTitle(@PathVariable String title) {
		return service.getBookByTitle(title);
	}
	
	@GetMapping("/bookByAuthName/{authname}")
	public List<Book> findBookByAuthName(@PathVariable String authname) {
		return service.getBookByAuthName(authname);
	}
	
	@GetMapping("/bookByIsbn/{isbn}")
	public List<Book> findBookByIsbn(@PathVariable String isbn) {
		return service.getBookByIsbn(isbn);
	}
	
	@PutMapping("/update")
	public Book updateBook(@RequestBody Book book) {
		return service.updateBook(book);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteBook(@PathVariable int id) {
		return service.deleteBook(id);
	}
}
