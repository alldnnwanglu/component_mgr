package rodking.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test2 {
	public static void main(String[] args) throws ParseException {
		/*Calendar c = Calendar.getInstance();
		c.set(2015, 10, 20, 0, 0, 0);
		
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int max = 3;
		for (int i = 0; i < max; i++) {
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
			sb.append("(SELECT \""+df.format(c.getTime())+"\" as t_time )");
			if(i<max-1)
				sb.append(" UNION ");
		}
		System.out.println(sb.toString());*/
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		StringBuilder strB = new StringBuilder();
		Date d = df.parse("2015-10-21");
		c.setTime(d);
		System.out.println(d);
		Calendar c2 = Calendar.getInstance();
		Date d2 = df.parse("2015-10-20");
		c2.setTime(d2);
		System.out.println((c2.getTimeInMillis()-c.getTimeInMillis())/(1000 * 60 * 60 * 24));
		
		
		Calendar starC = Calendar.getInstance();
		Calendar endC = Calendar.getInstance();
		starC.setTime(df.parse("2016-06-10"));
		endC.setTime(df.parse("2016-06-18"));
		int dx = (int) ((endC.getTimeInMillis() - starC.getTimeInMillis()) / (1000 * 60 * 60 * 24));

		strB.append("(SELECT \"" + df.format(starC.getTime()) + "\" as t_time )");
		for (int i = 0; i < dx; i++) {
			if (i < dx)
				strB.append(" UNION ");
			starC.add(Calendar.DAY_OF_YEAR, 1);
			strB.append("(SELECT \"" + df.format(starC.getTime()) + "\" as t_time )");
		}
		
		System.out.println(strB.toString());
	}
}
