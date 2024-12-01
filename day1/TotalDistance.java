package day1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public final class TotalDistance {
	private TotalDistance() {
	}

	public static void main(String[] args) {
		ArrayList<Integer> column0 = new ArrayList<>();
		ArrayList<Integer> column1 = new ArrayList<>();
		try (Scanner sc = new Scanner(new File("./day1/input.txt"))) {
			while (sc.hasNextLine()) {
				String[] numbers = sc.nextLine().trim().split("\\s+");
				if (numbers.length == 2) {
					column0.add(Integer.parseInt(numbers[0]));
					column1.add(Integer.parseInt(numbers[1]));
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
		Collections.sort(column0);
		Collections.sort(column1);
		int totalDiff = IntStream.range(0, column0.size())
				.map(i -> Math.abs(column0.get(i) - column1.get(i)))
				.sum();

		System.out.println(totalDiff);
	}
}
