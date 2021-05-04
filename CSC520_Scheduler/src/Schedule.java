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
		int score = 0;
		for(Class desiredClass : scheduler.desiredClassList) {
			if(classes.contains(desiredClass)) {
				score += desiredClass.importance;
			}
		}
		for(Break pause : scheduler.breaks) {
			if(isTimeFree(pause.startTime, pause.endTime)) {
				score += pause.importance;
			}
		}
		return score;
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
				if(klasse.number == meineKlasse.number && klasse.subject == meineKlasse.subject) {
					continue;
				} else {
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

}
