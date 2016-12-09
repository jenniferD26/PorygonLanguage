package porygon.Parser;

import java.util.ArrayList;

import porygon.Block.Block;
import porygon.Block.PointBlock;
import porygon.Block.QuadrilateralBlock;
import porygon.Tokenizer.Token;
import porygon.Tokenizer.Tokenizer;

public class QuadrilateralParser extends Parser<Block> {
public boolean pointsFlag = false;
	
	@Override
	public boolean shouldParse(String line) {
		if(line.matches("quadrilateral[\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*:[\\s]?\\(([\\s]*[-]?[0-9]+[\\s]*[,][\\s]*){7}[-]?[0-9]+[\\s]*\\)"))
		{
			return true;
		}
		else if (line.matches("quadrilateral[\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*:[\\s]?\\(([\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*[,][\\s]*){3}[a-zA-Z]+[a-zA-Z0-9]*[\\s]*\\)"))
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
		PointBlock value1;
		PointBlock value2;
		PointBlock value3;
		PointBlock value4;
		
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
			value1 = new PointBlock(superBlock, "point", name, values.get(0), values.get(1));
			value2 = new PointBlock(superBlock, "point", name, values.get(2), values.get(3));
			value3 = new PointBlock(superBlock, "point", name, values.get(4), values.get(5));
			value4 = new PointBlock(superBlock, "point", name, values.get(6), values.get(7));
		}
		else{
			try{
				value1 = new PointBlock(superBlock, "point", values.get(0).toString());
				value2 = new PointBlock(superBlock, "point", values.get(1).toString());
				value3 = new PointBlock(superBlock, "point", values.get(2).toString());
				value4 = new PointBlock(superBlock, "point", values.get(3).toString());
			}
			catch(Exception e){
				return null;
			}
		}
		
		
		return new QuadrilateralBlock(superBlock, type, name, value1,value2,value3,value4);
	}
}
