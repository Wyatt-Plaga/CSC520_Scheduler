import java.util.ArrayList;

public class Schedule {

	ArrayList<Class> classes = new ArrayList<Class>();
	ArrayList<Break> breaks = new ArrayList<Break>();
	
	Schedule(ArrayList<Class> classes, ArrayList<Break> breaks) {
		this.classes = classes;
		this.breaks = breaks;
	}

}
