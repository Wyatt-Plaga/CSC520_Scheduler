
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
				startTime = klasse.startTime;
				endTime = klasse.endTime;
				day = klasse.day;
			}
		}
	}
	boolean takesPlaceBetween(double startTime, double endTime) {
			if(startTime <= this.startTime && endTime >= this.startTime) {
				return true;
			}
			if(startTime <= this.endTime && endTime >= this.endTime) {
				return true;
			}
		return false;
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
		if(endMeridian.equals("PM") && startHour != 12) {
			endTime += 12;
		}
	}
	
	boolean matchingClass(Class klasse) {
		if(subject.equals(klasse.subject) && number == klasse.number) {
			return true;
		} else {
			return false;
		}
	}
}
