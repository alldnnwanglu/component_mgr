package rodking.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Test {
	static final String Path = "E:/javaPorject/test-server-project/SQL.txt";
	/**
	 * 工具类将 mysql 美化的sql 转换为一行显示
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader is = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Path))));
		StringBuilder sb = new StringBuilder();
		String str = is.readLine();
		while(str!=null)
		{
			sb.append(str);
			str = is.readLine();
		}
		
		str = sb.toString();
		str=str.replace("\n", " ");
		str=str.replaceAll("\t+", " ");
		str=str.replace("{ ", "{");
		str=str.replace(" }", "}");
		is.close();
		System.out.println(str);
	}
}
