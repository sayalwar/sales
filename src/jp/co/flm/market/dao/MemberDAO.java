/**
 * jp.co.flm.market.dao.ConnectionManager
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.flm.market.entity.Member;

/**
 * getMember->get the member information
 *
 * @author Sayawar.nilesh
 * @version 1.0 2023/01/13
 */
public class MemberDAO {

	private Connection con;

	public MemberDAO(Connection con) {
		this.con = con;
	}

	/**
	 * @param memberId Memberid is stored
	 * @param password  Member password is stored
	 * @return member
	 * @throws SQLException
	 */
	public Member getMember(String memberId, String password) throws SQLException {
		// 戻り値の準備
		Member member = null;

		// SQL文の準備
		String sql = "SELECT memberid,password,membername, gender, address, phone, memberpoint " +
				"FROM member WHERE memberid=? AND password=?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, password);
			res = stmt.executeQuery();
			if (res.next()) {
				member = new Member();
				member.setMemberId(res.getString("memberid"));
				member.setMemberName(res.getString("membername"));
				member.setPassword(res.getString("password"));
				member.setGender(res.getString("gender"));
				member.setAddress(res.getString("address"));
				member.setPhone(res.getString("phone"));
				member.setMemberPoint(res.getInt("memberpoint"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return member;
	}

	//nilesh added
	/**
	 * @param updatemember It is a object of type Member used for updating the Member information in Member Table
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateMember(Member updatemember) throws SQLException {
		// SQL文の準備

		String sql = "update member set password=?, membername=?, address=?, phone=? where memberid=?";
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, updatemember.getPassword());
			stmt.setString(2, updatemember.getMemberName());
			stmt.setString(3, updatemember.getAddress());
			stmt.setString(4, updatemember.getPhone());
			stmt.setString(5, updatemember.getMemberId());

			int num = stmt.executeUpdate();
			if (num == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}

		return false;
	}
	//nilesh end

	//start update Parmeshwari
	/**
	 * @param email Hold the MemberID used for checking weather memberId already exist or not
	 * @return status it is type of Boolean if memberID exist it return False else True
	 * @throws SQLException
	 */
	public boolean checkMemberId(String email) throws SQLException {
		String sql = "SELECT * from member where memberid=?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		boolean status = false;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			res = stmt.executeQuery();
			if (res.next()) {
				status = false;
			} else {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return status;
	}

	/**
	 * @param member Object type of Member  having the member information for registration of new Member
	 * @return status it is type of Boolean if Member data is registred in Database it will return True else False.
	 * @throws SQLException
	 */
	public boolean memberRegistration(Member member) throws SQLException {
		String sql = "insert into MEMBER(memberid,password,membername,gender,address,phone,memberpoint) values(?,?,?,?,?,?,?)";

		PreparedStatement stmt = null;
		int res = 0;
		boolean status = false;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getMemberName());
			stmt.setString(4, member.getGender().substring(0, 1));
			stmt.setString(5, member.getAddress());
			stmt.setString(6, member.getPhone());
			stmt.setInt(7, member.getMemberPoint());
			res = stmt.executeUpdate();

			if (res != 0) {
				status = true;
			} else {
				status = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (res != 0) {
				stmt.close();
			}
		}
		return status;

	}
	//Update completed Parmeshwari

	//Pragati Update Start
	/**
	 * @param member object type of Member having memberpoint and memberid.
	 * @param totalPoint Type of Integer having the total Point when order is purchased by the user.
	 * @return flag it is type of Boolean if member point  is updated on the basis of Memberid in Database it will return True else False.
	 * @throws SQLException
	 */
	public boolean setMember(Member member, int totalPoint) throws SQLException {
		// SQL文の準備

		String sql = "update MEMBER set  memberpoint=? where memberid=?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		int result = 0;
		boolean flag = false;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, totalPoint + member.getMemberPoint());
			stmt.setString(2, member.getMemberId());

			// SQL文を実行する。
			result = stmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			flag = true;
		}
		return flag;
	}
	//Pragati update Ends

}
