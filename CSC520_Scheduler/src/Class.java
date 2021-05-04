
public class Class {
	
	String subject;
	int number;
	int importance;
	
	Class(String subject, String numString, String numImport) {
		this.subject = subject;
		this.number = Integer.parseInt(numString);
		this.importance = Integer.parseInt(numImport);
		System.out.println(subject);
		System.out.println(number);
		System.out.println(importance);
	}
}
