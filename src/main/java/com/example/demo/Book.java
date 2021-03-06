package com.example.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="book_name", columnDefinition = "nvarchar", nullable = false, length = 500)
	private String name;
	@Column(name="book_author", columnDefinition = "nvarchar", nullable = false, length = 255)
	private String author;
	@Column(name="book_ISBN")
	private Integer ISBN;
	@Column(name="book_publisher", columnDefinition = "nvarchar", nullable = false, length = 255)
	private String publisher;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="book_publish_date", nullable = false)
	private Date publishDate;
	@Column(name="book_price", nullable = false)
	private Integer price;

	

}
