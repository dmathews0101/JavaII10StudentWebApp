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
 * Servlet implementation class RandomHighLightedListServlet
 */
@WebServlet("/RandomHighLightedListServlet")
public class RandomHighLightedListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandomHighLightedListServlet() {
        super();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
//		response.setIntHeader("Refresh", 3);
		
		out.println(StudentRepository.getStudentsListAsHTML(false));
		
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String selectStudentId = request.getParameter("selectStudentId");
		
		if ((null != selectStudentId) && selectStudentId.trim().length() > 0)
		{
			out.println(String.format("Student with Id %s was selected !",selectStudentId));
		}
		else
		{
			out.println("No selection !");
		}
		out.println("<form method=\"get\" action=\"RandomHighLightedListServlet\">");
		out.println("<button type=\"submit\" name=\"submit\">Return</button>");
		out.println("</form>");
		
		out.close();
	}	
}
