import java.util.ArrayList;

public class Schedule {

	ArrayList<Class> classes = new ArrayList<Class>();
	Scheduler scheduler;

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
		if(classes.size() < scheduler.desiredClasses) {
			return -10;
		}
		int score = 0;
		for(Class desiredClass : scheduler.desiredClassList) {
			for(Class takenClass : classes) {
				if(takenClass.matchingClass(desiredClass)) {
					score += desiredClass.importance;
				}
			}
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

	Schedule getBestNeighbor(int i) {
		//Schedule output = new Schedule(scheduler);
		Schedule output = this.cloneMe();
		for(Class klasse : scheduler.allClassList) {
			for(Class meineKlasse : classes) {
				Schedule clone = this.cloneMe();
				if(!hasClass(klasse.subject, klasse.number)) {
					clone.classes.remove(meineKlasse);
					clone.classes.add(klasse);
					if(clone.getScore() > output.getScore()) {
						//System.out.println(i);
						output = clone;
					}
				}
			}
		}
		return output;
	}

}
