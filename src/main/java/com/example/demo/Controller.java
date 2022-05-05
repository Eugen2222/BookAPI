package com.example.demo;

import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
		Book book = bookService.findOne(id);
		try {
			book.getName();
		}
		catch (Exception e){
	        throw new NotFoundException("Book with ID " + book.getId() + " does not exist.");
	    }
		return book;
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public List<Book> getAll() {
	
		return bookService.findAll();
	}
	
	
	@PostMapping(value="/insert")
	@ResponseBody
	public Book insert(@RequestBody  Book book) {
		System.out.println("got insert post");
		System.out.println("got parameter id="+book.toString());
		book=bookService.insert(book);	
		return book;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseBody
	public String update(@RequestBody @Validated Book book) {
		try {
			book.getId();
		}catch (Exception e){
			 throw new InvalidRequestException("Book or ID must not be null!");
		}
		System.out.println("got update");
		System.out.println("got parameter id="+book.getId());
		System.out.println("got parameter ="+book.toString());
	
		try {
			bookService.findOne(book.getId()).getName();
		}catch(Exception e) {
	        throw new NotFoundException("Book with ID " + book.getId() + " does not exist.");
	    }
		Book res=bookService.update(book);
		return "Succeeded. The information of Book id:"+res.getId()+" had been updated. ";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable Integer id) throws Exception{
		System.out.println("got delete");
		System.out.println("got parameter id="+id);
		try {
			this.bookService.findOne(id).getName();
		}
		catch(Exception e) {
	        throw new NotFoundException("Book with ID " + id + " does not exist.");
	    }
		this.bookService.delete(id);

		return  "Succeeded. Book :"+id+" had been deleted. ";
	}
	
	
}
