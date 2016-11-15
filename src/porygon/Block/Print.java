package porygon.Block;

import java.util.ArrayList;

import porygon.Default.Variable;

public class Print extends Block {

	private ArrayList<String> variables= new ArrayList<>();
	
	public Print(Block superBlock, ArrayList<String> variables){
		super(superBlock);
		this.variables = variables;
	}
	
	public ArrayList<String> getVariables()
	{
		return variables;
	}
	
	public void run() {
	}
}
