import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class ParserXML implements Parser {

    public void parse(String path, AnalysisReslt ana) throws IOException {
        try (InputStream in = Files.newInputStream(Path.of(path))) {
            XMLStreamReader reader =
                XMLInputFactory.newInstance().createXMLStreamReader(in);

            while (reader.hasNext()) {
                int event = reader.next();
                if (
                    event == XMLStreamConstants.START_ELEMENT &&
                    reader.getLocalName().equals("item")
                ) {
                    ParserResult res = new ParserResult();
                    res.setCity(reader.getAttributeValue(null, "city"));
                    res.setStreet(reader.getAttributeValue(null, "street"));
                    res.setHouse(reader.getAttributeValue(null, "house"));
                    res.setFloor(
                        Integer.parseInt(
                            reader.getAttributeValue(null, "floor")
                        )
                    );
                    ana.collect(res);
                }
            }
        } catch (XMLStreamException e) {
            throw new IOException("Ошибка разбора XML", e);
        }
    }
}
