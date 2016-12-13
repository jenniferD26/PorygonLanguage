package porygon.Parser;
import porygon.Block.Block;
import porygon.Block.CircumferenceBlock;
import porygon.Tokenizer.Tokenizer;

public class CircumferenceParser extends Parser<Block>{
	@Override
	public boolean shouldParse(String line) {
		return line.matches("circumference[\\s]*\\(([\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*){1}\\)");
	}

	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken();//skip "area"
		tokenizer.nextToken(); // skip the first parenthesis
		String lineName = tokenizer.nextToken().getToken();
		return new CircumferenceBlock(superBlock,lineName);
	}
}
