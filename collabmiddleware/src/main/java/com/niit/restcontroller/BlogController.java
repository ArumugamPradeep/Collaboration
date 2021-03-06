package com.niit.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;

@Controller
public class BlogController {

	@Autowired
	BlogDAO blogDAO;

	@GetMapping(value = "/getAllBlogs")
	public ResponseEntity<ArrayList<Blog>> getAllBlogs() {
		ArrayList<Blog> listBlogs = new ArrayList<Blog>();

		listBlogs = (ArrayList<Blog>) blogDAO.getBlogs();

		return new ResponseEntity<ArrayList<Blog>>(listBlogs, HttpStatus.OK);

	}

	@PostMapping(value = "/createBlog")
	public ResponseEntity<String> createBlog(@RequestBody Blog blog) {
		blog.setCreateDate(new java.util.Date());
		blog.setStatus("NA");
		blog.setLikes(0);

		if (blogDAO.createBlog(blog)) {
			return new ResponseEntity<String>("Blog Created", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Problem in Blog Creation", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping(value = "/approveBlog/{blogid}")
	public ResponseEntity<String> approveBlog(@PathVariable("blogid") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.approveBlog(blog)) {
			return new ResponseEntity<String>("Blog Approved", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Problem in Blog Approval", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(value = "/deleteBlog/{blogid}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogid") int blogId) {
		if (blogDAO.deleteBlog(blogId)) {
			return new ResponseEntity<String>("Blog Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Problem in Blog Deletion", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(value = "/test")
	public ResponseEntity<String> testMethod() {
		return new ResponseEntity<String>("Test RestController", HttpStatus.OK);
	}

}
