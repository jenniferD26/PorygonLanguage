package porygon.Parser;

import porygon.Block.Block;
import porygon.Tokenizer.Tokenizer;

public abstract class Parser<T extends Block> {
	
	// Takes a line and checks to see which parser it's for by using regular expressions
	public abstract boolean shouldParse(String line);
	
	// Takes the superblock and the tokenizer for the line and return a block of this parser's type
	public abstract T parse(Block superBlock, Tokenizer tokenizer);
}
