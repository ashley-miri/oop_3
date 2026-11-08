import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ConsoleIO cio = new ConsoleIO();
        AnalysisReslt anl = new AnalysisReslt();
        while (true) {
            try {
                String path = cio.readPath(
                    "Введите путь к файлу(для выхода введите exit):"
                );
                if (path.equalsIgnoreCase("exit")) {
                    break;
                }
                Parser parser = Parser.forPath(path);
                if (parser == null) {
                    cio.outText(
                        "Формат не поддерживается, нужен .csv или .xml"
                    );
                    continue;
                }
                long start = System.nanoTime();
                List<ParserResult> records = parser.parse(path);
                anl.collect(records);
                long time = (System.nanoTime() - start) / 1000000;
                cio.outText("Время обработки: " + time + " мс");
                cio.browse(anl.getDuplicates());
                cio.browse(anl.getFloorsByCity());
            } catch (IOException e) {
                cio.outText("Ошибка работы с файлом: " + e.getMessage());
            } catch (RuntimeException e) {
                cio.outText("Некорректные данные или путь: " + e.getMessage());
            }
        }
    }
}
