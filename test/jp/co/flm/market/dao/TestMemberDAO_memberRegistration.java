/**
 * @author Prameshiwari
 * It will be used for registration screen.
 *
 */

package jp.co.flm.market.dao;

import java.sql.Connection;

import jp.co.flm.market.entity.Member;

public class TestMemberDAO_memberRegistration {

	public static void main(String[] args) throws Exception {

		if (args.length != 6) {
			System.out.println("使い方: java jp.co.flm.market.test.TestMemberDAO_memberRegistration ");
			System.exit(1);
		}


		String memberId = args[0];
		String password = args[1];
		String memberName = args[2];
		String gender = args[3];
		String address = args[4];
		String phone = args[5];

		int memberPoint = 0;

		Connection con = null;
		try {

			con = ConnectionManager.getConnection();

			MemberDAO mdao = new MemberDAO(con);

			Member member = new Member();
			member.setMemberId(memberId);
			member.setPassword(password);
			member.setMemberName(memberName);
			member.setGender(gender);
			member.setAddress(address);
			member.setPhone(phone);
			member.setMemberPoint(memberPoint);

			boolean flag = mdao.memberRegistration(member);

			if (flag == true) {

				System.out.println("MemberId：" + member.getMemberId());
				System.out.println("MemberName：" + member.getMemberName());
				System.out.println("Gender：" + member.getGender());
				System.out.println("Address：" + member.getAddress());
				System.out.println("Phone：" + member.getPhone());
				System.out.println("Point：" + member.getMemberPoint());
				System.out.println("member details registered");
			} else {
				System.out.println("member details not registerd");
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