package es.upm.pproject.sokoban.model;

public class WrongActionException extends Exception{
	
	private static final long serialVersionUID = 4;

	//This exception will be thrown if an Action tries to move a box that doesn't exist
	public WrongActionException(String message) {
		super(message);
	}
}
