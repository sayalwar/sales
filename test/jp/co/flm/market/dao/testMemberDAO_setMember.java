/**
 * @author Pragati
 * It will be used for product purchase screen
 *
 */
package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.flm.market.entity.Member;
public class testMemberDAO_setMember {

	public static void main(String[] args) throws SQLException {
		if (args.length != 3) {
			System.out.println("使い方: java jp.co.flm.market.test.TestMemberDAO_setMember <会員ID>");
			System.exit(1);
		}

		        String memberId=args[0];
				String memberPoint = args[1];
				String totalPoint = args[2];

                int memPts=Integer.parseInt(memberPoint);
		        int ttalPoint=Integer.parseInt(totalPoint);

				Connection con = null;
				try {
					con = ConnectionManager.getConnection();
					MemberDAO mdao = new MemberDAO(con);
					Member member = new Member();
					member.setMemberId(memberId);
					member.setMemberPoint(memPts);

					boolean flag=mdao.setMember(member, ttalPoint);
					if(flag==true) {
						System.out.println("ポイント：" + member.getMemberPoint());
						System.out.println("successfully updated");
					}
					else {
						System.out.println("point has not been updated");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					// データベース接続を閉じる。
					if(con != null){
						con.close();
						}
				}
		}
}
