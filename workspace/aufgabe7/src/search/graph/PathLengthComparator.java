package search.graph;

import java.util.Comparator;

/**
 * Vergleicht die zurueckgelegten Wege.
 */
class PathLengthComparator implements Comparator<Path> {

    public int compare(Path p1, Path p2) {
        double dist = p1.pathLength() - p2.pathLength();
        return (dist < 0)? -1: (dist == 0)? 0: +1;  
    }
}
