package shapes;

import java.util.Comparator;

public final class ShapesAlphabetisch implements Comparator<IShape> {
	@Override
	public int compare(IShape a, IShape b)
	{
		return a.getName().compareTo(b.getName());
	}
}
