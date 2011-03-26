package com.rocketpush;

import com.rocketpush.communication.Communication;
import com.rocketpush.communication.SerialCommunication;
import com.rocketpush.domain.Cue;
import com.rocketpush.domain.CueList;
import com.rocketpush.domain.Slave;
import com.rocketpush.domain.Time;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class FireworksConsole
{
  private Communication comm;
  private Launcher launcher;

  public static void main(String[] args)
  {
    FireworksConsole console = new FireworksConsole();
    try
    {
      console.comm = new SerialCommunication(args[0]);
      console.launcher = new Launcher(console.comm);

      System.out.println("Fire away! v.0.1.20110104");
      if (args.length == 1) console.interactiveMode();
      else if ((args.length == 2) || (args.length == 3)) console.runScript(args); 
    }
    catch (Exception e) {
      System.out.println("Bad mojo: " + e);
    } finally {
      console.comm.close();
    }
  }

  private void runScript(String[] args) throws FileNotFoundException, InterruptedException
  {
    String script = args[1];
    int sleepTime = Integer.valueOf(args.length == 3 ? Integer.valueOf(args[2]).intValue() : 20).intValue();
    System.out.println("Running script: " + script + ", with a sleep time of: " + sleepTime + "ms");
    File file = new File(script);
    Scanner in = new Scanner(file);

    while (in.hasNextLine()) {
      evalute(in.nextLine());
      Thread.sleep(sleepTime);
    }
    in.close();
  }

  private void interactiveMode() {
    Scanner in = new Scanner(System.in);
    String input = "";

    while (!input.equals("exit")) {
      input = in.nextLine();
      evalute(input);
    }
    in.close();
  }

  private void evalute(String value) {
    String regex = ":";
    if (value.equals("sync")) {
      this.launcher.runSync();
    } else if (value.equals("start show")) {
      this.launcher.startShow();
    } else if (value.equals("end show")) {
      this.launcher.endShow();
    } else if (value.startsWith("fire cue")) {
      String[] values = value.split(regex);
      this.launcher.fireCue(slaveFor(values[1]), cueListFor(values));
    } else if (value.startsWith("test cue")) {
      String[] values = value.split(regex);
      this.launcher.testCue(slaveFor(values[1]), cueFor(values[2]));
    } else if (value.startsWith("reset")) {
      String[] values = value.split(regex);
      this.launcher.reset(slaveFor(values[1]));
    } else if (value.startsWith("arm slave")) {
      String[] values = value.split(regex);
      this.launcher.armSlave(slaveFor(values[1]));
    } else if (value.startsWith("disarm slave")) {
      String[] values = value.split(regex);
      this.launcher.disarmSlave(slaveFor(values[1]));
    } else if (value.startsWith("request fire time")) {
      String[] values = value.split(regex);
      this.launcher.requestFireTime(slaveFor(values[1]));
    } else if (value.startsWith("request slave status")) {
      String[] values = value.split(regex);
      this.launcher.requestSlaveStatus(slaveFor(values[1]));
    } else if (value.startsWith("request slave version")) {
      String[] values = value.split(regex);
      this.launcher.requestSlaveVersion(slaveFor(values[1]));
    } else if (value.startsWith("set cue fire time")) {
      String[] values = value.split(regex);
      this.launcher.setCueFireTime(slaveFor(values[1]), timeFor(values[2]));
    } else if (value.startsWith("test slave comm")) {
      String[] values = value.split(regex);
      this.launcher.testSlaveComm(slaveFor(values[1]));
    } else if (!value.equals("exit"))
    {
      if (value.equals("help")) {
        System.out.println("Here is a list of commands:\nsync\nstart show\nend show\nfire cue : slave : cue\ntest cue : slave : cue\nreset : slave\narm slave : slave\ndisarm slave : slave\nrequest fire time : slave\nrequest slave status : slave\nrequest slave version : slave\nset cue fire time : slave : time\ntest slave comm : slave\nexit\nhelp");
      }
      else
      {
        System.out.println("Command not found: " + value + ".  Try help");
      }
    }
  }

  private CueList cueListFor(String[] values) {
    CueList cues = new CueList();
    for (int i = 2; i < values.length; i++) {
      int arg = Integer.valueOf(values[i].trim()).intValue();
      cues.addCue(new Cue(arg));
    }

    return cues;
  }

  private Cue cueFor(String string) {
    return new Cue(Integer.valueOf(string.trim()).intValue());
  }

  private Time timeFor(String string) {
    return new Time(Integer.valueOf(string.trim()).intValue());
  }

  private Slave slaveFor(String string) {
    return new Slave(Integer.valueOf(string.trim()).intValue());
  }
}