package com.rocketpush.command;

import java.util.Arrays;

public class Command {
	
    private final int[] value;

    public Command(int[] command) {
        this.value = command;
    }

    Command(byte[] command) {
        this.value = toIntArray(command);
    }

    public int[] value() {
        return this.value;
    }

    public byte[] toByteArray() {
        byte[] temp = new byte[value.length];
		
        for(int i = 0; i < value.length; i++) {
            temp[i] = (byte) value[i];
        }
		
        return temp;
    }

    private int[] toIntArray(byte[] bytes) {
        int[] temp = new int[bytes.length];
		
        for(int i = 0; i < bytes.length; i++) {
            temp[i] = (int) bytes[i];
        }
		
        return temp;
    }

	
    @Override
    public String toString() {
        StringBuffer string = new StringBuffer("Command");
        for (int i = 0; i < value.length; i++) {
            string.append(" " + Integer.toHexString(value[i])); 
        }
		
        return string.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(value);

        return result;
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Command)) {
            return false;
        }
        Command other = (Command) obj;
        if (!Arrays.equals(value, other.value)) {
            return false;
        }

        return true;
    }
}