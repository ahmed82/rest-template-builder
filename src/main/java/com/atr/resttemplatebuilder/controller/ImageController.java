package com.atr.resttemplatebuilder.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atr.resttemplatebuilder.module.Record;
/**
 * 
 * @author Ahmed Al-Salih Web Team
 * @version 1.0
 * @apiNote Controller consume the MuleSoft Rest API calls in-order to fetch the Images, documents, Assets from FileNet P8.
 * 
 */
@RestController
@RequestMapping("/api")
public class ImageController {
	
	 // Inject the Rest template configuration
	 @Autowired
	 RestTemplate restTemplate;

	 @GetMapping("/hh")
	private static String getEmployees()
	{
	    final String uri = "http://localhost:8081/employee";
	 
	    //TODO: Autowire the RestTemplate in all the examples
	    RestTemplate restTemplate = new RestTemplate();
		/* 1 */
	    String result = restTemplate.getForObject(uri, String.class);
	    System.out.println(result);
	    
	    /* 2 */
	    Record result2 = restTemplate.getForObject(uri, Record.class);
	    /* 3 */
	    ResponseEntity<Record> response = restTemplate.getForEntity(uri, Record.class);
	    //Use the response.getBody()
	    
	    return result;
	}
	
	@GetMapping("/ss")
	private ResponseEntity<String> getEmployees2(
			@RequestParam(name = "invoiceNumber", required = false) 	String invoiceNumber,
			@RequestParam(name = "vendorNumber", required = false) 	String vendorNumber,
			@RequestParam(name = "invoiceDate", required = false) 	String invoiceDate
			)
	{
		
		//String uri = fileNetService.getDocumentsList(invoiceNumber,vendorNumber,invoiceDate);
		
		String uri = "/v1/documents?documentclass=IndirectPayables&invoiceNumber=111111&invoiceDateStart=2011-06-21T03:59:41.105Z&invoiceDateEnd=2013-06-22T03:59:41.105Z";
	    //RestTemplate restTemplate = new RestTemplate();
		System.out.println("----------------------"+uri);
	     
	   HttpHeaders headers = new HttpHeaders();
		
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  headers.set("client-id", "clientID"); 
		  headers.set("client-secret", "Very-Secrit"); 
		  headers.set("client-type", "legalnameapp");
		 
	 
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	     
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	     return response;
	    //Use the response.getBody()
	    
		/* Sending URL Params 
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "1");
	     
	    Record result = restTemplate.getForObject(uri, Record.class, params);
	     */
	    //Use the result
	}
	
	@GetMapping("/aa")
	public List<Object> mycalling(){
		final String url = "http://localhost:8081/employee";
		Object[] objs = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(objs);
	}
	
	  @RequestMapping(value = "/template/products", method = RequestMethod.POST)
	   public String createProducts(@RequestBody Product product) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
	   }

}
