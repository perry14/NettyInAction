package com.perry14.chapter6;

import java.util.Random;

public class MemcachedRequest { // 1
	private static final Random rand = new Random();
	private final int magic = 0x80;// fixed so hard coded
	private final byte opCode; // the operation e.g. set or get
	private final String key; // the key to delete, get or set
	private final int flags = 0xdeadbeef; // random
	private final int expires; // 0 = item never expires
	private final String body; // if opCode is set, the value
	private final int id = rand.nextInt(); // Opaque
	private final long cas = 0; // data version check...not used
	private final boolean hasExtras; // not all ops have extras

	public MemcachedRequest(byte opcode, String key, String value) {
		this.opCode = opcode;
		this.key = key;
		this.body = value == null ? "" : value;
		this.expires = 0;
		// only set command has extras in our example
		hasExtras = opcode == Opcode.SET;
	}

	public MemcachedRequest(byte opCode, String key) {
		this(opCode, key, null);
	}

	public int magic() { // 2
		return magic;
	}

	public int opCode() { // 3
		return opCode;
	}

	public String key() { // 4
		return key;
	}

	public int flags() { // 5
		return flags;
	}

	public int expires() { // 6
		return expires;
	}

	public String body() { // 7
		return body;
	}

	public int id() { // 8
		return id;
	}

	public long cas() { // 9
		return cas;
	}

	public boolean hasExtras() { // 10
		return hasExtras;
	}

	public class Status {
		public static final short NO_ERROR = 0x0000;
		public static final short KEY_NOT_FOUND = 0x0001;
		public static final short KEY_EXISTS = 0x0002;
		public static final short VALUE_TOO_LARGE = 0x0003;
		public static final short INVALID_ARGUMENTS = 0x0004;
		public static final short ITEM_NOT_STORED = 0x0005;
		public static final short INC_DEC_NON_NUM_VAL = 0x0006;
	}

	public class Opcode {
		public static final byte GET = 0x00;
		public static final byte SET = 0x01;
		public static final byte DELETE = 0x04;
	}
}