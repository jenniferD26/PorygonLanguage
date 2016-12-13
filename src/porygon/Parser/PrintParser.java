package porygon.Parser;

import java.util.ArrayList;

import porygon.Block.Block;
import porygon.Block.Print;
import porygon.Tokenizer.Token;
import porygon.Tokenizer.Tokenizer;

public class PrintParser extends Parser<Block>{
	//Override
	public boolean shouldParse(String line){
		return line.matches("print[\\s]*\\(([\\s]*[a-zA-Z]+[a-zA-Z0-9]*[\\s]*[,]?[\\s]*)*\\)");
	}
	
	//Override
	public Print parse(Block superBlock, Tokenizer tokenizer){

		Token v = tokenizer.nextToken();	// skips the print
		v = tokenizer.nextToken();	// skips the parenthesis
		
		// get all of the variables
		ArrayList<String> variables = new ArrayList<>();
		v = tokenizer.nextToken();
		while(!v.getToken().equals(")")){
			
			if(!v.getToken().equals(",")){
				variables.add(v.getToken());
				v = tokenizer.nextToken();
			}
			else{
				v = tokenizer.nextToken();
			}
		}
		return new Print(superBlock, variables);
	}
}
