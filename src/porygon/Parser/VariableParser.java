package porygon.Parser;

import porygon.Block.Block;
import porygon.Block.VariableBlock;
import porygon.Tokenizer.Token;
import porygon.Tokenizer.TokenType;
import porygon.Tokenizer.Tokenizer;

public class VariableParser extends Parser<Block> {

	@Override
	public boolean shouldParse(String line) {
		return line.matches("[a-zA-Z]+ [a-zA-Z]+ : [-]?[0-9]*");
	}

	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		//tokenizer.nextToken(); // Skip the var token.
		
		String type = tokenizer.nextToken().getToken();
		
		String name = tokenizer.nextToken().getToken();
		
		tokenizer.nextToken(); // Skip the = token.
		
		Token v = tokenizer.nextToken();
		Object value = null;
		
		if (v.getType() == TokenType.INTEGER_LITERAL) {
			value = Integer.valueOf(v.getToken());
		}
		
		else /* If it's an identifier */ {
			value = superBlock.getVariable(v.getToken()).getValue();
		}
		
		return new VariableBlock(superBlock, type, name, value);
	}
}