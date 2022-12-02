import com.example.homeshare.Listing;

import java.util.Comparator;

public class SortByNumBeds implements Comparator<Listing> {
    // Sorting in ascending order of distance (shortest to longest distance)
    public int compare(Listing a, Listing b)
    {
        return Double.compare(a.getNumBeds(), b.getNumBeds());
    }
}