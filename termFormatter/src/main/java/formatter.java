import java.io.FileNotFoundException;
import java.io.FileReader;
import com.opencsv.CSVReader;

/**
 * Created by christophermaness on 10/6/16.
 *
 * Several assumptions made here:
 * replaced all spaces with _
 * removed all commas
 * replaced all & with actual and
 */
public class formatter {

    public static void main(String[] args) {
        CSVReader reader = null;
        int fieldNameColumnIndex = -1;
        int additionalTermsColumnIndex = -1;

        try {
            reader = new CSVReader(new FileReader("/Users/christophermaness/deeplearning4j/acc_terms.csv"));
            String [] nextLine;

            if((nextLine = reader.readNext()) != null){
                for (int i = 0; i < nextLine.length; i++){
                    if(nextLine[i].equals("Field Name")){
                        fieldNameColumnIndex = i;
                    } else if (nextLine[i].equals("Additional Names")){
                        additionalTermsColumnIndex = i;
                    }
                }
            } else {
                throw new Exception("No inputs for file");
            }

            if(fieldNameColumnIndex == -1 || additionalTermsColumnIndex == -1){
                throw new Exception("Nessecary Columns Not Found");
            }

            while ((nextLine = reader.readNext()) != null) {
                String term = nextLine[fieldNameColumnIndex].toLowerCase();
                term = term.replace(',', ' ').replace(' ', '_').replace("&", "and").replace("__", "_");
                String additional = nextLine[additionalTermsColumnIndex].toLowerCase().replace('&',' ');

                String[] additionalTerms = additional.split(",");
                for (String s: additionalTerms) {
                    s = s.trim();
                    s = s.replace(' ', '_');
                    System.out.println(term + " " + s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
