package porygon.Parser;

import porygon.Block.Block;
import porygon.Block.DistanceBlock;
import porygon.Block.LineBlock;
import porygon.Tokenizer.Tokenizer;

public class DistanceParser extends Parser<Block> {

	@Override
	public boolean shouldParse(String line) {
		return line.matches("distance[\\s]*\\(([\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*){1}\\)");
	}

	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken();//skip "distance"
		tokenizer.nextToken(); // skip the first parenthesis
		String lineName = tokenizer.nextToken().getToken();
		return new DistanceBlock(superBlock,lineName);
	}

}
