package model;

public class Student {
	
	private static final int[] MAX_FIELD_LENGTH = {10,25,25,15,10}; 
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastNme) {
		this.lastName = lastNme;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String toPaddingString()
	{
		return toPaddingString(" ");
		
	}
	public String toPaddingString(String pPadding)
	{
		StringBuilder builder = new StringBuilder("");
		
		builder.append("|");
		builder.append(paddedValue(String.valueOf(getId()),MAX_FIELD_LENGTH[0],pPadding));
		builder.append("|");
		builder.append(paddedValue(getFirstName(),MAX_FIELD_LENGTH[1],pPadding));
		builder.append("|");
		builder.append(paddedValue(getLastName(),MAX_FIELD_LENGTH[2],pPadding));
		builder.append("|");
		builder.append(paddedValue(getUserName(),MAX_FIELD_LENGTH[3],pPadding));
		builder.append("|");
		
		return builder.toString();
	}
	public String toPaddingHTMLString(String pPadding)
	{
		return toPaddingHTMLString(pPadding, false);
	}
	public String toPaddingHTMLString(String pPadding, boolean pToBeHighlighted)
	{
		return toPaddingHTMLString(pPadding, pToBeHighlighted, "-1");
	}
	public String toPaddingHTMLString(String pPadding, boolean pToBeHighlighted, String pSelectedId)
	{
		StringBuilder builder = new StringBuilder("");
		
		boolean ifRowSelected = getId() == Integer.parseInt(pSelectedId);
		boolean ifGoodGuess = pToBeHighlighted && ifRowSelected;
		
		if (pToBeHighlighted)
		{
			builder.append("<tr bgcolor=\"green\">");
		}
		else
		{
			builder.append("<tr>");
		}
		
		builder.append("<td>");
		builder.append(paddedValue(String.valueOf(getId()),MAX_FIELD_LENGTH[0],pPadding));
		builder.append("</td>");
		builder.append("<td>");
		builder.append(paddedValue(getFirstName(),MAX_FIELD_LENGTH[1],pPadding));
		builder.append("</td>");
		builder.append("<td>");
		builder.append(paddedValue(getLastName(),MAX_FIELD_LENGTH[2],pPadding));
		builder.append("</td>");
		builder.append("<td>");
		builder.append(paddedValue(getUserName(),MAX_FIELD_LENGTH[3],pPadding));
		builder.append("</td>");
		builder.append("<td>");		
		builder.append(String.format("<input type=\"radio\" required = \"required\" name=\"selectStudentId\" value=\"%s\">",getId()));
		builder.append("</td>");
		if (ifRowSelected)
		{
			builder.append("<td>");
			if (ifGoodGuess)
			{
				builder.append("Good Guess !");
			}
			else
			{
				builder.append("Bad Guess !");
			}
			
			builder.append("</td>");
			
		}
		builder.append("</tr>");
		
		return builder.toString();
	}
	
	public static String toHTMLHeaderString(String pPadding)
	{
		StringBuilder builder = new StringBuilder("");
		
		builder.append("<tr>");
		builder.append("<th>");
		builder.append(paddedValue("ID",MAX_FIELD_LENGTH[0],pPadding));
		builder.append("</th>");
		builder.append("<th>");
		builder.append(paddedValue("First Name",MAX_FIELD_LENGTH[1],pPadding));
		builder.append("</th>");
		builder.append("<th>");
		builder.append(paddedValue("Last Name",MAX_FIELD_LENGTH[2],pPadding));
		builder.append("</th>");
		builder.append("<th>");
		builder.append(paddedValue("User Name",MAX_FIELD_LENGTH[3],pPadding));
		builder.append("</th>");
		builder.append("<th>");
		builder.append(paddedValue("Select",MAX_FIELD_LENGTH[4],pPadding));
		builder.append("</th>");
		builder.append("</tr>");

		return builder.toString();
	}
	
	private static String paddedValue(String pRawValue, int pValueLength, String pPadding) 
	{
		String paddedStr = pRawValue;
		
		int strLength = pRawValue.length();
		
		if (strLength > pValueLength)
		{
			return pRawValue.substring(0, pValueLength);
		}
		
		for(int i=0;i< pValueLength - strLength;i++)
		{
			paddedStr += pPadding;
		}
		
		return paddedStr;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastNme=" + lastName + ", userName=" + userName
				+ "]";
	}
	
	
}
