package rodking.component_mgr;

import rodking.component_mgr.ApplicationContext;
import rodking.util.JaveType;

public class TestComponent {
	public static void main(String[] args) throws Exception {
		ApplicationContext.getInstance().init();

		JaveType t = (JaveType) ApplicationContext.getInstance().getBeanByName("JaveType").getBeanClass();
		t.Test();
	}
}
