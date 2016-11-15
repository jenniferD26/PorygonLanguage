package porygon.Parser;

import porygon.Block.Block;
import porygon.Block.PointBlock;
import porygon.Block.VariableBlock;
import porygon.Tokenizer.Token;
import porygon.Tokenizer.TokenType;
import porygon.Tokenizer.Tokenizer;

public class PointParser extends Parser<Block> {
	@Override
	public boolean shouldParse(String line) {
		return line.matches("point[\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*:[\\s]?\\(([\\s]*[-]?[0-9]*[\\s]*[,]?[\\s]*){2}\\)");
	}

	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		//tokenizer.nextToken(); // Skip the var token.
		
		String type = tokenizer.nextToken().getToken();
		
		String name = tokenizer.nextToken().getToken();
		
		tokenizer.nextToken(); // Skip the : token.
		tokenizer.nextToken(); // skip the first parenthesis
		
		Token v = tokenizer.nextToken();
		Object xValue = null;
		Object yValue = null;
		
		if (v.getType() == TokenType.INTEGER_LITERAL) {
			xValue = Integer.valueOf(v.getToken());
		}
		else /* If it's an identifier */ {
			xValue = superBlock.getVariable(v.getToken()).getValue();
		}
		
		tokenizer.nextToken(); // skip the comma
		
		v = tokenizer.nextToken();
		
		if (v.getType() == TokenType.INTEGER_LITERAL) {
			yValue = Integer.valueOf(v.getToken());
		}
		else {
			yValue = superBlock.getVariable(v.getToken()).getValue();
		}
		
		return new PointBlock(superBlock, type, name, xValue, yValue);
	}
}
