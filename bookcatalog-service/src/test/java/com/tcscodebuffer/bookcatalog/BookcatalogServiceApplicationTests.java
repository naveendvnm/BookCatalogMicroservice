package com.tcscodebuffer.bookcatalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcscodebuffer.bookcatalog.entity.Book;

@SpringBootTest
class BookcatalogServiceApplicationTests {

	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void addBookTest() throws Exception {

		String uri = "/addBook";
		Book book = new Book();

		book.setTitleid(1);
		book.setTitle("Client Server Computing");
		book.setAuthname("Lalit Kumar");
		book.setIsbn("978-93-8067-432-2");
		book.setPubdate("05-10-2021");

		String inputJson = super.mapToJson(book);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "books is created successfully");
	}

	@Test
	public void getBooksTest() throws Exception {
		String uri = "/books";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Book[] booklist = super.mapFromJson(content, Book[].class);
		assertTrue(booklist.length > 0);

	}

	@Test
	public void getBookByIdTest() throws Exception {
		String uri = "/bookcatalog/bookById/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Book[] bookdetail = super.mapFromJson(content, Book[].class);
		assertTrue(bookdetail.length > 0);
	}

	@Test
	public void getBookByTitleTest() throws Exception {
		String uri = "/bookcatalog/bookByTitle/Client%20Server%20Computing";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Book[] booktitle = super.mapFromJson(content, Book[].class);
		assertTrue(booktitle.length > 0);
	}

	@Test
	public void getBookByAuthName() throws Exception {
		String uri = "/bookcatalog/bookByAuthName/Lalit%20Kumar";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Book[] bookauthname = super.mapFromJson(content, Book[].class);
		assertTrue(bookauthname.length > 0);
	}

	@Test
	public void getBookByIsbnTest() throws Exception {
		String uri = "/bookcatalog/bookByIsbn/978-93-8067-432-2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Book[] bookisbn = super.mapFromJson(content, Book[].class);
		assertTrue(bookisbn.length > 0);
	}

	@Test
	public void updateTest() throws Exception {
		String uri = "/books";
		Book book1 = new Book();

		book1.setTitleid(1);
		book1.setTitle("Junior Level Books Introduction to Computer");
		book1.setAuthname("Amit Garg");
		book1.setIsbn("978-93-5019-561-1");
		book1.setPubdate("01-01-2021");

		String inputJson = super.mapToJson(book1);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "book record is updated successsfully");
	}

	@Test
	public void deleteByIdTest() throws Exception {
		String uri = "/bookcatalog/deleteById/3";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "book record is deleted successsfully");
	}

}
