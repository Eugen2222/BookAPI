package com.example.demo;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import apiException.InvalidRequestException;
import apiException.NotFoundException;
import lombok.Data;
@RestController 
public class Controller {
	@Resource
	BookService bookService;
	@RequestMapping(value="/find/{id}", method=RequestMethod.GET)
	public Book getOne(@PathVariable Integer id) {
		System.out.println("get id "+id);
		Book book = this.bookService.findOne(id);
		if (book ==null) {
	        throw new NotFoundException("Book with ID " + book.getId() + " does not exist.");
	    }
		return book;
	}
	
	@RequestMapping(value="/find/", method=RequestMethod.GET)
	public List<Book> getAll() {
	
		return this.bookService.findAll();
	}
	
	
	@PostMapping(value="/insert")
	@ResponseBody
	public Book insert(@RequestBody Book book) {
		System.out.println("got insert post");
		System.out.println("got parameter id="+book.toString());
		//for
		book.setId(null);
		
		book=this.bookService.insert(book);
	
		return book;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseBody
	public String update(@RequestBody @Validated Book book) {
		
		if (book == null || book.getId()== null) {
	        throw new InvalidRequestException("Book or ID must not be null!");
	    }
		
		System.out.println("got update");
		System.out.println("got parameter id="+book.getId());
		System.out.println("got parameter ="+book.toString());
	
		
		if (bookService.findOne(book.getId())==null) {
	        throw new NotFoundException("Book with ID " + book.getId() + " does not exist.");
	    }
		Book res=this.bookService.update(book);
		return "Successed. Book id:"+book.getId()+" had been updated. ";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable Integer id) {
		System.out.println("got delete");
		System.out.println("got parameter id="+id);
		if (bookService.findOne(id)==null) {
	        throw new NotFoundException("Book with ID " + id + " does not exist.");
	    }
//		if ( id== null) {
//	        throw new InvalidRequestException("Book ID must not be null!");
//	    }

		this.bookService.delete(id);
		System.out.println("controller");
		return  "Successed. Book :"+id+" had been deleted. ";
	}
}
