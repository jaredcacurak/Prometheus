package com.rocketpush;

import com.rocketpush.domain.Cue;
import com.rocketpush.domain.CueList;
import com.rocketpush.domain.Slave;
import com.rocketpush.domain.Time;

public interface Commandable {

	void reset(Slave slave);

	void fireCue(Slave slave, CueList cues);

	void testCue(Slave slave, Cue cue);

	void testSlaveComm(Slave slave);
	
	void requestSlaveVersion(Slave slave);
	
	void requestFireTime(Slave slave);

	void disarmSlave(Slave slave);
	
	void armSlave(Slave slave);

	void startShow();

	void endShow();

	void runSync();
	
	void requestSlaveStatus(Slave slave);
	
	void setCueFireTime(Slave slave, Time time);
}
