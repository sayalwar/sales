/**
 * @author Nilesh
 * It will be used for Shopping cart screen, member inquiry screen.
 *
 */

package jp.co.flm.market.dao;

import java.sql.Connection;

import jp.co.flm.market.entity.Member;

public class TestMemberDAO_getMember {
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			System.out.println("使い方: java jp.co.flm.market.test.TestMemberDAO01 <memberID> <password>");
			System.exit(1);
		}

		String memberId = args[0];
		String password = args[1];

		Connection con = null;
		try {
			con = ConnectionManager.getConnection();

			MemberDAO mdao = new MemberDAO(con);

			Member member = mdao.getMember(memberId, password);
			System.out.println("Member ID：" + member.getMemberId());
			System.out.println("Member Name：" + member.getMemberName());
			System.out.println("Gender：" + member.getGender());
			System.out.println("Address：" + member.getAddress());
			System.out.println("Phone number：" + member.getPhone());
			System.out.println("Points：" + member.getMemberPoint());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if(con != null){
				con.close();
			}
		}
	}
}