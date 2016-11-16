package porygon.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import porygon.Block.*;
import porygon.Parser.*;
import porygon.Tokenizer.*;

public class RunTime {
	static Map<String, PointBlock> points = new HashMap<String, PointBlock>();
	static Map<String, LineBlock> lines = new HashMap<String, LineBlock>();
	static Map<String, TriangleBlock> triangles = new HashMap<String, TriangleBlock>();
	static Map<String, QuadrilateralBlock> quadrilateral = new HashMap<String, QuadrilateralBlock>();
	
	public static int RunCode(String code) {
		
		if(code.equals("exit")){
			return 0;
		}

		Parser<?>[] parsers = new Parser<?>[] { 
			new PointParser(), 
			new PrintParser(), 
			new LineParser(),
			new DistanceParser(),
			new TriangleParser(),
			new QuadrilateralParser(),
			};
		
		Block block = null;
		
		boolean success = false;
			code = code.trim();
			Tokenizer tokenizer = new Tokenizer(code);
			
			for (Parser<?> parser : parsers) {
				if (parser.shouldParse(code)) {
					Block newBlock = parser.parse(block, tokenizer);
					
					if(newBlock instanceof PointBlock){
						points.put(((PointBlock) newBlock).getName(), (PointBlock) newBlock);
					}
					else if(newBlock instanceof LineBlock){
						lines.put(((LineBlock) newBlock).getName(), (LineBlock) newBlock);
					}
					else if(newBlock instanceof TriangleBlock){
						triangles.put(((TriangleBlock) newBlock).getName(), (TriangleBlock) newBlock);
					}
					else if(newBlock instanceof QuadrilateralBlock){
						quadrilateral.put(((QuadrilateralBlock) newBlock).getName(), (QuadrilateralBlock) newBlock);
					}
					else if(newBlock instanceof DistanceBlock){
						DistanceBlock temp=(DistanceBlock)newBlock;
						Object var = getVariable(temp.getLineName(),"line");
						if(var!=null){
							System.out.println("distance "+temp.getLineName()+" = "+temp.getDistance());
						}
						else{
							System.out.println("Line "+temp.getLineName()+" does not exist");
						}
						
					}
					else if(newBlock instanceof Print){
						
						// print all of the variables
						for(String var : ((Print)newBlock).getVariables()){
							
							// searches through the point list
							if(points.get(var) != null && points.get(var).getType().equals("point")){
									System.out.println(points.get(var));
							}
							
							// searches through the line list
							else if(lines.get(var) != null && lines.get(var).getType().equals("line")){
								if(lines.get(var) != null){
									System.out.println(lines.get(var));
								}
								else{
									System.out.println("Variable "+var+" does not exist");
								}
							}
							// searches through the triangles list
							else if(triangles.get(var) != null && triangles.get(var).getType().equals("triangle")){
								if(triangles.get(var) != null){
									System.out.println(triangles.get(var));
								}
								else{
									System.out.println("Variable "+var+" does not exist");
								}
							}
							else{
								System.out.println("Variable "+var+" does not exist");
							}
						}
					}
					else {
						System.out.println("Unable to create variable, because one or more values is incorrect: " + code);
						return 1;
					}
					
					block = newBlock;
					success = true;
					break;
				}
			}
			
			if (block == null) {
				System.out.println("Incorrect Syntax: " + code);
		}
		
		if(success){
			block.run();
			return 0;
		}
		
		return 1;
	}
	
	public static Object getVariable(String name, String type){
		if(type.equals("point")){
			return points.get(name);
		}
		else if (type.equals("line")){
			return lines.get(name);
		}
		else if(type.equals("quadrilateral")){
			return quadrilateral.get(name);
		}
		else if (type.equals("triangle")){
			return triangles.get(name);
		}
		
		return null;
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); 
		String input = "";
		int error = 0;
		
		while(!input.equals("exit")){
			System.out.print("porygon> ");
			input = scanner.nextLine();

			error = RunCode(input);
		}
		
		if(error == 0){
			System.out.println("Exit Success");
		}
	}
}