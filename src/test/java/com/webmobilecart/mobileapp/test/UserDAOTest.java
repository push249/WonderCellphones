package com.webmobilecart.mobileapp.test;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webmobilecart.mobileapp.dao.UserDAO;


@ContextConfiguration("classpath:mobilecart-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class UserDAOTest {
	@Autowired
	private UserDAO userdao;
	@Test
	public void testAddUser()
	{
		int expectedRowAdded = 1;	
		int rowsadded = userdao.addUser("a", "b", "c", "d", "e","f", "g");
		assertEquals(rowsadded ,expectedRowAdded);
	}
}
