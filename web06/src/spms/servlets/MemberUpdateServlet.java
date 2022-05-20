package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

			request.setAttribute("member", memberDao.selectOne(Integer
					.parseInt(request.getParameter("no"))));
			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");

		} catch (Exception e) {
			throw new ServletException();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			
			Member member = (Member) request.getAttribute("member");
			memberDao.update(member);

			request.setAttribute("viewUrl", "redirect:list.do");

		} catch (Exception e) {
			throw new ServletException();
		}
	}
}
