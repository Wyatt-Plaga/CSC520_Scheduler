import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Scheduler {
	
	int desiredClasses;
	ArrayList<Class> desiredClassList = new ArrayList<Class>();
	ArrayList<Class> allClassList = new ArrayList<Class>();
	ArrayList<Break> breaks = new ArrayList<Break>();

	public static void main(String[] args) {
		System.out.println("Starting...");
		Scheduler scheduler = new Scheduler();
		scheduler.rosterIntake();
		scheduler.fileIntake();
	    HillClimbing hillSearch = new HillClimbing(scheduler);
		System.out.println("Hill Search Completed...");
		SimulatedAnnealing aSearch = new SimulatedAnnealing(scheduler);
		System.out.println();
		hillSearch.printWinner();
		System.out.println();
		aSearch.printWinner();
	}
	
	void rosterIntake() {
		try {
			File rosterFile = new File("classRoster.txt");
			Scanner rosterReader = new Scanner(rosterFile);
			while (rosterReader.hasNextLine()) {
				String currentLine = rosterReader.nextLine();
				String[] lineSplit = currentLine.split(" ");
				Class klasse = new Class(this, lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[5], lineSplit[6], lineSplit[7]);
				allClassList.add(klasse);
			}
			rosterReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	void fileIntake() {

		try {
			File inputFile = new File("input.txt");
			Scanner inputReader = new Scanner(inputFile);
			inputReader.nextLine();
			desiredClasses = Integer.parseInt(inputReader.nextLine());
			inputReader.nextLine();
			String currentLine = inputReader.nextLine();
			while (!currentLine.equals("Input any breaks (if any) you would like, along with their importance on a scale of 1-10 (ex. 9:00 AM - 10:00 AM 1):")) {
				String[] lineSplit = currentLine.split(" ");
				Class klasse = new Class(this, lineSplit[0], lineSplit[1], lineSplit[2]);
				desiredClassList.add(klasse);
				currentLine = inputReader.nextLine();
			}
			while(inputReader.hasNextLine()) {
				currentLine = inputReader.nextLine();
				String[] lineSplit = currentLine.split(" ");
				Break pause = new Break(lineSplit[5], lineSplit[0], lineSplit[1], lineSplit[3], lineSplit[4]);
				breaks.add(pause);
			}
			inputReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//mode 0: vanilla, mode 1: guaranteed classes, mode 2: guaranteed breaks, mode 3: random
	Schedule generateRandomSchedule(int mode) {
		Schedule schedule = new Schedule(this);
		Random rand = new Random();
		int random = rand.nextInt(2);
		for(int i=0; i<desiredClasses; i++) {
			boolean scheduleProblem = false;
			boolean matchingDesiredClass = true;
			Class randomClass = allClassList.get(rand.nextInt(2397));
			if((random == 0 && mode == 3) || mode == 1) {
				for(Class klasse : desiredClassList) {
					if(i<desiredClasses && !schedule.hasClass(klasse)) {
						schedule.classes.add(klasse);
						i--;
					}
				}
			}
			if((random == 1 && mode == 3) || mode == 2) {
				scheduleProblem = false;
				for(Break pause : breaks) {
					if(randomClass.takesPlaceBetween(pause.startTime, pause.endTime)) {
						scheduleProblem = true;
					}
				}
			}
			if(matchingDesiredClass && (mode == 1 || mode == 3) && schedule.isTimeDayFree(randomClass.startTime, randomClass.endTime, randomClass.day)) {
				schedule.classes.add(randomClass);
			}else if(!scheduleProblem  && (mode == 1 || mode == 2) && schedule.isTimeDayFree(randomClass.startTime, randomClass.endTime, randomClass.day)) {
				schedule.classes.add(randomClass);
			} else if(mode ==0){
				schedule.classes.add(randomClass);
			} else {
				i--;
			}
		}
		return schedule;
	}

}
