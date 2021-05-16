package jd.instruction.fast.analyzer;


public class UnexpectedInstructionException extends RuntimeException
{
	private static final long serialVersionUID = -3407799517256621265L;

	public UnexpectedInstructionException() 
	{ 
		super(); 
	}
	
	public UnexpectedInstructionException(String s) 
	{ 
		super(s); 
	}
}