package com.example.demo;


import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apiException.NotFoundException;

@Service
public class BookService {
	@Resource
	private BookRepository bookRepository;
	
	
	public Book findOne(Integer id) {
		return this.bookRepository.getById(id);
	}
	public List<Book> findAll() {
		return  this.bookRepository.findAll();
	}
	
	public Book insert(Book book) {
		book.setId(null);
		return this.bookRepository.save(book);
	}
	public Book update(Book book) {
		return this.bookRepository.save(book);
	}
	
	public void delete(Integer id) {
		bookRepository.deleteById(id);		
	}
}
