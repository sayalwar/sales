/**
 * @author Nilesh
 * It will be used for Member update screen.
 *
 */
package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.flm.market.entity.Member;

public class TestMemberDAO_updateMember {

	public static void main(String[] args) throws SQLException {
		if (args.length != 6) {
			System.out.println("使い方: java jp.co.flm.market.test.TestMemberDAO_updateMember ");
			System.exit(1);
		}


		String memberId = args[0];
		String password = args[1];
		String memberName = args[2];
		String gender = args[3];
		String phone = args[5];



		Connection con = null;
		try {

			con = ConnectionManager.getConnection();

			MemberDAO mdao = new MemberDAO(con);

			Member member = new Member();
			member.setMemberId(memberId);
			member.setPassword(password);
			member.setMemberName(memberName);
			member.setGender(gender);
			member.setPhone(phone);


			boolean flag = mdao.updateMember(member);

			if (flag == true) {

				System.out.println("MemberId：" + member.getMemberId());
				System.out.println("MemberName：" + member.getMemberName());
				System.out.println("Address：" + member.getAddress());
				System.out.println("Phone：" + member.getPhone());
				System.out.println("Point：" + member.getMemberPoint());
				System.out.println("member details updated");
			} else {
				System.out.println("member details not updated");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}