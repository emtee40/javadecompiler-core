package jd.instruction.bytecode.factory;

import java.util.List;
import java.util.Stack;

import jd.classfile.ClassFile;
import jd.classfile.Method;
import jd.instruction.bytecode.instruction.Instruction;
import jd.instruction.bytecode.instruction.TableSwitch;


public class TableSwitchFactory extends InstructionFactory
{
	public int create(
			ClassFile classFile, Method method, List<Instruction> list, 
			List<Instruction> listForAnalyze,  
			Stack<Instruction> stack, byte[] code, int offset, 
			int lineNumber, boolean[] jumps)
	{
		final int opcode = code[offset] & 255;

		// Skip padding
		int i = (offset+4) & 0xFFFC;
		
		final int defaultOffset = 
			((code[i  ] & 255) << 24) | ((code[i+1] & 255) << 16) |
            ((code[i+2] & 255) << 8 ) |  (code[i+3] & 255);
		
		i += 4;
		
		final int low = 
			((code[i  ] & 255) << 24) | ((code[i+1] & 255) << 16) |
            ((code[i+2] & 255) << 8 ) |  (code[i+3] & 255);
		
		i += 4;
		
		final int high = 
			((code[i  ] & 255) << 24) | ((code[i+1] & 255) << 16) |
            ((code[i+2] & 255) << 8 ) |  (code[i+3] & 255);
		
		i += 4;

		int length = high - low + 1;
		int[] offsets = new int[length];

		for (int j=0; j<length; j++)
		{
			offsets[j] = 
				((code[i  ] & 255) << 24) | ((code[i+1] & 255) << 16) |
	            ((code[i+2] & 255) << 8 ) |  (code[i+3] & 255);
							
			i += 4;
		}
		
		final Instruction key = stack.pop();

		list.add(new TableSwitch(
			opcode, offset, lineNumber, key, defaultOffset, 
			offsets, low, high));
		
		return (i - offset - 1);
	}
}