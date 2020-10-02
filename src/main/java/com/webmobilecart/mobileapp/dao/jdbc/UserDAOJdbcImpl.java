package com.webmobilecart.mobileapp.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;









import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.inject.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.webmobilecart.mobileapp.dao.UserDAO;
import com.webmobilecart.mobileapp.domain.User;
import com.webmobilecart.mobileapp.exceptions.*;
@Repository("UserDaoJdbc")
public class UserDAOJdbcImpl implements UserDAO {
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
	public int addUser(User user){
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	}
	public int addUser(String firstName, String lastName, String userName, String eMail, String password, String address, String contactNo){
		try{	
			int rowsAffected;
			Connection dbConn;
			String queryStr = "insert into user (FirstName, LastName, UserName, eMail, password, address, contactNo) values ("+firstName+","+lastName+","+userName+","+eMail+","+password+","+password+","+contactNo+")";
			dbConn =  null;// getConnection();
			Statement queryStmt = dbConn.createStatement();
			dbConn.setAutoCommit(false);
			rowsAffected = queryStmt.executeUpdate(queryStr);
			if(rowsAffected !=1)
			{
				dbConn.rollback();
				throw new DbFailure("User already exists");
			}
			else{
				dbConn.commit();
			}
			queryStmt.close();
			
		}
		catch(Exception e){System.out.println(e);}
		return 1;
	}
	
	public static void updateUser(String userName, String password, String address, String contactNo){
		try{
			Connection dbConn;
			String queryStr = "SELECT * " + "FROM user " + "WHERE UserName like '" + userName + "' AND password like '"+password+"'";
			
			dbConn =  null;// getConnection();
			Statement queryStmt = dbConn.createStatement();
			ResultSet results;
			
			results = queryStmt.executeQuery(queryStr);
			int idUser = results.getInt("idUser");
			if(contactNo == null){
				queryStr = "UPDATE user SET address = '"+address+"' WHERE idUser = '"+idUser+"'";
				queryStmt.executeQuery(queryStr);	
				System.out.println("User Updated");
			}
			if(address == null){
				queryStr = "UPDATE user SET contactNo = '"+contactNo+"' WHERE idUser = '"+idUser+"'";
				queryStmt.executeQuery(queryStr);	
				System.out.println("User Updated");
			}
			if(address != null && contactNo != null){
				queryStr = "UPDATE user SET address = '"+address+"' and contactNo = '"+contactNo+"' WHERE idUser = '"+idUser+"'";
				queryStmt.executeQuery(queryStr);
				System.out.println("User Updated");
			}
			else{
				System.out.println("No data updated");
			}
			results.close();
			queryStmt.close();
			
		}
		catch(Exception e){System.out.println(e);} 
	}
	public static void getUser(String userName, String password){
		try{
			Connection dbConn;
			String queryStr = "SELECT * " + "FROM user " + "WHERE UserName like '" + userName + "' AND password like '"+password+"'";
			
			dbConn =  null;// getConnection();
			Statement queryStmt = dbConn.createStatement();
			ResultSet results;
			
			results = queryStmt.executeQuery(queryStr);
			while (results.next()) {
				//Display user details
			}
			results.close();
			queryStmt.close();
			}
			catch(Exception e){System.out.println(e);}
	}

	public static void removeUser(String userName, String password){
		try{
			Connection dbConn;
			String queryStr = "SELECT * " + "FROM user " + "WHERE UserName like '" + userName + "' AND password like '"+password+"'";
			
			dbConn =  null;// getConnection();
			Statement queryStmt = dbConn.createStatement();
			ResultSet results;
			
			results = queryStmt.executeQuery(queryStr);
			int idUser = results.getInt("idUser");
			queryStr = "DELETE FROM user WHERE idUser = '"+idUser+"'";
			queryStmt.executeQuery(queryStr);
			System.out.println("User Deleted");
			results.close();
			queryStmt.close();
			
		}
		catch(Exception e){System.out.println(e);}
	}
	
}
