package rodking.util;

import rodking.component_mgr.Component;

@Component("rodking")
public class JaveType {

	public static Boolean valueOf(boolean b) {
		return b ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public Boolean test(boolean b)
	{
		return b;
	}
}
