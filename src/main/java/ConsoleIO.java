import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    private final Scanner scan = new Scanner(System.in);

    public void outText(String text) {
        System.out.print("\n" + text);
    }

    public int readInt(String promt) {
        while (true) {
            outText(promt);
            try {
                return Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                outText("Введи значение принадлежащее множеству Z");
            }
        }
    }

    public String readReqest(String promt) {
        while (true) {
            outText(promt);
            String str = scan.nextLine().trim();
            if (!str.isEmpty()) {
                return str;
            } else {
                outText("Строка пуста.");
            }
        }
    }

    public <T> T browse(List<T> list) {
        if (list.isEmpty()) {
            outText("Лист пуст");
            return null;
        }

        int page = 0;
        while (true) {
            for (int i = 1; i <= 5; i++) {
                if (5 * page + i - 1 > list.size() - 1) {
                    break;
                }
                outText(i + ") " + list.get(5 * page + i - 1));
            }
            outText("6)Предыдущая страница\n7)Следующая страница\n8)Выход");
            int r = readInt("Ввод: ");
            switch (r) {
                case 6:
                    if (page == 0) {
                        outText(
                            "Это первая страница, предыдущей не существует"
                        );
                        break;
                    }
                    page--;
                    break;
                case 7:
                    if ((list.size() + 4) / 5 == page + 1) {
                        outText(
                            "Это последняя страница, следующей не существует"
                        );
                        break;
                    }
                    page++;
                    break;
                case 8:
                    return null;
                default:
                    outText("Некорректное значение, повторите ввод");
                    break;
            }
        }
    }
}
