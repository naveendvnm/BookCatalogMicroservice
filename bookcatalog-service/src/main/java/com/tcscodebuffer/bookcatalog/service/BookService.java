package com.tcscodebuffer.bookcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tcscodebuffer.bookcatalog.entity.Book;
import com.tcscodebuffer.bookcatalog.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	
	/*
	 * Kafka Template
	 * */
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	String kafkaTopic = "javatopic";
	
	 
	// Posting a book
	public Book saveBook(Book book) {
		kafkaTemplate.send(kafkaTopic, "Book Record is saved under JavaTopic Kafka-Zookeeper!!!");
		return repository.save(book);
	}
	
	// Posting all books
	public List<Book> saveBooks(List<Book> books) {
		kafkaTemplate.send(kafkaTopic, "Books Record is saved under JavaTopic Kafka-Zookeeper!!!");
		return repository.saveAll(books);
	}
	
	public List<Book> getBooks() {
		return repository.findAll();
	}
	
	public Book getBookById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	// title name
	public List<Book> getBookByTitle(String title) {
		return repository.findByTitle(title);
	}	
	
	// author
	public List<Book> getBookByAuthName(String authname) {
		return repository.findByAuthname(authname);
	}
	
	// ISBN
	public List<Book> getBookByIsbn(String isbn) {
		return repository.findByIsbn(isbn);
	}
	
	public String deleteBook(int id) {
		repository.deleteById(id);
		return "Book removed || "+id;
	}
	
	public Book updateBook(Book book) {
		Book existingBook = repository.findById(book.getTitleid()).orElse(null);
		existingBook.setTitle(book.getTitle());
		existingBook.setAuthname(book.getAuthname());
		existingBook.setIsbn(book.getIsbn());
		existingBook.setPubdate(book.getPubdate());
		
		return repository.save(existingBook);
	}
}
