package sample;

import java.util.ArrayList;
import java.util.List;

public class AllResults {
    public List<Results> allresults;

    public AllResults() {
        allresults = new ArrayList<>();
    }

    @Override
    public String toString() {
        String str = "All Results:\n{\n";
        for (int i = 0; i < allresults.size(); i++) {
            str += allresults.get(i).toString()
                    + "\n";
        }
        str += "}";
        return str;
    }
    // todo html toString
}
