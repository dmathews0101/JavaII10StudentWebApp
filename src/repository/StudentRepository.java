package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import model.Student;

public class StudentRepository {
	
	private static final String FILE_TUDENTS = "c:/dev/Students.txt";
	private static final String FILE_OUTPUT_NAME = "C:\\dev\\tools\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ROOT\\ex01\\students.html";

	public static String getStudentsListAsHTML() throws IOException
	{
		return getStudentsListAsHTML(false);
	}
	public static String getStudentsListAsHTML(boolean pHighlighted) throws IOException
	{
		return getStudentsListAsHTML(pHighlighted,"-1");
	}
	public static String getStudentsListAsHTML(boolean pHighlighted, String pSelectedId) throws IOException
	{
		List<Student> students = readFile(FILE_TUDENTS);
		
		int randomIndex = pHighlighted ? new Random().nextInt(students.size()):-1;
		
		return getStudentsToHTMLTable(students,randomIndex,pSelectedId);
	}
	
	private static List<Student> readFile(String pFileName) throws IOException
	{
		List<Student> students = new ArrayList<Student>();
		
		BufferedReader bufferR = new BufferedReader(new FileReader(pFileName));
		
		String currentLine = bufferR.readLine();
		Student studentToAdd;
		
		while (null != currentLine)
		{
			
			studentToAdd = new Student();
			buildStudentFromLine(studentToAdd,currentLine);students.add(studentToAdd);
			 
			 currentLine = bufferR.readLine();
		}
		
		bufferR.close();
		
		return students;
		
	}
	
	private static void buildStudentFromLine(Student studentToAdd, String currentLine) {
		StringTokenizer tokenizer = new StringTokenizer(currentLine, ",");
		
		String firstName = tokenizer.nextToken();
		studentToAdd.setFirstName(firstName);
		
		String remainingStr = tokenizer.nextToken();
		
		tokenizer = new StringTokenizer(remainingStr, "(");
		
		String lastName = tokenizer.nextToken().trim();
		studentToAdd.setLastName(lastName);
		
		remainingStr = tokenizer.nextToken().replace(")","");
		tokenizer = new StringTokenizer(remainingStr, " ");
		
		String idStr = tokenizer.nextToken().trim();
		studentToAdd.setId(Integer.parseInt(idStr));
		
		String userName = tokenizer.nextToken().trim();
		studentToAdd.setUserName(userName);
	}
	
	private static void displayStudentsTable(List<Student> pStudents)
	{
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("| ID       | First Name              | Last Name               | User Name     |");
		System.out.println("-------------------------------------------------------------------------------");
		
		for(Student student:pStudents)
		{
			System.out.println(student.toPaddingString());

			System.out.println("-------------------------------------------------------------------------------");
		}		
	}
//	Generate html File with Students list : Students.html (header, body...)
	private static String getStudentsToHTMLTable(List<Student> pStudents) throws IOException
	{
		return getStudentsToHTMLTable(pStudents, -1);
	}
	private static String getStudentsToHTMLTable(List<Student> pStudents,int pIndexToHighLight) throws IOException
	{
		return getStudentsToHTMLTable(pStudents, pIndexToHighLight, "-1");
	}
	private static String getStudentsToHTMLTable(List<Student> pStudents,int pIndexToHighLight, String pSelectedId) throws IOException
	{
		StringWriter strWriter = new StringWriter();
		BufferedWriter bufferW = new BufferedWriter(strWriter);
		
		bufferW.write("<html>");
		bufferW.newLine();
		
		String content = Student.toHTMLHeaderString("&nbsp;");
		bufferW.write("<body>");
		bufferW.newLine();
		bufferW.append("<form method=\"post\" action=\"SimpleGuessServlet\">");
		bufferW.newLine();
		bufferW.write("<table>");
		bufferW.newLine();
		bufferW.write(content);
		bufferW.newLine();
		int index = 0;
		for(Student student:pStudents)
		{
			bufferW.write(student.toPaddingHTMLString("&nbsp;", (index == pIndexToHighLight),pSelectedId));
			bufferW.newLine();
			index++;
		}
		bufferW.write("</table>");
		bufferW.newLine();
		bufferW.append("<button type=\"submit\" name=\"submit\">Guess!</button>");
		bufferW.newLine();
		bufferW.write("</form>");
		bufferW.newLine();
		bufferW.write("</body>");
		bufferW.newLine();
		bufferW.write("</html>");
		bufferW.newLine();
		
		bufferW.close();
		
		return strWriter.toString();
	}	
	

}
