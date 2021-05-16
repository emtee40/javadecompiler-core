package jd.instruction.bytecode.factory;

import java.util.List;
import java.util.Stack;

import jd.classfile.ClassFile;
import jd.classfile.Method;
import jd.instruction.bytecode.ByteCodeConstants;
import jd.instruction.bytecode.instruction.Instruction;
import jd.instruction.bytecode.instruction.StoreInstruction;


public class LStoreFactory extends InstructionFactory
{
	public int create(
			ClassFile classFile, Method method, List<Instruction> list, 
			List<Instruction> listForAnalyze,  
			Stack<Instruction> stack, byte[] code, int offset, 
			int lineNumber, boolean[] jumps)
	{
		final int opcode = code[offset] & 255;
		int index;
		
		if (opcode == ByteCodeConstants.LSTORE)
			index = code[offset+1] & 255;
		else
			index = opcode - ByteCodeConstants.LSTORE_0;
		
		final Instruction instruction = new StoreInstruction(
			ByteCodeConstants.STORE, offset, lineNumber, 
			index, "J", stack.pop());
		
		list.add(instruction);
		listForAnalyze.add(instruction);
		
		return ByteCodeConstants.NO_OF_OPERANDS[opcode];
	}
}