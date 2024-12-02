package day2;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SafeReports {
	private SafeReports() {
	}

	public static void main(String[] args) {
		int safeAmount = 0;
		try (Scanner sc = new Scanner(new File("./day2/input.txt"))) {
			while (sc.hasNextLine()) {
				String reportString = sc.nextLine().trim();

				List<Integer> report = parseReport(reportString);

				if (isSafe(report) || canBeMadeSafe(report)) {
					safeAmount++;
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println(safeAmount);
	}

	private static List<Integer> parseReport(String reportString) {
		List<Integer> report = new ArrayList<>();
		for (String num : reportString.split("\\s+")) {
			report.add(Integer.parseInt(num));
		}
		return report;
	}

	private static boolean isSafe(List<Integer> report) {
		boolean increasing = report.get(0) < report.get(1);
		for (int i = 0; i < report.size() - 1; i++) {
			int diff = Math.abs(report.get(i) - report.get(i + 1));
			if ((report.get(i + 1) > report.get(i)) != increasing || diff < 1 || diff > 3) {
				return false;
			}
		}
		return true;
	}

	private static boolean canBeMadeSafe(List<Integer> report) {
		for (int i = 0; i < report.size(); i++) {
			List<Integer> modifiedReport = new ArrayList<>(report);
			modifiedReport.remove(i);
			if (isSafe(modifiedReport)) {
				return true;
			}
		}
		return false;
	}

}
