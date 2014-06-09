
package search.graph;

import java.util.Comparator;

/**
 * Vergleicht die zurueckgelegten Wege + Luftlinie zum Ziel.
 */
class AStarComparator implements Comparator<Path> {
    private Node goal = null;

    public int compare(Path p1, Path p2) {
        if (goal == null) goal = p1.goalNode();
        double dist = p1.pathLength() - p2.pathLength()
            + p1.lastNode().distTo(goal)
            - p2.lastNode().distTo(goal);
        return (dist < 0)? -1: (dist == 0)? 0: +1;  
    }
}
