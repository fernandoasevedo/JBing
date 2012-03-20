package bing.translate;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import bing.util.Auth;

public class BingTranslate {
	
	public String[] getTranslations( String word , String from, String to, String accesToken ) throws IOException, JDOMException{
		
		//Segundo a documentação do bing, a palavra Bearer deve ser inserida no token de acesso
		accesToken = "Bearer " + accesToken;
		
		URL url = new URL(
				"http://api.microsofttranslator.com/v2/Http.svc/GetTranslations?"
				+ "&" + "text=" + URLEncoder.encode( word, "UTF-8")
				+ "&" + "from=" + from + "&" + "to=" + to  
				+ "&" +  "maxTranslations=5");
	
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//Adicionando dados de cabeçalho
		conn.setRequestProperty("Authorization", accesToken);
		conn.setRequestProperty("Content-Type", "text/plain");
		conn.setRequestMethod("POST");
		//Faz com que a saída seja gerada
		conn.setDoOutput(true);
		
		//Envia os dados
		OutputStreamWriter writer = new OutputStreamWriter( conn.getOutputStream() );
		writer.flush();
		
		
		//Fazendo a leitura do xml retornado		
		Document document = null;				
		//construtor do documento
		SAXBuilder docBuilder = new SAXBuilder();
		document = docBuilder.build( conn.getInputStream() );
		
		//Pegando os elementos do documento
		Element root = document.getRootElement();
		Element t = root.getChild("Translations", root.getNamespace() );
		List<Element> elements = t.getChildren();
		String translates[] = new String[ elements.size() ];
		int index = 0;
		for( Element e : elements ){
			//System.out.println( e );
			translates[ index ] = e.getChildText("TranslatedText" , e.getNamespace());
			index++;
		}		
		
		writer.close();		
		return translates;
	}
}
