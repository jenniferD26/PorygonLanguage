package porygon.Block;

import porygon.Default.BuiltInType;
import porygon.Default.Type;

public class CircleBlock extends Block {

		private String type, name;
		private Object radius1, radius2;
		
		public CircleBlock(Block superBlock, String type, String name, Object radius1, Object radius2) {
			super(superBlock);
			this.type = type;
			this.name = name;
			this.radius1 = radius1;
			this.radius2 = radius2;
		}
		
		public String getName() {
			return name;
		}
		
		public String getType(){
			return type;
		}
		
		public Object getRadius1(){
			return radius1;
		}
		public Object getRadius2(){
			return radius2;
		}
		public String toString() {
			return new String (name + " -> r = "+getRadius());
		}
		private double getRadius(){
			double x1=Double.parseDouble(""+(((PointBlock)radius1).getXValue()));
			double x2=Double.parseDouble(""+(((PointBlock)radius2).getXValue()));
			double y1=Double.parseDouble(""+(((PointBlock)radius1).getYValue()));
			double y2=Double.parseDouble(""+(((PointBlock)radius2).getYValue()));
			double resultX=Math.pow(x2-x1,2);
			double resultY=Math.pow(y2-y1,2);
			return Math.sqrt(resultX+resultY);
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
