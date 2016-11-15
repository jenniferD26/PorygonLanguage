package porygon.Block;

import java.util.ArrayList;

import porygon.Default.RunTime;

import porygon.Default.BuiltInType;
import porygon.Default.Type;

public class PointBlock extends Block {

	private String type, name;
	private Object xValue;
	private Object yValue;
	private ArrayList<Object> values = new ArrayList<Object>();
	
	public PointBlock(Block superBlock, String type, String name, Object xValue, Object yValue) {
		super(superBlock);
		
		this.type = type;
		this.name = name;
		this.xValue = xValue;
		this.yValue = yValue;
		values.add(xValue);
		values.add(yValue);
	}
	
	public PointBlock(Block superBlock, String type, String name) {
		super(superBlock);
		
		this.type = type;
		this.name = name;
		PointBlock block = (PointBlock)RunTime.getVariable(name, type);
		this.xValue = block.xValue;
		this.yValue = block.yValue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public String toString() {
		return new String (name + " = ("+xValue+" , "+yValue+")");
	}
	
	public String linePrint(){
		return new String ("("+xValue+" , "+yValue+")");
	}

	@Override
	public void run() {
		Type t = Type.match(type);
		
		if (t == BuiltInType.VOID) {
			throw new IllegalStateException("Cannot declare variables of type void.");
		}
	}
}
