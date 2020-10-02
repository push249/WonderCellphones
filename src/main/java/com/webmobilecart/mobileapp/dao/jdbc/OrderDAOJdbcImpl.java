package com.webmobilecart.mobileapp.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.inject.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.webmobilecart.mobileapp.dao.OrderDAO;
import com.webmobilecart.mobileapp.domain.Order;	
import com.webmobilecart.mobileapp.exceptions.DbFailure;
@Repository("OrderDaoJdbc")

public class OrderDAOJdbcImpl implements OrderDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("order")
		                 .usingGeneratedKeyColumns("id")
		                 .usingColumns("idUser","ProductId");
	}
	@Autowired
	public void addOrder(int idUser, int ProductId){
		try{
			Connection dbConn;
			int rowsAffected;
			String queryStr = "insert into order value ("+idUser+","+ProductId+")";
			dbConn = null;// getConnection();
			Statement queryStmt = dbConn.createStatement();
			dbConn.setAutoCommit(false);
			rowsAffected = queryStmt.executeUpdate(queryStr);
			if(rowsAffected !=1)
			{
				dbConn.rollback();
				throw new DbFailure("Order already exists");
			}
			else{
				dbConn.commit();
			}
			queryStmt.close();
		}
		catch(Exception e){System.out.println(e);}
		
	}

	public int removeOrder(int idUser, int ProductId){
		try{
			Connection dbConn;
			String queryStr = "SELECT * " + "FROM order " + "WHERE idUSer = '" + idUser + "'AND ProductId = '"+ProductId+"'";
			
			dbConn = null;//  getConnection();
			Statement queryStmt = dbConn.createStatement();
			ResultSet results;
			
			results = queryStmt.executeQuery(queryStr);
			if(results != null)
			{
				queryStr = "DELETE FROM order WHERE idUser = '"+idUser+"'AND ProductId = '"+ProductId+"'";
				queryStmt.executeQuery(queryStr);
			}
			else{
				System.out.println("Order does not exist");
			}
			results.close();
			queryStmt.close();
		}
		catch(Exception e){System.out.println(e);}
		return 1;
	}
	
	public static void searchProduct(String pName){
		try{
		Connection dbConn;
		String queryStr = "SELECT * " + "FROM product " + "WHERE ProductName like '%" + pName + "%'";
		
		dbConn = null;//  getConnection();
		Statement queryStmt = dbConn.createStatement();
		ResultSet results;
		String ProductName, Specs;
		double price;

		results = queryStmt.executeQuery(queryStr);
		while (results.next()) {
			ProductName = results.getString("ProductName");
			price = results.getDouble("Price");
			Specs = results.getString("Specs");			
			System.out.println(ProductName + "    " + price+"\n"+Specs);
		}
		results.close();
		queryStmt.close();
		}
		catch(Exception e){System.out.println(e);}
	}
}
