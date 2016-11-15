package porygon.Default;

public interface Type {
	public static Type match(String str){
		try{
			return BuiltInType.valueOf(str.toUpperCase());
		}
		catch (Exception e){
			// TODO: match str to a class
			return null;
		}
	}
}
