
public class Break {
	
	int importance;
	double startTime;
	double endTime;
	
	Break(String importance, String startTimeString, String startMeridian, String endTimeString, String endMeridian) {
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
		this.importance = Integer.parseInt(importance);
	}

}
