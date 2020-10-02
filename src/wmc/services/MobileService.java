package wmc.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import wmc.domain.Product;
import wmc.exceptions.DbFailure;

public class MobileService {

    private static String dbSourceUrl = "jdbc:mysql://localhost:3306/mobilecart";
    static private Connection courseDbConn;
    static private String userId = "mobilecartuser";
    static private String dbPassword = "mobilecartpwd";
    private static ArrayList<Product> productList;
    private static Product product;

    public static Connection getConnection() throws SQLException {
        if (courseDbConn == null) {
            courseDbConn = DriverManager.getConnection(dbSourceUrl, userId, dbPassword);
        }

        return courseDbConn;
    }

    public static void shutdown() throws SQLException {
        if (courseDbConn != null) {
            courseDbConn.close();
        }
    }

    public static int addUser(String firstName, String lastName, String userName, String eMail, String password, String address, String contactNo) {
        int rowsAffected = 0;
        try {

            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "insert into user (FirstName, LastName, UserName, eMail, password, address, contactNo) values ('" + firstName + "','" + lastName + "','" + userName + "','" + eMail + "','" + password + "','" + address + "','" + contactNo + "')";
            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            dbConn.setAutoCommit(false);
            rowsAffected = queryStmt.executeUpdate(queryStr);
            if (rowsAffected != 1) {
                dbConn.rollback();
                throw new DbFailure("User already exists");
            } else {
                dbConn.commit();
            }
            queryStmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rowsAffected;
    }

    public static void updateUser(String userName, String password, String address, String contactNo) {
        try {
            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "SELECT * " + "FROM user " + "WHERE UserName like '" + userName + "' AND password like '" + password + "'";

            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            ResultSet results;

            results = queryStmt.executeQuery(queryStr);
            int idUser = results.getInt("idUser");
            if (contactNo == null) {
                queryStr = "UPDATE user SET address = '" + address + "' WHERE idUser = '" + idUser + "'";
                queryStmt.executeQuery(queryStr);
                System.out.println("User Updated");
            }
            if (address == null) {
                queryStr = "UPDATE user SET contactNo = '" + contactNo + "' WHERE idUser = '" + idUser + "'";
                queryStmt.executeQuery(queryStr);
                System.out.println("User Updated");
            }
            if (address != null && contactNo != null) {
                queryStr = "UPDATE user SET address = '" + address + "' and contactNo = '" + contactNo + "' WHERE idUser = '" + idUser + "'";
                queryStmt.executeQuery(queryStr);
                System.out.println("User Updated");
            } else {
                System.out.println("No data updated");
            }
            results.close();
            queryStmt.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int getUser(String userName, String password) {
        try {
            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "SELECT * " + "FROM user " + "WHERE UserName like '" + userName + "' AND password like '" + password + "'";

            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            ResultSet results;

            results = queryStmt.executeQuery(queryStr);
            results.next();
            if (results != null) {
                int idUser = results.getInt("idUser");
                results.close();
                queryStmt.close();
                return idUser;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void removeUser(String userName, String password) {
        try {
            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "SELECT * " + "FROM user " + "WHERE UserName like '" + userName + "' AND password like '" + password + "'";

            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            ResultSet results;

            results = queryStmt.executeQuery(queryStr);
            int idUser = results.getInt("idUser");
            queryStr = "DELETE FROM user WHERE idUser = '" + idUser + "'";
            queryStmt.executeQuery(queryStr);
            System.out.println("User Deleted");
            results.close();
            queryStmt.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int addOrder(int idUser, int ProductId) {
        int rowsAffected = 0;
        try {
            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "SELECT * " + "FROM orderproduct " + "WHERE idUser = " + idUser + " AND ProductId = " + ProductId;

            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            ResultSet results;

            results = queryStmt.executeQuery(queryStr);
            if (results != null) {
                queryStr = "insert into orderproduct values (" + idUser + "," + ProductId + ")";
                rowsAffected = queryStmt.executeUpdate(queryStr);
            } else {
                System.out.println("Order already exists");
            }
            queryStmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rowsAffected;
    }

    public static ArrayList<Product> getOrder(int idUser) {
        productList = new ArrayList<Product>();

        try {
            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "SELECT * " + "FROM orderproduct " + "WHERE idUSer = " + idUser + "";

            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            ResultSet results, temp;

            results = queryStmt.executeQuery(queryStr);

            while (results.next()) {
                int pid = results.getInt("ProductId");
                queryStr = "SELECT * " + "FROM product " + "WHERE ProductId = " + pid;
                queryStmt = dbConn.createStatement();
                temp = queryStmt.executeQuery(queryStr);
                while (temp.next()) {
                    product = new Product();
                    product.setId(temp.getInt("ProductId"));
                    product.setName(temp.getString("ProductName"));
                    product.setPrice(temp.getDouble("Price"));
                    product.setSpecs(temp.getString("Specs"));
                    product.setImage(temp.getString("Img"));
                    productList.add(product);
                }
            }
            results.close();
            queryStmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return productList;
    }

    public static void removeOrder(int idUser, int ProductId) {
        try {
            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "SELECT * " + "FROM orderproduct " + "WHERE idUser = " + idUser + " AND ProductId = " + ProductId;

            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            ResultSet results;

            results = queryStmt.executeQuery(queryStr);
            if (results != null) {
                queryStr = "DELETE FROM orderproduct WHERE idUser = " + idUser + " AND ProductId = " + ProductId;
                queryStmt.executeUpdate(queryStr);
            } else {
                System.out.println("Order does not exist");
            }
            results.close();
            queryStmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Product> searchProduct(String pName) {
        productList = new ArrayList<Product>();

        try {
            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "SELECT * " + "FROM product " + "WHERE ProductName = '" + pName + "'";

            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            ResultSet results;
            results = queryStmt.executeQuery(queryStr);
            if (results.next()) {
                results.previous();
                while (results.next()) {
                    product = new Product();
                    product.setId(results.getInt("ProductId"));
                    product.setName(results.getString("ProductName"));
                    product.setPrice(results.getDouble("Price"));
                    product.setSpecs(results.getString("Specs"));
                    product.setImage(results.getString("Img"));
                    productList.add(product);
                }
            } else {
                productList = null;
            }
            results.close();
            queryStmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return productList;
    }

    public static ArrayList<Product> listProduct() {
        productList = new ArrayList<Product>();

        try {
            Connection dbConn;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String queryStr = "SELECT * " + "FROM product ";

            dbConn = getConnection();
            Statement queryStmt = dbConn.createStatement();
            ResultSet results;
            results = queryStmt.executeQuery(queryStr);

            while (results.next()) {
                product = new Product();
                product.setId(results.getInt("ProductId"));
                product.setName(results.getString("ProductName"));
                product.setPrice(results.getDouble("Price"));
                product.setSpecs(results.getString("Specs"));
                product.setImage(results.getString("Img"));
                productList.add(product);
            }

            results.close();
            queryStmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return productList;
    }
}
