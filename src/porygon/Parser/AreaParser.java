package porygon.Parser;

import porygon.Block.AreaBlock;
import porygon.Block.Block;
import porygon.Tokenizer.Tokenizer;

public class AreaParser extends Parser<Block>{
	@Override
	public boolean shouldParse(String line) {
		return line.matches("area[\\s]*\\(([\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*){1}\\)");
	}

	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken();//skip "area"
		tokenizer.nextToken(); // skip the first parenthesis
		String lineName = tokenizer.nextToken().getToken();
		return new AreaBlock(superBlock,lineName);
	}
}
