import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ConsoleIO cio = new ConsoleIO();
        AnalysisReslt anl = new AnalysisReslt();
        while (true) {
            try {
                anl.clear();
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
                parser.parse(path, anl);
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
