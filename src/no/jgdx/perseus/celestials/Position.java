package no.jgdx.perseus.celestials;

/**
 * Defines an (x,y,z)-coordinate in space.
 * 
 * @author jonas
 * 
 */
public class Position {

	private final long x;

	private final long y;

	private final long z;

	public final static Position ORIGIN = new Position(0, 0, 0);

	public Position(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public long getX() {
		return x;
	}

	public long getY() {
		return y;
	}

	public long getZ() {
		return z;
	}

	public Position add(Position p) {
		return new Position(x + p.x, y + p.y, z + p.z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (x ^ (x >>> 32));
		result = prime * result + (int) (y ^ (y >>> 32));
		result = prime * result + (int) (z ^ (z >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + x + ",\t" + y + ",\t" + z + ")";
	}
}