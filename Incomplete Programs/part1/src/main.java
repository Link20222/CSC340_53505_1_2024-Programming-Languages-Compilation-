import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class main {
	
	static void printList(List list) {
		for(Object obj : list) {
			if(obj != null)
				System.out.println(obj);
		}
		System.out.println();
	}
	
	public static void main(String args[]){
		
		List<Token> listOfTokens = new ArrayList<Token>();		
		File f = new File("src\\mython.txt");
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Lexer l = new Lexer(fr);
		
		
		while(!l.yyatEOF()) {
			try {
				listOfTokens.add(l.yylex());
			} catch (Exception e) {
				System.out.println("\nEOF/Error");
			}
		}
		
		printList(listOfTokens);
		
		System.out.println("End of main!");
	}
	
}
