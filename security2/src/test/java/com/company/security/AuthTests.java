package com.company.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 현재 클래스가 테스트 클래스고, 테스트 코드가 스프링을 실행
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"}) 
public class AuthTests {

	@Autowired
	private DataSource ds;

	@Test // 테스트 메소드는 - @Test 어노테이션 사용 / public, 파라미터 없고, void 타입
	public void test() {
		String sql = "insert into spring_member_auth(userid,auth) values(?,?)";
		
		for (int i = 0; i<100; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				if (i<80) {
					pstmt.setString(1, "user"+i); // user0 ~ user79
					pstmt.setString(2, "ROLE_USER");
				} else if (i<90) {
					pstmt.setString(1, "manager"+i); // manager80 ~ manager89
					pstmt.setString(2, "ROLE_MEMBER");
				}else {
					pstmt.setString(1, "admin"+i); // admin 90 ~ admin 99
					pstmt.setString(2, "ROLE_ADMIN");
				}	
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				if (con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
}
