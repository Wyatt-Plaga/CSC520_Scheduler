import java.util.ArrayList;

public class Schedule {

	ArrayList<Class> classes = new ArrayList<Class>();
	Scheduler scheduler;
	int counter = 0;

	Schedule(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	Schedule(ArrayList<Class> classes, Scheduler scheduler) {
		this.classes = classes;
		this.scheduler = scheduler;
	}

	Schedule cloneMe() {
		return new Schedule((ArrayList<Class>) classes.clone(), scheduler);
	}

	int getScore() {
		if(classes.size() == 0 && counter < 10) {
			//System.out.println("No class size");
			counter++;
			return 0;
		}
		int score = 0;
		for(Class desiredClass : scheduler.desiredClassList) {
			for(Class takenClass : classes) {
				if(takenClass.matchingClass(desiredClass)) {
					score += desiredClass.importance;
				}
			}
		}
		if(counter < 10 || true) {
			//System.out.println("score from classes is " + score);
		}
		for(Break pause : scheduler.breaks) {
			if(isTimeFree(pause.startTime, pause.endTime)) {
				score += pause.importance;
			}
		}
		return score;
	}

	boolean hasClass(String subject, int number) {
		for(Class klasse: classes) {
			if(klasse.number == number && klasse.subject.equals(subject)) {
				//System.out.println("comparing " + klasse.subject + klasse.number + " to " + subject + number);

				return true;
			}
		}
		return false;
	}

	boolean isTimeFree(double startTime, double endTime) {
		for(Class klasse : classes) {
			if(startTime <= klasse.startTime && endTime >= klasse.startTime) {
				return false;
			}
			if(startTime <= klasse.endTime && endTime >= klasse.endTime) {
				return false;
			}
		}
		return true;
	}

	Schedule getBestNeighbor() {
		Schedule output = new Schedule(scheduler);
		for(Class klasse : scheduler.allClassList) {
			for(Class meineKlasse : classes) {
				Schedule clone = this.cloneMe();
				//System.out.println(clone.classes.size());
				if(scheduler.desiredClassList.contains(klasse)) {
					System.out.println("desired class");
				}
				if(!hasClass(klasse.subject, klasse.number)) {
					clone.classes.remove(meineKlasse);
					clone.classes.add(klasse);
					//System.out.println(clone.classes.size());
					//System.out.println(output.getScore());
					if(clone.getScore() > output.getScore()) {

						output = clone;
					}
				}
			}
		}
		return output;
	}

}
