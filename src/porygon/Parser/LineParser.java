package porygon.Parser;

import java.util.ArrayList;

import porygon.Block.Block;
import porygon.Block.LineBlock;
import porygon.Block.PointBlock;
import porygon.Tokenizer.Token;
import porygon.Tokenizer.Tokenizer;

public class LineParser extends Parser<Block> {
	
	public boolean pointsFlag = false;
	
	@Override
	public boolean shouldParse(String line) {
		if(line.matches("line[\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*:[\\s]?\\(([\\s]*[-]?[0-9]+[\\s]*[,]{1}[\\s]*){3}[-]?[0-9]+[\\s]*\\)"))
		{
			return true;
		}
		else if (line.matches("line[\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*:[\\s]?\\([\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*[,][\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*\\)"))
		{
			pointsFlag = true;
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
		PointBlock rValue;
		PointBlock lValue;
		
		while(!v.getToken().equals(")")){
			
			if(!v.getToken().equals(",")){
				values.add(v.getToken());
				v = tokenizer.nextToken();
			}
			else{
				v = tokenizer.nextToken();
			}
		}
		
		if(!pointsFlag){
			rValue = new PointBlock(superBlock, "point", name, values.get(0), values.get(1));
			lValue = new PointBlock(superBlock, "point", name, values.get(2), values.get(3));
		}
		else{
			try{
				rValue = new PointBlock(superBlock, "point", values.get(0).toString());
				lValue = new PointBlock(superBlock, "point", values.get(1).toString());
			}
			catch(Exception e){
				return null;
			}
		}
		
		
		return new LineBlock(superBlock, type, name, rValue, lValue);
	}
}
