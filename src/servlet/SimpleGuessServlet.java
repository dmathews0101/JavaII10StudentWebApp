package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.StudentRepository;

/**
 * Servlet implementation class SimpleGuessServlet
 */
@WebServlet("/SimpleGuessServlet")
public class SimpleGuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleGuessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String selectStudentId = request.getParameter("selectStudentId");
//		response.setIntHeader("Refresh", 3);
		
		out.println(StudentRepository.getStudentsListAsHTML(true,selectStudentId));
		
		out.close();
	}

}
