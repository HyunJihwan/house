package kr.or.dw.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest02 {
	// 문제1) 사용자로부터 empno 값을 입력받아 입력한 값보다 empno 가 큰 자료들을 출력하시오
	
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;	// 사용할 SQL문이 SELECT문일경우 필요
		Scanner sc = new Scanner(System.in);
		int input = 0;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "dron4gi", "java");
			
			
			String sql = "select * from emp";
			
			
			stmt = conn.createStatement();
			
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("empno 숫자를 입력해주세요");
			input = Integer.parseInt(sc.nextLine());
			System.out.println("━━━━━━━━━━━━━━━━━━━━ SQL문의 처리 결과━━━━━━━━━━━━━━━━━━━━━━━");
			while(rs.next()) {
				if(rs.getInt("empno") > input) {
					System.out.print("empno : " + rs.getInt("empno") + "\t");
					System.out.print("ename : " + rs.getString(2) + "\t");
					System.out.print("job : " + rs.getString("job") + "\t");
					System.out.println();
				}

			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7. 사용했던 자원 반납
			// close 하기전에는 메모리 상에 파일을 쓰기 위한 정보를 넣어 놓는거고,
			// close 하는 시점에 쓰기 처리가 되는것.
			if(rs != null) try {rs.close();} catch(SQLException e2) {}
			if(conn != null) try {conn.close();} catch(SQLException e2) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e2) {}
		}
		

	
	}
	
	
}