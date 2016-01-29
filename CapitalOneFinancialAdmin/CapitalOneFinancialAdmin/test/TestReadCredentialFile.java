import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestReadCredentialFile {

	public static void main(String[] args) {
		File credentialFile = new File("C:\\Users\\Shuvra\\Documents\\CapitalOneFinancial\\ApplicationCode\\CapitalOneFinancialAdmin\\WebContent\\WEB-INF\\credentials.txt");
			FileReader fileReader;
			try {
				fileReader = new FileReader(credentialFile);
				
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				try {
					String rawCredential = bufferedReader.readLine();
					String[] rawCredentialSplit = rawCredential.split("@");
					for (int i = 0; i < rawCredentialSplit.length; i++) {
						System.out.println(rawCredentialSplit[i]);
					}
				} catch (IOException e) {
					System.out.println("IOException");
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
			
			
			
	}

}
