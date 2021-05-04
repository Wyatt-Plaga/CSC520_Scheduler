import java.util.ArrayList;

public class Schedule {

	ArrayList<Class> classes = new ArrayList<Class>();
	Scheduler scheduler;
	
	Schedule(ArrayList<Class> classes, Scheduler scheduler) {
		this.classes = classes;
		this.scheduler = scheduler;
	}
	
	int getScore() {
		int score = 0;
		for(Class desiredClass : scheduler.desiredClassList) {
			if(classes.contains(desiredClass)) {
				score += desiredClass.importance;
			}
		}
//		for(Break desiredBreak : scheduler.desiredClassList) {
//			if(classes.contains(desiredClass)) {
//				score += desiredClass.importance;
//			}
//		}
		return 0;
	}

}
