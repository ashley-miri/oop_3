import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParserCSV implements Parser {

    public List<ParserResult> parse(String path) throws IOException {
        List<ParserResult> result = new ArrayList<>();

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
                result.add(res);
            }
        }
        return result;
    }
}
