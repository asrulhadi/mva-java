/**
 * Part of Web Application Secure Coding - JSF
 * It is extremely insecure! Please do not use
 * this in any kind of production environment
 *
 * @author asrulhadi
 * @create 2015
 */
package my.mva.libs;

import java.sql.*;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DB implements Serializable {

  // Singleton for DB connection.
  // All sharing the same connection
  private static DB instance = null;
  private static Connection con = null;

  private String host = "localhost";
  private String username = "mva";
  private String password = "mva";
  private String db_name = "insecure";

  protected DB() {
    this.connect(this.db_name);
    System.out.println("Connection to database");
  }

  public static DB getInstance() {
    if ((instance == null) || (con == null))  {
      instance = new DB();
    }
    return instance;
  }

  public int createDB() {
    if ( con != null ) this.close();
    this.connect("");
    int res = this.execute("CREATE DATABASE IF NOT EXISTS " + this.db_name);
    this.reconnect();
    return res;
  }

  public void reconnect() {
    this.close();
    this.connect(this.db_name);
  }

  protected void connect(String db_name) {
    boolean createDB = false;
    if ( con != null ) return;
    String url = "jdbc:mysql://" + this.host + "/" + db_name + "?useSSL=false&autoReconnect=true";
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(url, username, password);
      System.out.println("Connection successful");
    } catch (ClassNotFoundException cnf) {
      System.out.println(cnf.getMessage());
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void close() {
    try {
      con.close();
      System.out.println("Closing connection");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      con = null;
    }
  }

  public ResultSet query(String sql) {
    ResultSet result = null;
    if ( con == null ) {
      System.out.println("Connection NULL");
      return null;
    }
    try {
      System.out.println("Query: " + sql);
      Statement st = con.createStatement();
      result = st.executeQuery(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }
    System.out.println("Query executed succesfully");
    return result;
  }

  public int execute(String sql) {
    int result = -1;
    if ( con == null ) {
      System.out.println("Connection NULL");
      return -1;
    }
    try {
      System.out.println("Execute: " + sql);
      Statement st = con.createStatement();
      if (st.execute(sql)) {
        // Select Statement
        st.getResultSet();
        result = 1;
      } else {
        // Delete, update or Insert
        result = st.getUpdateCount();
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return -1;
    }
    System.out.println("Query executed succesfully");
    return result;
  }

  public boolean update(String sql) {
    if ( con == null ) return false;
    try {
      System.out.println("Update :" + sql);
      Statement st = con.createStatement();
      st.executeUpdate(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
    System.out.println("Query executed succesfully");
    return true;
  }

	public PreparedStatement prepareStatement(String sql) {
    try {
		    return con.prepareStatement(sql);
      } catch (SQLException e) {
        return null;
      }
	}
}

// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
