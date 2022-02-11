package counters;

import test1.LoginTest;

public class flowID_counter extends LoginTest {
	
	public static void counter(int row) throws Exception {
		
		if (xc.getCellDoubleValue(row, 0)==1.0)
			++bpmApproval_count;
		else
			throw new Exception("Flow doesn't exist");
	}

}
