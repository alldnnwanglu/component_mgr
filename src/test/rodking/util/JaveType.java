package rodking.util;

import javax.annotation.Resource;

//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;

import rodking.component_mgr.Component;

@Component
public class JaveType {

	@Resource
	TestManager tManager;

	//CloseableHttpClient httpclient = HttpClients.createDefault();

	public static Boolean valueOf(boolean b) {
		return b ? Boolean.TRUE : Boolean.FALSE;
	}

	public Boolean test(boolean b) {
		/*
		 * HttpPost httppost = new HttpPost("www.baidu.com");
		 * System.out.println("executing request " + httppost.getURI()); try {
		 * CloseableHttpResponse response = httpclient.execute(httppost);
		 * HttpEntity entity = response.getEntity(); if (entity != null) {
		 * System.out.println("--------------------------------------");
		 * System.out.println("Response content: " +
		 * EntityUtils.toString(entity, "UTF-8"));
		 * System.out.println("--------------------------------------"); } }
		 * catch (Exception e) {
		 * 
		 * } finally { } return b;
		 */
		System.out.println("rodking");
		return b;

	}
	
	public void Test()
	{
		tManager.doSomeThing();
	}
}
