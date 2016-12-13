package porygon.Parser;

import java.util.ArrayList;

import porygon.Block.Block;
import porygon.Block.PointBlock;
import porygon.Default.RunTime;
import porygon.Block.CircleBlock;
import porygon.Block.LineBlock;
import porygon.Tokenizer.Token;
import porygon.Tokenizer.Tokenizer;

public class CircleParser extends Parser<Block> {
public boolean pointsFlag = false, lineFlag=false;
	
	@Override
	public boolean shouldParse(String line) {
		if(line.matches("circle[\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*:[\\s]*\\(([\\s]*[-]?[0-9]*[\\s]*[,][\\s]*){3}[-]?[0-9]+[\\s]*\\)"))
		{
			return true;
		}
		else if (line.matches("circle[\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*:[\\s]*\\(([\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*[,][\\s]*){1}[a-zA-Z]+[a-zA-Z0-9]*[\\s]*\\)"))
		{
			pointsFlag = true;
			return true;
		}
		else if (line.matches("circle[\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*:[\\s]*\\([\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*[\\s]*\\)"))
		{
			lineFlag = true;
			return true;
		}
		
		return false;
	}

	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		
		String type = tokenizer.nextToken().getToken();
		
		String name = tokenizer.nextToken().getToken();
		
		tokenizer.nextToken(); // Skip the : token.
		tokenizer.nextToken(); // skip the first parenthesis
		
		Token v = tokenizer.nextToken();
		ArrayList<Object> values = new ArrayList<>();
		PointBlock radius1,radius2;
		
		while(!v.getToken().equals(")")){
			
			if(!v.getToken().equals(",")){
				values.add(v.getToken());
				v = tokenizer.nextToken();
			}
			else{
				v = tokenizer.nextToken();
			}
		}
		
		if(!pointsFlag&&!lineFlag){
			radius1 = new PointBlock(superBlock, "point", name, values.get(0), values.get(1));
			radius2=new PointBlock(superBlock, "point", name, values.get(2), values.get(3));
		}
		else if(lineFlag){
			try{
				LineBlock line= (LineBlock) RunTime.getVariable(values.get(0).toString(), "line");
				radius1 = new PointBlock(superBlock, "point",name,((PointBlock)line.getLValue()).getXValue(), ((PointBlock)line.getLValue()).getYValue());
				radius2 = new PointBlock(superBlock, "point",name,((PointBlock)line.getRValue()).getXValue(), ((PointBlock)line.getRValue()).getYValue());
			}
			catch(Exception e){
				return null;
			}
		}
		else{
			try{
				radius1 = new PointBlock(superBlock, "point", values.get(0).toString());
				radius2 = new PointBlock(superBlock, "point", values.get(1).toString());
			}
			catch(Exception e){
				return null;
			}
		}
		return new CircleBlock(superBlock, type, name, radius1,radius2);
	}
}
