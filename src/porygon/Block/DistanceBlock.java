package porygon.Block;

import porygon.Default.RunTime;

public class DistanceBlock extends Block {
	String lineName="";

	public DistanceBlock(Block superBlock, String line) {
		super(superBlock);
		this.lineName=line;
	}
	public String getLineName(){
		return lineName;
	}
	@Override
	public void run() {

	}
	public double getDistance() {
		LineBlock var=(LineBlock)RunTime.getVariable(lineName, "line");
		double x1=Double.parseDouble(""+(((PointBlock)var.getLValue()).getXValue()));
		double x2=Double.parseDouble(""+(((PointBlock)var.getRValue()).getXValue()));
		double y1=Double.parseDouble(""+(((PointBlock)var.getLValue()).getYValue()));
		double y2=Double.parseDouble(""+(((PointBlock)var.getRValue()).getYValue()));
		double resultX=Math.pow(x2-x1,2);
		double resultY=Math.pow(y2-y1,2);
		return Math.sqrt(resultX+resultY);
	}

}
