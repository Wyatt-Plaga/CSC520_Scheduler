
public class HillClimbing {
	
	Scheduler scheduler;
	Schedule currentSchedule;
	
	HillClimbing(Scheduler scheduler) {
		this.scheduler = scheduler;
		currentSchedule = scheduler.generateRandomSchedule();
		for(int i=0; i<1000; i++) {
			currentSchedule = currentSchedule.getBestNeighbor();
		}
	}
	
	Schedule getWinner() {
		return currentSchedule;
	}
	
	void printWinner() {
		System.out.println("The best schedule for you is:");
		System.out.println(currentSchedule.classes.size());
		for(Class klasse : currentSchedule.classes) {
			System.out.println(klasse.subject + klasse.number);
		}
	}

}
