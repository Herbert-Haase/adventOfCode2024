package day1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public final class SimilarityScore {
	private SimilarityScore() {
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
		ArrayList<Integer> similarScore = new ArrayList<>();
		int sum = column0.stream()
				.mapToInt(num0 -> num0
						* (int) column1.stream().filter(num1 -> num1.equals(num0)).count())
				.sum();
		// for (int num0 : column0) {
		// int score = 0;
		// for (int num1 : column1) {
		// score += num0 == num1 ? 1 : 0;
		// }
		// similarScore.add(score * num0);
		// }
		// int sum = similarScore.stream().reduce(0, (e1, e2) -> e1 + e2);

		System.out.println(sum);
	}
}
