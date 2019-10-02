import java.util.ArrayList;
import java.util.Random;

public class Universe {
	private Region[] regionList;

	public Universe() {
		regionList = generateRegionList();
	}

	private Region[] generateRegionList() {
		Region[] returnedList = new Region[9];

		// Generate coordinate hashset
		int count = 0;
		ArrayList<String> coords = new ArrayList<>();
		for (int y = 0; y <= 400; y++){
			for (int x = 0; x <= 400; x++) {
				coords.add(y + "," + x);
			}
		}

		// Pull coords from hashset
		Random gen = new Random();
		for (int i = 0; i < 9; i++) {
			int randPos = gen.nextInt(coords.size());

			// Get target
			String coord = coords.get(randPos);
			String[] coordXY = coord.split(",");
			int coordY = Integer.parseInt(coordXY[0]);
			int coordX = Integer.parseInt(coordXY[1]);
			// Delete surrounding coords
			for (int y = -2; y <= 2; y++) {
				for (int x = -2; x <= 2; x++) {
					int removeCoordX = coordX + x;
					int removeCoordY = coordY + y;

					// Remove if in bounds
					boolean xInRange = removeCoordX >= 0 && removeCoordX < 400;
					boolean yInRange = removeCoordY >= 0 && removeCoordY < 400;
					if (xInRange && yInRange) {
						String removeCoord = removeCoordY + "," + removeCoordX;
						coords.remove(removeCoord);
					}
				}
			}

			String regionName = RegionNames.values()[i].toString();
			returnedList[i] = new Region(regionName, coordX - 200, coordY - 200);
		}

		return returnedList;
	}
}
