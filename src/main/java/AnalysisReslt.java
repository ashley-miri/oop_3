import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisReslt {

    ConsoleIO cio = new ConsoleIO();

    private static final int FLOOR_CONST = 5;

    private final Map<ParserResult, Integer> duplicates = new HashMap<>();
    private final Map<String, int[]> floorsByCity = new HashMap<>();

    public void clear() {
        duplicates.clear();
        floorsByCity.clear();
    }

    public void collect(ParserResult r) {
        int res = duplicates.merge(r, 1, (old, one) -> old + 1);
        if (res == 1 && r.getFloor() <= FLOOR_CONST && r.getFloor() > 0) {
            int[] counts = floorsByCity.computeIfAbsent(
                r.getCity(),
                k -> new int[FLOOR_CONST]
            );
            counts[r.getFloor() - 1]++;
        }
    }

    public List<String> getDuplicates() {
        List<String> lines = new ArrayList<>();
        for (Map.Entry<ParserResult, Integer> e : duplicates.entrySet()) {
            int count = e.getValue();
            if (count > 1) {
                ParserResult r = e.getKey();
                lines.add(
                    r.getCity() +
                        ", " +
                        r.getStreet() +
                        ", д. " +
                        r.getHouse() +
                        ", " +
                        r.getFloor() +
                        " эт. — " +
                        count +
                        " раз(а)"
                );
            }
        }
        return lines;
    }

    public List<String> getFloorsByCity() {
        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, int[]> e : floorsByCity.entrySet()) {
            int[] counts = e.getValue();
            StringBuilder sb = new StringBuilder(e.getKey()).append(": ");
            for (int i = 0; i < counts.length; i++) {
                sb.append(i + 1)
                    .append("-эт: ")
                    .append(counts[i])
                    .append("  ");
            }
            lines.add(sb.toString());
        }
        return lines;
    }
}
