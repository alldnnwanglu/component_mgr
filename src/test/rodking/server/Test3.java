package rodking.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Test3 {
	public static void main(String[] args) {

		List<String> awards = new ArrayList<>();
		awards.add("10004_100");
		awards.add("10000_200");

		System.out.println(awards.toArray(new String[0]));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String str = df.format(d);
		System.out.println(str);
		//System.out.println(d.getYear());
		
		Timer timer = new Timer();
		timer.schedule(new TestTask(), 1000);
	}
}

class TestTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
