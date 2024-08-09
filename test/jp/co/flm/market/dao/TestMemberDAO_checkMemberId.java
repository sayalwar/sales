/**
 * @author Prameshiwari
 * It will be used for registration screen
 *
 */
package jp.co.flm.market.dao;

import java.sql.Connection;



public class TestMemberDAO_checkMemberId {
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("使い方: java jp.co.flm.market.test.TestMemberDAO_MemberId ");
			System.exit(1);
		}


		String email = args[0];


			Connection con = null;
		try {

			con = ConnectionManager.getConnection();


			MemberDAO mdao = new MemberDAO(con);

			boolean flag = mdao.checkMemberId(email);
			if(flag != true) {
				System.out.println("email address is already registered");
			}else {
				System.out.println("email address is not registered");
			}

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