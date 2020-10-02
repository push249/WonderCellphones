package com.webmobilecart.mobileapp.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webmobilecart.mobileapp.dao.OrderDAO;

public class OrderDAOTest {
	@Autowired
	private OrderDAO orderdao;
	@Test
	public void testAddUser()
	{
		int expectedRowDeleted = 1;	
		int rowsdeleted = orderdao.removeOrder(1, 1);
		assertEquals(rowsdeleted ,expectedRowDeleted);
	}
}
