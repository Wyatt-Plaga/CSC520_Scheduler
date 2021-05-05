import java.util.ArrayList;

public class HillClimbing {
	
	Scheduler scheduler;
	Schedule currentSchedule;
	ArrayList<Schedule> bestCandidates = new ArrayList<Schedule>();
	
	HillClimbing(Scheduler scheduler) {
		this.scheduler = scheduler;
		for(int j=0; j<500; j++) {
			currentSchedule = scheduler.generateRandomSchedule();
			for(int i=0; i<5; i++) {
				currentSchedule = currentSchedule.getBestNeighbor(i);
			}
			bestCandidates.add(currentSchedule);
		}
		for(Schedule schedule : bestCandidates) {
			if(schedule.getScore() > currentSchedule.getScore()) {
				currentSchedule = schedule;
			}
		}
	}
	
	Schedule getWinner() {
		return currentSchedule;
	}
	
	void printWinner() {
		System.out.println("The best schedule for you is:");
		for(Class klasse : currentSchedule.classes) {
			System.out.println(klasse.subject + klasse.number + " at " + klasse.startTime + "-" + klasse.endTime);
		}
		System.out.println("With score: " + currentSchedule.getScore());
	}

}
