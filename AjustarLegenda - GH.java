package estudo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AjustarLegenda {

	public String legenda;
	public FileReader reader;
	public FileWriter writer;
	public LocalTime tempo;
	public BufferedReader leitor;
	public BufferedWriter escritor;
	public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
	public String line;
	
	public void AbreLegenda() throws FileNotFoundException {
		//Escreva o endereço do arquivo srt
		reader = new FileReader("D:\\.srt");
		leitor = new BufferedReader (reader);
	}
	
	public void ConsertaLegenda() throws IOException {
		//escreva o endereço do arquivo srt
		writer = new FileWriter("D:\\.srt", StandardCharsets.UTF_8);
		escritor = new BufferedWriter (writer);
		line = leitor.readLine();
		
		
		while(line != null) {

			if (line.contains("-->")) {
				
				String[] timesplit = line.split(" --> ");
				String primeirotempo = timesplit[0];
				String segundotempo = timesplit[1];
				LocalTime tempo1 = LocalTime.parse(primeirotempo, dtf);
				LocalTime tempo2 = LocalTime.parse(segundotempo, dtf);
				tempo1 = tempo1.minusSeconds(5);
				tempo2 = tempo2.minusSeconds(5);
				
				escritor.write(tempo1.toString().replace(".", ","));	
				escritor.write(" --> ");
				escritor.write(tempo2.toString().replace(".", ","));
				
			}else {
				
			escritor.write(line);
				
			}
			escritor.write("\n");
			escritor.flush();
					
			line = leitor.readLine();
		}
			
	}
		
		
}
