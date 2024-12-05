package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Integer;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class Day {
	private Day() {
	}

	public static void main(String[] args) throws Exception {
		try (Scanner sc = new Scanner(new File("./day5/input.txt"))) {
			// ruleLabel:
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				Pattern pattern = Pattern.compile("\\d{2}\\|\\d{2}");
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					String match = matcher.group();
					ruleList(match);
				}
				Pattern patternUpdate = Pattern.compile("\\d{2},");
				Matcher matcherUpdate = patternUpdate.matcher(line);
				if (matcherUpdate.find()) {
					List<Integer> update = getIncorrectU(updateList(line));
					if (!update.isEmpty()) {
						for (int i = 0; i < rules.size(); ++i) {
							int item0 = rules.get(i).get(0);
							int item1 = rules.get(i).get(1);
							int index0 = update.indexOf(item0);
							int index1 = update.indexOf(item1);
							if (index1 < index0 && index1 != -1) {
								update.add(index0 + 1, item1);
								update.remove(index1);
								i = -1;
							}
						}
						updatesList.add(update.get(update.size() / 2));
					}
				}
			}
			System.out.println(updatesList.stream().reduce(0, (a, b) -> a + b));
		}
	}

	public static List<Integer> getIncorrectU(List<Integer> update) {
		List<Integer> updatesIncorrect = new ArrayList<>();
		for (int i = 0; i < rules.size(); ++i) {
			int item0 = rules.get(i).get(0);
			int item1 = rules.get(i).get(1);
			int index0 = update.indexOf(item0);
			int index1 = update.indexOf(item1);
			if (index1 < index0 && index1 != -1) {
				return update;
			}
		}
		return updatesIncorrect;
	}

	public static List<List<Integer>> rules = new ArrayList<>();
	public static List<Integer> updatesList = new ArrayList<>();

	public static void ruleList(String line) {
		String[] rulesString = line.split("\\|");
		List<Integer> rule = Arrays
				.stream(rulesString).map((a) -> Integer.parseInt(a))
				.collect(Collectors.toList());
		rules.add(rule);
	}

	public static List<Integer> updateList(String line) {
		String[] updateString = line.split(",");
		List<Integer> update = Arrays
				.stream(updateString).map((a) -> Integer.parseInt(a))
				.collect(Collectors.toList());
		return update;
	}

}
