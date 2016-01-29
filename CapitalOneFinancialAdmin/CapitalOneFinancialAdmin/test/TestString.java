import java.util.HashMap;

public class TestString {

	public static void main(String[] args) {
		String name = " ?,  ?,  ?,  ?,  ?, ";
		name = name.trim();
		System.out.println(name.substring(0, name.length()-1));
		
		HashMap<String, String> hashMap = new HashMap<String,String>();
		hashMap.put("Shuvradipta", "Saha");
		hashMap.put("Ratul", "BHattacharya");
		
		System.out.println(hashMap);
		
	}

}
