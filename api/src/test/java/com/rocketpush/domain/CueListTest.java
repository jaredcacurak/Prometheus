package com.rocketpush.domain;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collection;

public class CueListTest {
	CueList listOfCues;
	
	@Before
	public void setUp() {			
		this.listOfCues = new CueList();
	}
	
	@After
	public void tearDown() {	
		this.listOfCues = null;
	}
	
	@Test
	public void list_of_cues_is_empty() {
		assertThat(this.listOfCues.size(), is(0));
	}

	@Test
	public void add_one_cue_to_the_list_of_cues() {
		Cue cue = new Cue(1);
		
		this.listOfCues.addCue(cue);
		
		assertThat(this.listOfCues.size(), is(1));
	}
	
	@Test
	public void add_two_cues_to_the_list_of_cues() {
		Cue cue1 = new Cue(1);
		Cue cue2 = new Cue(2);
		
		this.listOfCues.addCue(cue1);
		this.listOfCues.addCue(cue2);
		
		assertThat(this.listOfCues.size(), is(2));
	}
	
	@Test
	public void list_of_cue_stream_value_is_1_when_it_has_cue_one() {
		int[] expected = { 0x01 };
		Cue cue = new Cue(1);
		
		this.listOfCues.addCue(cue);
		
		assertThat(this.listOfCues.toArray(), is(expected));
	}
	
	@Test
	public void list_of_cue_stream_value_is_0x20_when_it_has_cue_32() {
		int[] expected = { 0x20 };
		Cue cue = new Cue(32);		
		
		this.listOfCues.addCue(cue);		
		
		assertThat(this.listOfCues.toArray(), is(expected));
	}
	
	@Test
	public void list_of_cue_stream_value_is_4_3_2_1_when_it_has_cue_1_2_3_and_4() {
		int[] expected = { 0x4, 0x3, 0x2, 0x1 };
		Cue cue1 = new Cue(1);
		Cue cue2 = new Cue(2);
		Cue cue3 = new Cue(3);
		Cue cue4 = new Cue(4);
		
		this.listOfCues.addCue(cue1);
		this.listOfCues.addCue(cue2);
		this.listOfCues.addCue(cue3);
		this.listOfCues.addCue(cue4);
		
		assertThat(this.listOfCues.toArray(), is(expected));
	}
	
	@Test
	public void fire_all_thrity_two_cues() {
		int[] expected = new int[32];		
		for(int i = 1; i <= 32; i++) {
			this.listOfCues.addCue(new Cue(i));
			expected[i-1] = 33 - i;
		}
		
		assertThat(this.listOfCues.toArray(), is(expected));
	}
	
	@Test
	public void add_more_than_one_cue_to_the_list_of_cues() {
		int[] expected = { 0x4, 0x3, 0x2, 0x1 };
		Collection<Cue> cues = new ArrayList<Cue>();
		cues.add(new Cue(1));
		cues.add(new Cue(2));
		cues.add(new Cue(3));
		cues.add(new Cue(4));
		
		this.listOfCues.addCues(cues);
		
		assertThat(this.listOfCues.size(), is(4));
		assertThat(this.listOfCues.toArray(), is(expected));
	}
	
	@Test
	public void add_more_than_one_cue_to_the_list_of_cues_again() {
		int[] expected = { 0x20, 0x12, 0x03 };
		Collection<Cue> cues = new ArrayList<Cue>();
		cues.add(new Cue(32));
		cues.add(new Cue(3));
		cues.add(new Cue(18));		
		
		this.listOfCues.addCues(cues);
		
		assertThat(this.listOfCues.size(), is(3));
		assertThat(this.listOfCues.toArray(), is(expected));
	}
}