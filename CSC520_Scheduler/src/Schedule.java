import java.util.ArrayList;
import java.util.Random;

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
	
	boolean hasClass(Class klasse) {
		for(Class meineKlasse : classes) {
			if(klasse.matchingClass(meineKlasse)) {
				return true;
			}
		}
		return false;
	}

	Schedule cloneMe() {
		return new Schedule((ArrayList<Class>) classes.clone(), scheduler);
	}

	int getScore() {
		if(classes.size() < scheduler.desiredClasses) {
			return -1000;
		}
		int score = 0;
		for(Class desiredClass : scheduler.desiredClassList) {
			for(Class takenClass : classes) {
				if(takenClass.matchingClass(desiredClass)) {
					score += desiredClass.importance;
				}
			}
		}
		for(Class class1 : classes) {
			for(Class class2 : classes) {
				if(!class1.matchingClass(class2) && class1.startTime == class2.startTime && class1.day.equals(class2.day)) {
					score -= 10;
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
			if(startTime >= klasse.startTime && endTime <= klasse.endTime) {
				return false;
			}
		}
		return true;
	}
	
	boolean isTimeDayFree(double startTime, double endTime, String day) {
		for(Class klasse : classes) {
			if(startTime <= klasse.startTime && endTime >= klasse.startTime && day.equals(klasse.day)) {
				return false;
			}
			if(startTime <= klasse.endTime && endTime >= klasse.endTime && day.equals(klasse.day)) {
				return false;
			}
		}
		return true;
	}

	Schedule getBestNeighbor(int i) {
		Schedule output = this.cloneMe();
		for(Class klasse : scheduler.allClassList) {
			for(Class meineKlasse : classes) {
				Schedule clone = this.cloneMe();
				if(!hasClass(klasse.subject, klasse.number)) {
					clone.classes.remove(meineKlasse);
					clone.classes.add(klasse);
					if(clone.getScore() > output.getScore()) {
						output = clone;
					}
				}
			}
		}
		return output;
	}
	
	Schedule getAnnealedNeighbor(int i, SimulatedAnnealing algorithm) {
		Schedule output = this.cloneMe();
		for(Class klasse : scheduler.desiredClassList) {
			for(Class meineKlasse : classes) {
				Schedule clone = this.cloneMe();
				if(!hasClass(klasse.subject, klasse.number)) {
					clone.classes.remove(meineKlasse);
					clone.classes.add(klasse);
					if(algorithm.pFunction(output.getScore(),clone.getScore(),i)) {
						output = clone;
						return output;
					}
				}
			}
		}
		for(Class klasse : scheduler.allClassList) {
			for(Class meineKlasse : classes) {
				Schedule clone = this.cloneMe();
				if(!hasClass(klasse.subject, klasse.number)) {
					clone.classes.remove(meineKlasse);
					clone.classes.add(klasse);
					if(algorithm.pFunction(output.getScore(),clone.getScore(),i)) {
						output = clone;
						return output;
					}
				}
			}
		}
		return output;
	}

}
