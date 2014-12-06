package org.arabikitouhu.direction;

public class Direction {

	public static final int UP		= 0;
	public static final int DOWN	= 1;
	public static final int NORTH	= 2;
	public static final int SOUTH	= 3;
	public static final int EAST	= 4;
	public static final int WEST	= 5;


	private int id;

	public Direction(int id) {
		this.id = id;
	}

	public int GetValue() { return id; }

	public void SetID(int value) { id = value; }
}
