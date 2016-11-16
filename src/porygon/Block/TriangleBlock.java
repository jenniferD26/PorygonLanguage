package porygon.Block;

import java.util.ArrayList;

import porygon.Default.BuiltInType;
import porygon.Default.Type;

public class TriangleBlock extends Block {

	private String type, name;
	private Object value1;
	private Object value2;
	private Object value3;
	private ArrayList<Object> values = new ArrayList<Object>();
	
	public TriangleBlock(Block superBlock, String type, String name, Object value1, Object value2, Object value3) {
		super(superBlock);
		this.type = type;
		this.name = name;
		this.value1 = value1;
		this.value2 = value2;
		this.value3=value3;
		values.add(value1);
		values.add(value2);
		values.add(value3);
	}
	
	public String getName() {
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public Object getValue1(){
		return value1;
	}
	
	public Object getValue2(){
		return value2;
	}
	public Object getValue3(){
		return value3;
	}
	
	public String toString() {
		return new String (name + " = "+((PointBlock)value1).linePrint()+ ", "+((PointBlock)value2).linePrint()+ " and "+((PointBlock)value3).linePrint());
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

