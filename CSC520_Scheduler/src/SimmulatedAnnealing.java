import java.util.ArrayList;
import java.util.Random;

public class SimmulatedAnnealing {
	
	Scheduler scheduler;
	Schedule currentSchedule;
	ArrayList<Schedule> bestCandidates = new ArrayList<Schedule>();
	double numSteps = 100;

	SimmulatedAnnealing(Scheduler scheduler) {
		this.scheduler = scheduler;
		for(int j=0; j<50; j++) {
			currentSchedule = scheduler.generateRandomSchedule(0);
			for(int i=0; i<numSteps; i++) {
				currentSchedule = currentSchedule.getAnnealedNeighbor(i, this);
				//System.out.println(currentSchedule.getScore());
			}
			System.out.println(currentSchedule.getScore());
			bestCandidates.add(currentSchedule);
		}
		for(Schedule schedule : bestCandidates) {
			if(schedule.getScore() > currentSchedule.getScore()) {
				currentSchedule = schedule;
			}
		}
	}
	
	boolean pFunction(int origionalScore, int newScore, int run) {
		if(newScore > origionalScore) {
			return true;
		} else {
			Random rand = new Random();
			double functionOut = (1/(1+Math.exp(((origionalScore-newScore)/getTemperature(run)))));
			if(functionOut * 100 != 50) {
				//System.out.println(functionOut * 10);
			}
			if((rand.nextInt(99) + 1) < (functionOut * 100)) {
				return true;
			}
		}
		return false;		
	}
	
	float getTemperature(float run) {
		//return (float) ((1-(run+1)/numSteps)*100);
		return (float) (100 * Math.pow(0.95,run));
		//return (float) (100/run);
	}
	
	Schedule getWinner() {
		return currentSchedule;
	}
	
	void printWinner() {
		System.out.println("The best schedule for you by annealing is:");
		for(Class klasse : currentSchedule.classes) {
			System.out.println(klasse.subject + klasse.number + " at " + klasse.startTime + "-" + klasse.endTime + " on " + klasse.day);
		}
		System.out.println("With score: " + currentSchedule.getScore());
	}

}
