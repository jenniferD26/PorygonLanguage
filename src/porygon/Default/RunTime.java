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
	static Map<String, CircleBlock> circle = new HashMap<String, CircleBlock>();
	
	public static int RunCode(String code) {
		
		if(code.equals("exit")){
			return 0;
		}
		if(code.equals("help")){
			return 1;
		}
		if(code.equals("printall")){
			return 1;
		}

		Parser<?>[] parsers = new Parser<?>[] { 
			new PointParser(), 
			new PrintParser(), 
			new LineParser(),
			new DistanceParser(),
			new TriangleParser(),
			new QuadrilateralParser(),
			new CircleParser(),
			new AreaParser(),
			new CircumferenceParser()
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
						System.out.println("Point " +((PointBlock) newBlock).getName()+" was successfully created.");
					}
					else if(newBlock instanceof LineBlock){
						lines.put(((LineBlock) newBlock).getName(), (LineBlock) newBlock);
						System.out.println("Line " +((LineBlock) newBlock).getName()+" was successfully created.");
					}
					else if(newBlock instanceof TriangleBlock){
						triangles.put(((TriangleBlock) newBlock).getName(), (TriangleBlock) newBlock);
						System.out.println("Triangle " +((TriangleBlock) newBlock).getName()+" was successfully created.");
					}
					else if(newBlock instanceof QuadrilateralBlock){
						quadrilateral.put(((QuadrilateralBlock) newBlock).getName(), (QuadrilateralBlock) newBlock);
						System.out.println("Quadrilateral " +((QuadrilateralBlock) newBlock).getName()+" was successfully created.");
					}
					else if(newBlock instanceof CircleBlock){
						circle.put(((CircleBlock) newBlock).getName(), (CircleBlock) newBlock);
						System.out.println("Circle " +((CircleBlock) newBlock).getName()+" was successfully created.");
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
					else if(newBlock instanceof CircumferenceBlock){
						CircumferenceBlock temp=(CircumferenceBlock)newBlock;
						Object var = getVariable(temp.getLineName(),"circle");
						if(var!=null){
							System.out.println("circumference "+temp.getLineName()+" = "+temp.getCircumference());
						}
						else{
							System.out.println("Circle "+temp.getLineName()+" does not exist");
						}
						
					}
					else if(newBlock instanceof AreaBlock){
						AreaBlock temp=(AreaBlock)newBlock;
						Object var = getVariable(temp.getLineName(),"triangle");
						if(var!=null){
							System.out.println("area "+temp.getLineName()+" = "+temp.getAreaT());
						}
						else {
							var = getVariable(temp.getLineName(),"quadrilateral");
							if(var!=null){
								System.out.println("area "+temp.getLineName()+" = "+temp.getAreaQ());
							}
							else{
								var = getVariable(temp.getLineName(),"circle");
								if(var!=null){
									System.out.println("area "+temp.getLineName()+" = "+temp.getAreaC());
								}
								else{
									System.out.println("Shape "+temp.getLineName()+" does not exist");
								}
							}
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
							// searches through the triangle list
							else if(triangles.get(var) != null && triangles.get(var).getType().equals("triangle")){
								if(triangles.get(var) != null){
									System.out.println(triangles.get(var));
								}
								else{
									System.out.println("Variable "+var+" does not exist");
								}
							}
							else if(quadrilateral.get(var) != null && quadrilateral.get(var).getType().equals("quadrilateral")){
								if(quadrilateral.get(var) != null){
									System.out.println(quadrilateral.get(var));
								}
								else{
									System.out.println("Variable "+var+" does not exist");
								}
							}
							else if(circle.get(var) != null && circle.get(var).getType().equals("circle")){
								if(circle.get(var) != null){
									System.out.println(circle.get(var));
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
		else if (type.equals("circle")){
			return circle.get(name);
		}
		return null;
	}
	
	private static void getHelp(){
		System.out.println("\nThe following is a list with a short description and format for each available command:"
				+ "\npoint <var_name> : (<x_value> , <y_value>) -> Creates a point located at the "
				+ "given xy coordinates.\n\nline <var_name> : (<x1_value> , <y1_value> , "
				+ "<x2_value> , <y2_value>) -> Creates a line located at the two given xy values. "
				+ "(Basically a connection between the two given points)\n\n"
				+ "line <var_name> : (<point_var> , <point_var>) -> Creates a line using the two given "
				+ "point variables. (If they exist)\n\ntriangle <var_name> : (<x1_value> , "
				+ "<y1_value> , <x2_value> , <y2_value> , <x3_value> , <y3_value>) -> "
				+ "Creates a triangle located at the three given xy values.\n\ntriangle <var_name> : "
				+ "(<point_var> , <point_var> , <point_var>) -> Creates a triangle using the three given "
				+ "points.\n\nquadrilateral <var_name> : (<x1_value> , <y1_value> , <x2_value> , "
				+ "<y2_value> , <x3_value> , <y3_value> , <x4_value> , <y4_value>) -> "
				+ "Creates a quadrilateral located at the four xy values.\n\nquadrilateral "
				+ "<var_name> : (<point_var> , <point_var> , <point_var>) , <point_var>) -> "
				+ "Creates a quadrilateral using the four given points.\n\ncircle <var_name> : "
				+ "(<x1_value> , <y1_value> , <x2_value> , <y2_value>) -> Creates a circle using the "
				+ "given coordinates. (x1y1 being the circle's top, x2y2 the center)\n\n"
				+ "circle <var_name> : (<point_var> , <point_var>) -> Creates a circle using the "
				+ "given points. (Same case explained before)\n\ncircle <var_name> : (<line_var>) -> "
				+ "Creates a circle using the given line as it's radius.\n\nprint (<var_list>) -> "
				+ "Shows the values of the given variables. (Variables can be separated by "
				+ "whitespaces or commas)\n\nprintall -> Shows the names and values of all the "
				+ "variables in the system.\n\ndistance (<line_var>) -> Calculates the length of the given "
				+ "line.\n\ncircumference (<circle_var>) -> Calculates the circumference of the given "
				+ "circle.\n\narea (<shape_var>) -> Calculates the area of the given shape. "
				+ "(Currently, shapes can be either a triangle, quadrilateral or a circle)\n\n"
				+ "help -> ...\n\nexit -> Terminates Porygon.\n");
	}
	
	private static void printAll() {
		if (points.isEmpty() && lines.isEmpty() && triangles.isEmpty() && quadrilateral.isEmpty() && circle.isEmpty()) {
			System.out.println("There are currently no variables in the system.");
		}
		else {
			if (!points.isEmpty()){
				System.out.println("Points: \n");
				for (PointBlock var : points.values()) {
					System.out.println(var.toString());
				}
				System.out.println();
			}
			if (!lines.isEmpty()){
				System.out.println("Lines: \n");
				for (LineBlock var : lines.values()) {
					System.out.println(var.toString());
				}
				System.out.println();
			}
			if (!triangles.isEmpty()){
				System.out.println("Triangles: \n");
				for (TriangleBlock var : triangles.values()) {
					System.out.println(var.toString());
				}
				System.out.println();
			}
			if (!quadrilateral.isEmpty()){
				System.out.println("Quadrilaterals: \n");
				for (QuadrilateralBlock var : quadrilateral.values()) {
					System.out.println(var.toString());
				}
				System.out.println();
			}
			if (!circle.isEmpty()){
				System.out.println("Circles: \n");
				for (CircleBlock var : circle.values()) {
					System.out.println(var.toString());
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		
		System.out.println("If you wish to see the list of available commands, type \"help\" "
				+ "(Without the quotes)\n");
		Scanner scanner = new Scanner(System.in); 
		String input = "";
		int error = 0;
		
		while(!input.equals("exit")){
			if (input.equals("help")){
				getHelp();
			}
			if (input.equals("printall")){
				printAll();
			}
			System.out.print("porygon> ");
			input = scanner.nextLine();

			error = RunCode(input);
		}
		
		if(error == 0){
			System.out.println("Exit Success");
			scanner.close();
		}
	}
}