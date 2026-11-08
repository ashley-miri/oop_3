import java.io.IOException;
import java.util.List;

public interface Parser {
    List<ParserResult> parse(String path) throws IOException;

    static Parser forPath(String path) {
        String lower = path.toLowerCase();
        if (lower.endsWith(".csv")) return new ParserCSV();
        if (lower.endsWith(".xml")) return new ParserXML();
        return null;
    }
}
