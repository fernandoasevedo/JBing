import java.util.Scanner;

import bing.translate.BingTranslate;
import bing.util.Auth;

public class TranslateTest {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite a palavra ser traduzida: ");
		String word = scan.nextLine();

		BingTranslate bingTranslate = new BingTranslate();

		String translates[] = null;
		try {
			
			String clientId = "";
			String clientSecret = "";
			//valor padrão
			String scope = "http://api.microsofttranslator.com";
			
			String acess = Auth.accessToken(clientId, clientSecret, scope );
			
			translates = bingTranslate.getTranslations(word, "pt", "en", acess);

			if (translates == null) {
				System.out.println("Não foram encontradas traduções de " + word	+ "!");
			} else {
				System.out.println("Traduções de " + word + ":");
				for (String t : translates)
					System.out.println("\t" + t);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
