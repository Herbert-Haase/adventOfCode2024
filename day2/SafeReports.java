package day2;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public final class SafeReports {
	private SafeReports() {
	}

	public static void main(String[] args) {
		int safeAmount = 0;
		try (Scanner sc = new Scanner(new File("./day2/input.txt"))) {
			while (sc.hasNextLine()) {
				String[] report = sc.nextLine().trim().split("\\s+");
				boolean increasing = Integer.parseInt(report[0]) - Integer.parseInt(report[1]) < 0
						? true
						: false;
				boolean loopBreak = false;
				for (int i = 0; i < report.length - 1; i++) {
					int level0 = Integer.parseInt(report[i]);
					int level1 = Integer.parseInt(report[i + 1]);
					int diff = Math.abs(level0 - level1);
					if (((level0 - level1 < 0) == increasing) && ((diff > 0) && (diff < 4))) {
					} else {
						loopBreak = true;
						break;
					}
				}
				if (!loopBreak) {
					++safeAmount;
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println(safeAmount);
	}
}
