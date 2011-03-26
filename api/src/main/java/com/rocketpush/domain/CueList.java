package com.rocketpush.domain;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class CueList {
	private final SortedSet<Cue> listOfCues;

	public CueList() {
		this.listOfCues = new ConcurrentSkipListSet<Cue>(new Comparator<Cue>() {

			@Override
			public int compare(Cue o1, Cue o2) {
				return o2.getId() - o1.getId();
			}
		});
	}

	public void addCue(Cue cue) {
		this.listOfCues.add(cue);
	}

	public void addCues(Collection<Cue> cues) {
		this.listOfCues.addAll(cues);
	}

	public int size() {
		return this.listOfCues.size();
	}

	public int[] toArray() {
		int[] values = new int[this.listOfCues.size()];
		int i = 0;
		for (Cue cue : this.listOfCues) {
			values[i] = cue.getId();
			i += 1;
		}

		return values;
	}
}
