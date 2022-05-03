package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import apiException.NotFoundException;


@WebMvcTest(Controller.class)
class BookApiApplicationTests {
	@Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    
    Date now = new Date();
    Integer testId=1;
    @MockBean
    BookService bks;
    Book RECORD_1 = new Book(testId ,"boook","david",123,"ok store",now,120);
    Book RECORD_2 = new Book(2 ,"boook2","david",123,"ok store",now,120);    

    @Test
    public void getAllRecords_success() throws Exception {
        List<Book> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2));
        
        Mockito.when(bks.findAll()).thenReturn(records);
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/find")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("boook2")));
    }
    
   
    @Test
    public void getFindRecords_success() throws Exception {
        List<Book> records = new ArrayList<>(Arrays.asList(RECORD_1));
        
        Mockito.when(bks.findOne(testId)).thenReturn(RECORD_1);
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/find/"+testId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(7)))
                .andExpect(jsonPath("$.name", is("boook")));
    }
    
    @Test
    public void createRecord_success() throws Exception {
        Book book = Book.builder()
        		
                .name("Taipei")
                .author("James")
                .ISBN(1234)
                .publishDate(now)
                .publisher("gooder")
                .price(500)
                .build();

        Mockito.when(bks.insert(book)).thenReturn(book);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(book));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
        		.andExpect(jsonPath("$.name", is("Taipei")));

        }
    
    @Test
    public void updateBookRecord_success() throws Exception {
        Book updatedRecord = Book.builder()
                 .id(testId)
        		 .name("Taipei")
                 .author("James")
                 .ISBN(1234)
                 .publishDate(now)
                 .publisher("gooder")
                 .price(500)
                 .build();

        Mockito.when(bks.findOne(RECORD_1.getId())).thenReturn(RECORD_1);
        Mockito.when(bks.update(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
        		.andExpect(jsonPath("$", containsString("Succeeded.")));
        
                
    }
    
    
    @Test
    public void updateById_notFound() throws Exception {
    	
    	int testCase=10;
    	Book updatedRecord = Book.builder()
                .id(testCase)
       		 .name("Taipei")
                .author("James")
                .ISBN(1234)
                .publishDate(now)
                .publisher("gooder")
                .price(500)
                .build();
        Mockito.when(bks.findOne(testCase)).thenReturn(null);
        Mockito.when(bks.update(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
        .andExpect(status().isBadRequest())
                .andExpect(result ->
                Assert.assertTrue(result.getResolvedException() instanceof NotFoundException))
        .andExpect(result ->
                Assert.assertEquals("Book with ID " + testCase + " does not exist.", result.getResolvedException().getMessage()));
    }
    
    @Test
    public void deleteBookRecord_success() throws Exception {
    	Mockito.when(bks.findOne(RECORD_1.getId())).thenReturn(RECORD_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/delete/"+RECORD_1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    

    
    @Test
    public void deletePatientById_notFound() throws Exception {
    	
    	int testCase=10;
        Mockito.when(bks.findOne(testCase)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/delete/"+testCase)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
                .andExpect(result ->
                Assert.assertTrue(result.getResolvedException() instanceof NotFoundException))
        .andExpect(result ->
                Assert.assertEquals("Book with ID " + testCase + " does not exist.", result.getResolvedException().getMessage()));
    }
    
    
    
	@Test
	void contextLoads() {
	}

}
