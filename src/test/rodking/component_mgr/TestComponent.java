package rodking.component_mgr;

import rodking.component_mgr.ApplicationContext;
import rodking.util.JaveType;
import rodking.util.TestExt;
import rodking.util.TestInter;

public class TestComponent {
	public static void main(String[] args) throws Exception {
		ApplicationContext.getInstance().init();

		JaveType t = (JaveType) ApplicationContext.getInstance().getBeanOfType(JaveType.class);
		t.Test();

		// 测试通过 Class 来获得所有继承这个没的 实力对象
		ApplicationContext.getInstance().getBeansOfType(TestInter.class);
		
		// 测试通过 Annotation 来获得所有继承这个没的 实力对象
		ApplicationContext.getInstance().getBeansWithAnnotation(Component.class);
	}
}
