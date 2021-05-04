
public class Class {
	
	Scheduler scheduler;
	String subject;
	int number;
	int importance;
	double startTime;
	double endTime;
	String day;
	
	Class(Scheduler scheduler, String subject, String numString, String numImport) {
		this.scheduler = scheduler;
		this.subject = subject;
		this.number = Integer.parseInt(numString);
		this.importance = Integer.parseInt(numImport);
		for(Class klasse : scheduler.allClassList) {
			if(klasse.subject.equals(subject) && klasse.number == number) {
				klasse.startTime = startTime;
				klasse.endTime = endTime;
				klasse.day = day;
			}
		}
	}
	
	Class(Scheduler scheduler, String subject, String numString, String startTimeString, String startMeridian, String endTimeString, String endMeridian, String day) {
		this.scheduler = scheduler;
		this.subject = subject;
		this.number = Integer.parseInt(numString);
		this.day = day;
		String[] startSplit = startTimeString.split(":");
		double startHour = Integer.parseInt(startSplit[0]);
		double startMinutes = ((double) Integer.parseInt(startSplit[1]))/60;
		startTime = startHour + startMinutes;
		if(startMeridian.equals("PM")) {
			startTime += 12;
		}
		String[] endSplit = endTimeString.split(":");
		double endHour = Integer.parseInt(endSplit[0]);
		double endMinutes = ((double) Integer.parseInt(endSplit[1]))/60;
		endTime = endHour + endMinutes;
		if(endMeridian.equals("PM")) {
			endTime += 12;
		}
	}
}
