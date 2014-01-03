package project.LoginProject.dao;

import project.LoginProject.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static project.LoginProject.util.DbUtil.close;

/**
 * DB에 존재하는 데이터들을 sql 문장을 이용해서 다루는 클래스들, 즉
 * DAO(Data Access Object)들이 존재하는 패키지이다.
 */
public class LoginDao {
    Connection con;

    public LoginDao(Connection con) {
        super();
        this.con = con;
    }

    public User getUserLogin(String id, String passwd) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            String sql = "select * from users where id=? and passwd=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, passwd);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setAddr(rs.getString("addr"));
                user.setId(rs.getString("id"));
                user.setJob(rs.getString("job"));
                user.setPasswd(rs.getString("passwd"));
                user.setSalary(rs.getString("salary"));
                user.setName(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }

        return user;
    }
}
