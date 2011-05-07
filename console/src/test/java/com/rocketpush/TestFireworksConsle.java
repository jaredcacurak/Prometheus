package com.rocketpush;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

import com.rocketpush.communication.FakeCommunication;

public class TestFireworksConsle {
	
	FakeCommunication comm = null;
	Launcher launcher = null;
	FireworksConsole console = null;
	
	@Before
	public void init() {
		comm = new FakeCommunication();
		launcher = new Launcher(comm);		
		console = new FireworksConsole(launcher);
	}
	
	@After
	public void teardown() {
		comm = null;
		launcher = null;
		console = null;
	}

	@Test
	public void reset_slave_one() {		
		console.evalute("reset:1");
		
		int[] expected = { 0x81, 0x7E, 0x01, 0xFE };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void simple_fire_cue() {
		console.evalute("fire cue:2:0:0:0:0:1");
		
		int[] expected = { 0x82, 0x7D, 0x02, 0xFD, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x01, 0xFE, 0x00, 0xFF };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void fire_cue_incrementing_time() {
		console.evalute("fire cue:2:1:2:3:4:1");
		
		int[] expected = { 0x82, 0x7D, 0x02, 0xFD, 0x01, 0xFE, 0x02, 0xFD, 0x03, 0xFC, 0x04, 0xFB, 0x01, 0xFE, 0x00, 0xFF };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void fire_more_than_one_cue() {
		console.evalute("fire cue:2:1:2:3:4:2:1:3");
		
		int[] expected = { 0x82, 0x7D, 0x02, 0xFD, 0x01, 0xFE, 0x02, 0xFD, 0x03, 0xFC, 0x04, 0xFB, 0x03, 0xFC, 0x02, 0xFD, 0x01, 0xFE, 0x00, 0xFF };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void test_cue() {
		console.evalute("test cue:3:9");
		
		int[] expected = { 0x83, 0x7C, 0x03, 0xFC, 0x09, 0xF6 };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void test_slave_comm() {
		console.evalute("test slave comm:2");
		
		int[] expected = { 0x84, 0x7B, 0x02, 0xFD };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void request_slave_version() {
		console.evalute("request slave version:15");
		
		int[] expected = { 0x85, 0x7A, 0x0F, 0xF0 };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void set_cue_fire_time() {
		console.evalute("set cue fire time:3:50");
		
		int[] expected = { 0x86, 0x79, 0x03, 0xFC, 0x32, 0xCD };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void reqest_fire_time() {
		console.evalute("request fire time:16");
		
		int[] expected = { 0x87, 0x78, 0x10, 0xEF };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void disarm_slave() {
		console.evalute("disarm slave:1");
		
		int[] expected = { 0x88, 0x77, 0x01, 0xFE };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void arm_slave() {
		console.evalute("arm slave:8");
		
		int[] expected = { 0x89, 0x76, 0x08, 0x0F7 };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void request_slave_status() {
		console.evalute("request slave status:14");
		
		int[] expected = { 0x8A, 0x75, 0x0E, 0xF1 };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void request_battery_level() {
		console.evalute("request battery level:15");
		
		int[] expected = { 0x8B, 0x74, 0x0F, 0xF0 };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void start_show() {
		console.evalute("start show:0:0:0:0");
		
		int[] expected = { 0x8C, 0x73, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void end_show() {
		console.evalute("end show:0:0:0:0");
		
		int[] expected = { 0x8D, 0x72, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF };
		assertThat(comm.getLastCommand(), is(expected));
	}
	
	@Test
	public void run_sync() {
		console.evalute("sync:0:0:0:0");
		
		int[] expected = { 0x8E, 0x71, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x00, 0xFF };
		assertThat(comm.getLastCommand(), is(expected));
	}
}