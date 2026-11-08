import java.io.IOException;
import java.util.List;

public interface Parser {
    List<ParserResult> parse(String path) throws IOException;
}
