import java.io.IOException;

public interface Parser {
    void parse(String path, AnalysisReslt ana) throws IOException;

    static Parser forPath(String path) {
        String lower = path.toLowerCase();
        if (lower.endsWith(".csv")) return new ParserCSV();
        if (lower.endsWith(".xml")) return new ParserXML();
        return null;
    }
}
