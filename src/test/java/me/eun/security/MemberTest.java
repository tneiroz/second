package me.eun.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

import me.eun.AppTest;

public class MemberTest extends AppTest {

	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	@Qualifier (value = "bcryptPwEncoder")
	PasswordEncoder passwordEncoder;
	
	@Test
	@Ignore
	public void memberInsertTest() {
		String sql = "insert into member_tbl(userId,userPw,userName) values(?,?,?)";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try { 
	    	conn= dataSource.getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1, "ParkJungEun");
	    	pstmt.setString(2, passwordEncoder.encode("1234"));
	    	pstmt.setString(3, "박정은");
	    	pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void adminInsertTest() {
		String sql = "insert into member_tbl(userId,userPw,userName) values(?,?,?)";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try { 
	    	conn= dataSource.getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1, "admin");
	    	pstmt.setString(2, passwordEncoder.encode("1234"));
	    	pstmt.setString(3, "관리자");
	    	pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

//Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException:
//No qualifying bean of type 'org.springframework.security.crypto.password.PasswordEncoder' available: 
//expected single matching bean but found 2: customNoopPasswordEncoder,bCryptPasswordEncoder
// security-context.xml 에서 빈을 2개로 냅둬서 오류가 발생하였다. 

