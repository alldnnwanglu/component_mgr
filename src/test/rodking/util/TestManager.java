package rodking.util;

import rodking.component_mgr.annotate.Component;

@Component
public class TestManager extends TestExt implements TestInter{

	public void doSomeThing() {
		System.out.println("rodking");
	}
}
