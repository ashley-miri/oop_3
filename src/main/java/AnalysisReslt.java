import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisReslt {

    private static final int FLOOR_CONST = 5;

    private final Map<ParserResult, Integer> duplicates = new HashMap<>();
    private final Map<String, int[]> floorsByCity = new HashMap<>();

    public void collect(List<ParserResult> records) {
        duplicates.clear();
        floorsByCity.clear();
        for (ParserResult r : records) {
            int res = duplicates.merge(r, 1, (old, one) -> old + 1);
            if (res == 1 && r.getFloor() <= FLOOR_CONST && r.getFloor() > 0) {
                int[] counts = floorsByCity.computeIfAbsent(
                    r.getCity(),
                    k -> new int[FLOOR_CONST]
                );
                counts[r.getFloor() - 1]++;
            }
        }
    }
}
