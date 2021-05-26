package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ��ȣȭ�� ��й�ȣ�� �Է¹޾� �α��� ����
 * @author user
 *
 */
public class UserDAO {
	/**
	 * ���̵�� ��й�ȣ�� �Է¹޾� �ΰ��� ������ DB Table���� ��ȸ�Ǵ� ����� �ִٸ�ˤ�
	 * �α��� �������� ���� LoginDataVO�� �����Ͽ� ��ȸ �÷� ���� �Է��� �� ��ȯ.
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public LoginDataVO selecLogin(LoginVO lVO) throws SQLException{
		LoginDataVO ldVO=null;
		
		DbConnection dc = DbConnection.getInstance();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
		//1. Connection ���
			con=dc.getConn();
		//2. ������ ������ü ���
			String selectQuery="select name,email from test_login where id=? and pass=?";
			pstmt = con.prepareStatement(selectQuery);
		//3. ���ε� ������ �� �Ҵ�
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPass());
		//4. ������ ������ ��� ���
			rs=pstmt.executeQuery();
					
			if(rs.next()) {//where���� id�� ��й�ȣ�� ��ȸ�� ���ڵ尡 ����. �α��� ����.
				ldVO= new LoginDataVO(rs.getString("name"), rs.getString("email"));
			}//end if
		//5. ���� ����
		}finally {
			dc.dbClose(con, pstmt, rs);
		}//end finally
		
		return ldVO;
	}
}
