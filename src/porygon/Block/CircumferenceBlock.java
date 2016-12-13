package porygon.Block;

import porygon.Default.RunTime;

public class CircumferenceBlock extends Block {

	String lineName="";

	public CircumferenceBlock(Block superBlock, String line) {
		super(superBlock);
		this.lineName=line;
	}
	public String getLineName(){
		return lineName;
	}
	@Override
	public void run() {
	}
	public double getCircumference(){
		CircleBlock var=(CircleBlock)RunTime.getVariable(lineName, "circle");
		if(var!=null){
			double x1=Double.parseDouble(""+(((PointBlock)var.getRadius1()).getXValue()));
			double x2=Double.parseDouble(""+(((PointBlock)var.getRadius2()).getXValue()));
			double y1=Double.parseDouble(""+(((PointBlock)var.getRadius1()).getYValue()));
			double y2=Double.parseDouble(""+(((PointBlock)var.getRadius2()).getYValue()));
			double resultX=Math.pow(x2-x1,2);
			double resultY=Math.pow(y2-y1,2);
			double radius = Math.sqrt(resultX+resultY);
			double area=2*Math.PI*radius;
			return area;
		}
		return 0;
	}
}
