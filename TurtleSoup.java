
package P2.turtle;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.*
public class TurtleSoup {

	public static void drawSquare(Turtle turtle, int sideLength) {
		turtle.draw();
		turtle.forward(sideLength);
		turtle.turn(90.00);
		turtle.forward(sideLength);
		turtle.turn(90.00);
		turtle.forward(sideLength);
		turtle.turn(90.00);
		turtle.forward(sideLength);
		turtle.turn(90.00);
	}

	public static double calculateRegularPolygonAngle(int sides) {
		return (sides - 2) * 180.00 / sides;
	}


	public static int calculatePolygonSidesFromAngle(double angle) {
		return (int) Math.round(360 / (180 - angle));
	}


	public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		turtle.draw();
		for (int i = 0; i < sides; i++) {
			turtle.forward(sideLength);
			turtle.turn(180.00 - calculateRegularPolygonAngle(sides));
		}
	}

	public static double calculateBearingToPoint(double currentBearing, double currentX, double currentY,
			double targetX, double targetY) {
		if (Math.abs(currentX - targetX) <= 0.0001) {
			if (Math.abs(currentY - targetY) <= 0.001)
				return 0;

			else if (currentY < targetY)
				return (360 - currentBearing) % 360;

			else
				return ((180 - currentBearing) + 360) % 360;
		} else if (currentX < targetX) {
			if (Math.abs(currentY - targetY) <= 0.001)
				return ((90 - currentBearing) + 360) % 360;
			else if (currentY < targetY)
				return ((Math.toDegrees(Math.atan((targetX - currentX) / (targetY - currentY))) - currentBearing) + 360)
						% 360;
			else
				return ((180 - Math.toDegrees(Math.atan((targetX - currentX) / (currentY - targetY))) - currentBearing)
						+ 360) % 360;
		} else {
			if (Math.abs(currentY - targetY) <= 0.001)
				return ((270 - currentBearing) + 360) % 360;
			else if (currentY < targetY)
				return ((360 - Math.toDegrees(Math.atan((currentX - targetX) / (targetY - currentY))) - currentBearing)
						+ 360) % 360;
			else
				return ((180 + Math.toDegrees(Math.atan((currentX - targetX) / (currentY - targetY))) - currentBearing)
						+ 360) % 360;
		}

	}

	public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY, int targetX,
			int targetY) {
		if (currentX == targetX) {
			if (currentY == targetY)
				return 0;

			else if (currentY < targetY)
				return (360 - currentBearing) % 360;

			else
				return ((180 - currentBearing) + 360) % 360;
		} else if (currentX < targetX) {
			if (currentY == targetY)
				return ((90 - currentBearing) + 360) % 360;
			else if (currentY < targetY)
				return ((Math.toDegrees(Math.atan((targetX - currentX) / (targetY - currentY))) - currentBearing) + 360)
						% 360;
			else
				return ((180 - Math.toDegrees(Math.atan((targetX - currentX) / (currentY - targetY))) - currentBearing)
						+ 360) % 360;
		} else {
			if (currentY == targetY)
				return ((270 - currentBearing) + 360) % 360;
			else if (currentY < targetY)
				return ((360 - Math.toDegrees(Math.atan((currentX - targetX) / (targetY - currentY))) - currentBearing)
						+ 360) % 360;
			else
				return ((180 + Math.toDegrees(Math.atan((currentX - targetX) / (currentY - targetY))) - currentBearing)
						+ 360) % 360;
		}



	public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
		ArrayList<Double> bearing = new ArrayList<Double>();
		double currentBearing = 0.00;
		for (int i = 0; i < xCoords.size() - 1; i++) {
			double angle = calculateBearingToPoint(currentBearing, xCoords.get(i), yCoords.get(i), xCoords.get(i + 1),
					yCoords.get(i + 1));
			currentBearing += angle;
			bearing.add(angle);
		}
		return bearing;

	}


	public static Set<Point> convexHull(Set<Point> points) {

		Point[] pointsArray = points.toArray(new Point[0]);
		Set<Point> outpoint = new HashSet<Point>();
		int lenth = points.size();
		if (lenth == 0)
			return outpoint;
		Point temp = pointsArray[0];
		Point flag;
		for (int i = 1; i < lenth; i++) {
			if (pointsArray[i].x() < temp.x()) {
				temp = pointsArray[i];
			}
		}
		outpoint.add(temp);
		Point first = temp;/
		double currentBearing = 0;
		if (lenth == 1)
			return outpoint;

		do {
			flag = pointsArray[0] == temp ? pointsArray[1] : pointsArray[0];
			for (int i = 0; i < lenth; i++) {
				if ((temp != pointsArray[i]) && calculateBearingToPoint(currentBearing, temp.x(), temp.y(),
						pointsArray[i].x(), pointsArray[i].y()) <= calculateBearingToPoint(currentBearing, temp.x(),
								temp.y(), flag.x(), flag.y())) {
					if (calculateBearingToPoint(currentBearing, temp.x(), temp.y(), pointsArray[i].x(),
							pointsArray[i].y()) == calculateBearingToPoint(currentBearing, temp.x(), temp.y(), flag.x(),
									flag.y())) {
						flag = ((pointsArray[i].x() - temp.x()) * (pointsArray[i].x() - temp.x())
								+ (pointsArray[i].y() - temp.y())
										* (pointsArray[i].y() - temp.y())) > ((flag.x() - temp.x())
												* (flag.x() - temp.x()) + (flag.y() - temp.y()) * (flag.y() - temp.y()))
														? pointsArray[i]
														: flag;
					} else
						flag = pointsArray[i];
				}
			}
			currentBearing += calculateBearingToPoint(currentBearing, temp.x(), temp.y(), flag.x(), flag.y());
			temp = flag;
			if (temp != first)
				outpoint.add(temp);
		} while (temp != first);
		return outpoint;
	}


	public static void drawPersonalArt(Turtle turtle) {
		turtle.color(PenColor.BLUE);
		turtle.draw();
		int sidelength = 5;
		for (int j = 0; j < 100; j++) {
			for (int i = 0; i < 4; i++) {
				turtle.forward(sidelength);
				turtle.turn(90);
			}
			sidelength += 5;
			turtle.turn(20);
		}
	}
		 drawPersonalArt(turtle);
	}

}
