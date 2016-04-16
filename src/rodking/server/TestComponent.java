package rodking.server;

import java.io.IOException;

import rodking.component_mgr.ClassPathBeanDefinitionScanner;

public class TestComponent {
	public static void main(String[] args) throws IOException {
		ClassPathBeanDefinitionScanner t = new ClassPathBeanDefinitionScanner();
		t.doScan("util/**/*.class");
	}
}
