package org.btm.sampleEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddressBookEx extends HttpServlet
{
		protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
		{
			response.setContentType("text/html;charset=UTF-8");
			try {
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=lovey");
			String fname=request.getParameter("firstname");
			String lname=request.getParameter("lastname");
			String address=request.getParameter("address");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			int zip=Integer.parseInt(request.getParameter("zip"));
			String phoneNo=request.getParameter("phonenum");
			String s2=request.getParameter("s1");
			if(s2.equals("insert")){
			PreparedStatement pstmt = con.prepareStatement("insert into btm.addressbook values(?,?,?,?,?,?,?)");
			pstmt.setString(1,fname);
			pstmt.setString(2,lname);
			pstmt.setString(3,address);
			pstmt.setString(4,city);
			pstmt.setString(5,state);
			pstmt.setInt(6,zip);
			pstmt.setString(7,phoneNo);
			int a=pstmt.executeUpdate();
			if(a>0) {
			out.println("Values Are Inserted");
			} else {
			out.println("Values Are not Inserted");
			}
			} 
			else if(s2.equals("delete"))
			{
				PreparedStatement pst = con.prepareStatement("delete from btm.addressbook where fname =?");
				pst.setString(1,fname);
				pst.executeUpdate();
				out.println("<body><h3>deleted with fname " + fname + "</h3></body></html>");
			} 
			else if(s2.equals("update"))
			{
				//String query = "update btm.addressbook set lname='"+lname+"',address='"+address+"',city='"+city+"',state='"+state+"',zip='"+zip+"',phoneno='"+phoneNo +"' where fname =?";
				String query = "update btm.addressbook set fname='"+fname+"',lname='"+lname+"',address='"+address+"',city='"+city+"',state='"+state+"',zip='"+zip+"',phoneNo='"+phoneNo+"' where fname="+fname;
				Statement stmt = con.createStatement();
				int i = stmt.executeUpdate(query);
				//out.println("query" + query);
				out.println("update successfully");
			}
			out.println("<html>");
			out.println("<head>");
			out.println("<title>UserInfo</title>");
			out.println("</head>");
			out.println("<body>");
			ResultSet rs;
			out.println("<form>");
			out.println("<table border='1' cellspacing='3' cellpadding='2'>");
			out.println("<tr><td> FIRSTNAME </td> <td>LASTNAME</td> <td>ADDRESS</td><td>CITY</td><td>STATE</td><td>ZIP</td><td>PHONENUM</td></tr>");
			PreparedStatement pstmt = con.prepareStatement("select * from btm.addressbook");
			ResultSet rs1 = pstmt.executeQuery();
			while (rs1.next()) {
			out.println("<tr><td>" + rs1.getString(1) + "</td><td>" + rs1.getString(2) + "</td><td>" + rs1.getString(3) + "</td><td>" + rs1.getString(4) + "</td><td>" + rs1.getString(5) + "</td><td>" + rs1.getString(6) + "</td><td>" + rs1.getString(7)  + "</td></tr>");
			}
			out.println("</table>");
			out.println("</form>");
			out.println("<form method='post' action='AddressBookServlet' >");
			out.println("<br><br><br>");
			out.println("<br><br><br>");
			out.println("<br><br>");
			out.println("<form action='AddressBookServlet' method=post>");
			out.println("<br>Enter FirstName : <input type='text' value=''name='search1'/> ");
			out.println("<input type='hidden' value='" + fname + "'name='fname'/> ");
			out.println("<input type='hidden' value='" + zip + "'name='zip'/> ");
			out.println("<br><br>");
			String s3 = request.getParameter("search1");
			out.println("Click Here For Delete: <input type ='submit' value='Delete' name='s1'/>");
			out.println("<br><br>");
			out.println("Click Here For Delete: <input type ='submit' value='Update1' name='s1'/>");
			out.println("<br><br>");
			out.println("Click Here For Search Details :<input type ='submit' value='Search Details' name='s1'/>");
			out.println("</form>");
			if (s2.equals("Delete")) {
			PreparedStatement pst2 = con.prepareStatement("delete from btm.addressbook where fname =?");
			pst2.setString(1, s3);
			pst2.executeUpdate();
			} // out.println("Click Here For Search Details :<input type ='submit' value='Search Details' name='search'/>");
			else if (s2.equals("Search Details")) {
			//out.println("Click Here For Search Details :<input type ='submit' value='Search Details' name='search'/>");
			out.println("<table border='1' cellspacing='3' cellpadding='2'>");
			String s23 = request.getParameter("search1");
			out.println("<tr><td>FIRSTNAME</td> <td>LASTNAME</td> <td>ADDRESS</td><td>CITY</td><td>STATE</td><td>ZIP</td><td>PHONENUM</td></tr>");
			PreparedStatement pst1 = con.prepareStatement("select * from btm.addressbook where fname=? ");
			pst1.setString(1, s23);
			//System.out.println("hello");
			rs = pst1.executeQuery();
			while (rs.next()) {
			out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td><td>" + rs.getString(7) + "</td><td>");
			}
			} else if (s2.equals("Update1")) {
			PreparedStatement pst2 = con.prepareStatement("select * from btm.addressbook where fname =?");
			pst2.setString(1, s3);
			rs1 = pst2.executeQuery();
			if (rs1.next()) {
			out.println(" <form method=post action=AddressBookServlet>");
			out.println("<table bgcolor=skyblue align=center cellspacing=1 cellpadding=0 >");
			out.println(" <tr><td> First name:</td><td><input type=text name=firstname value='" + rs1.getString(1) + "' readonly /><br /></td></tr>");
			out.println("<tr><td> Last name:</td><td> <input type=text name=lastname value='" + rs1.getString(2) + "'/><br /></td></tr>");
			out.println("<tr><td> Address:</td><td> <input type=text name=address value='" + rs1.getString(3) + "'/><br/></td></tr>");
			out.println("<tr><td> City:</td><td> <input type=text name=city value='" + rs1.getString(4) + "'/></br></td></tr>");
			out.println("<tr><td> State: </td><td><input type=text name=state value='" + rs1.getString(5) + "'/><br /></td></tr>");
			out.println(" <tr><td>Zip:</td><td> <input type=text name=zip value='" + rs1.getInt(6) + "'/><br /></td></tr>");
			out.println(" <tr><td>Phone Number:</td><td> <input type=text name=phonenum value='" + rs1.getString(7) + "'/><br /></td></tr>");
			out.println("<tr><td><br></td><td<br></td></tr>");
			out.println("<tr><td></td><td>");
			out.println("<input type=reset value=Reset />");
			out.println(" <input type=Submit value=insert name=s1>");
			out.println("<input type=Submit value=update name=s1>");
			out.println("<input type=Submit value=delete name=s1> </td></tr></table>");
			}
			}
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			} catch (Exception ex) {
			ex.printStackTrace();
			}
			}
			protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
		}
}
