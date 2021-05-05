
public class HillClimbing {
	
	Scheduler scheduler;
	Schedule currentSchedule;
	
	HillClimbing(Scheduler scheduler) {
		this.scheduler = scheduler;
		currentSchedule = scheduler.generateRandomSchedule();
		System.out.println("Starting schedule with score of " + currentSchedule.getScore());
		for(Class klasse : currentSchedule.classes) {
			System.out.println(klasse.subject + klasse.number);
		}
		for(int i=0; i<1000; i++) {
			currentSchedule = currentSchedule.getBestNeighbor();
			//System.out.println(currentSchedule.classes.size());
		}
	}
	
	Schedule getWinner() {
		return currentSchedule;
	}
	
	void printWinner() {
		System.out.println("The best schedule for you is:");
		System.out.println("With score: " + currentSchedule.getScore());
		System.out.println(currentSchedule.classes.size());
		for(Class klasse : currentSchedule.classes) {
			System.out.println(klasse.subject + klasse.number + " at " + klasse.startTime + "-" + klasse.endTime);
		}
	}

}
