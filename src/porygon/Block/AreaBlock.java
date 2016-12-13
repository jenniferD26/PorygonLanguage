package porygon.Block;

import porygon.Default.RunTime;

public class AreaBlock extends Block{
	String lineName="";

	public AreaBlock(Block superBlock, String line) {
		super(superBlock);
		this.lineName=line;
	}
	public String getLineName(){
		return lineName;
	}
	@Override
	public void run() {

	}
	public double getAreaT() {
		TriangleBlock var=(TriangleBlock)RunTime.getVariable(lineName, "triangle");
		if(var!=null){
			double x1=Double.parseDouble(""+(((PointBlock)var.getValue1()).getXValue()));
			double x2=Double.parseDouble(""+(((PointBlock)var.getValue2()).getXValue()));
			double x3=Double.parseDouble(""+(((PointBlock)var.getValue3()).getXValue()));
			double y3=Double.parseDouble(""+(((PointBlock)var.getValue3()).getYValue()));
			double y1=Double.parseDouble(""+(((PointBlock)var.getValue1()).getYValue()));
			double y2=Double.parseDouble(""+(((PointBlock)var.getValue2()).getYValue()));
			double result1=x2*(y3-y1);
			double result2=x1*(y2-y3);
			double result3=x3*(y1-y2);
			double result4=Math.abs((result1+result2+result3)/2);
			return result4;
		}
		return 0;
	}
	public double getAreaQ() {
		QuadrilateralBlock var=(QuadrilateralBlock)RunTime.getVariable(lineName, "quadrilateral");
		if(var!=null){
			double x1=Double.parseDouble(""+(((PointBlock)var.getValue1()).getXValue()));
			double x2=Double.parseDouble(""+(((PointBlock)var.getValue2()).getXValue()));
			double x3=Double.parseDouble(""+(((PointBlock)var.getValue3()).getXValue()));
			double x4=Double.parseDouble(""+(((PointBlock)var.getValue4()).getXValue()));
			double y1=Double.parseDouble(""+(((PointBlock)var.getValue1()).getYValue()));
			double y2=Double.parseDouble(""+(((PointBlock)var.getValue2()).getYValue()));
			double y3=Double.parseDouble(""+(((PointBlock)var.getValue3()).getYValue()));
			double y4=Double.parseDouble(""+(((PointBlock)var.getValue4()).getYValue()));
			double result1=x1*(y2);
			double result2=x2*(y3);
			double result3=x3*(y4);
			double result4=x4*(y1);
			double result5=x2*(y1);
			double result6=x3*(y2);
			double result7=x4*(y3);
			double result8=x1*(y4);
			double result9=Math.abs((result1+result2+result3+result4-result5-result6-result7-result8)/2);
			return result9;
		}
		return 0;
	}
	public double getAreaC() {
		CircleBlock var=(CircleBlock)RunTime.getVariable(lineName, "circle");
		if(var!=null){
			double x1=Double.parseDouble(""+(((PointBlock)var.getRadius1()).getXValue()));
			double x2=Double.parseDouble(""+(((PointBlock)var.getRadius2()).getXValue()));
			double y1=Double.parseDouble(""+(((PointBlock)var.getRadius1()).getYValue()));
			double y2=Double.parseDouble(""+(((PointBlock)var.getRadius2()).getYValue()));
			double resultX=Math.pow(x2-x1,2);
			double resultY=Math.pow(y2-y1,2);
			double radius = Math.sqrt(resultX+resultY);
			double area=2*Math.PI*(Math.pow(radius, 2));
			return area;
		}
		return 0;
	}
}
