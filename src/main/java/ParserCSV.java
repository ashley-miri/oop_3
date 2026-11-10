import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ParserCSV implements Parser {

    public void parse(String path, AnalysisReslt ana) throws IOException {
        try (
            BufferedReader reader = Files.newBufferedReader(
                Path.of(path),
                StandardCharsets.UTF_8
            )
        ) {
            String line = reader.readLine();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                line = line.replace("\"", "");
                String[] part = line.split(";");
                ParserResult res = new ParserResult();
                res.setCity(part[0]);
                res.setStreet(part[1]);
                res.setHouse(part[2]);
                res.setFloor(Integer.parseInt(part[3]));
                ana.collect(res);
            }
        }
    }
}
