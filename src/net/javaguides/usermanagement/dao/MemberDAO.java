package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.javaguides.usermanagement.model.Member;
import net.javaguides.usermanagement.model.User;

public class MemberDAO {
	private static String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=CST";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "12345678";
	private static final String INSERT_USERS_SQL = "INSERT INTO members" + "  (name, password, email) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,name,password,email from members where id =?";
	private static final String SELECT_ALL_USERS = "select * from members where name = ?";
	private static final String DELETE_USERS_SQL = "delete from members where id = ?;";
	private static final String UPDATE_USERS_SQL = "update members set name = ?,password= ?, email =? where id = ?;";
	
	public MemberDAO() {
		
	}
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public static Member selectMember(String mname) {
		Member member = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			preparedStatement.setString(1, mname);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				member = new Member(id, name, password, email);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return member;
	}
	
	public static boolean updateMember(Member member) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, member.getName());
			statement.setString(2, member.getPassword());
			statement.setString(3, member.getEmail());
			statement.setInt(4, member.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	private static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
