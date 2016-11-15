package porygon.Block;

import java.util.ArrayList;

import porygon.Default.BuiltInType;
import porygon.Default.Type;

public class LineBlock extends Block {
	private String type, name;
	private Object rValue;
	private Object lValue;
	private ArrayList<Object> values = new ArrayList<Object>();
	
	public LineBlock(Block superBlock, String type, String name, Object rValue, Object lValue) {
		super(superBlock);
		
		this.type = type;
		this.name = name;
		this.rValue = rValue;
		this.lValue = lValue;
		values.add(rValue);
		values.add(lValue);
	}
	
	public String getName() {
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public Object getRValue(){
		return rValue;
	}
	
	public Object getLValue(){
		return lValue;
	}
	
	public String toString() {
		return new String (name + " = "+((PointBlock)rValue).linePrint()+ " and "+((PointBlock)lValue).linePrint());
	}

	@Override
	public void run() {
		Type t = Type.match(type);
		
		if (t == BuiltInType.VOID) {
			throw new IllegalStateException("Cannot declare variables of type void.");
		}
		
		//getSuperBlock().addVariable(new Point(getSuperBlock(), name, values));
	}
}
