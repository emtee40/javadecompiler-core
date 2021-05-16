package jd.instruction.bytecode.instruction;

import jd.classfile.ConstantPool;
import jd.classfile.LocalVariables;
import jd.classfile.constant.ConstantFieldref;
import jd.classfile.constant.ConstantNameAndType;

public class GetField extends IndexInstruction
{
	public Instruction objectref;

	public GetField(
		int opcode, int offset, int lineNumber, 
		int index, Instruction objectref)
	{
		super(opcode, offset, lineNumber, index);
		this.objectref = objectref;
	}

	public String getReturnedSignature(
			ConstantPool constants, LocalVariables localVariables) 
	{
		if (constants == null)
			return null;
		
		ConstantFieldref cfr = constants.getConstantFieldref(this.index);
		if (cfr == null)
			return null;
		
		ConstantNameAndType cnat = 
			constants.getConstantNameAndType(cfr.name_and_type_index);
		if (cnat == null)
			return null;
		
		return constants.getConstantUtf8(cnat.descriptor_index);
	}
}