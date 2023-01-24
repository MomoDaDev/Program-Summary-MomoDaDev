package sample;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Results {
    private int number;

    private List<int[]> numbers;

    public Results(int number, List<int[]> numbers) {
        this.number = number;
        this.numbers = numbers;
    }

    public List<int[]> getNumbers() {
        return numbers;
    }

    public void addNumbers(int i1, int i2, int i3, int i4){
        numbers.add(new int[] { i1, i2, i3, i4 });
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        String str = "Results for number " + number + ": { ";
        for (int i = 0; i < numbers.size(); i++) {
            str += "[" + IntStream.of(numbers.get(i))
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(", ")) + "] ";
        }
        str += "}";
        return str;
    }

    // todo html toString
}
