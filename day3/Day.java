package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.lang.Integer;

public final class Day {
	private Day() {
	}

	public static void main(String[] args) throws Exception {
		Path path = Paths.get("./day3/test.txt");
		String contents = Files.readString(path, StandardCharsets.UTF_8);
		String filteredContents = filterInstructions(contents);

		List<String> stringList = parseString(filteredContents);
		List<Integer> intList = parseInt(stringList);
		int sum = intList.stream().reduce(0, (a, b) -> a + b);

		System.out.println(sum);
	}

	private static List<Integer> parseInt(List<String> stringList) {
		List<Integer> intList = new ArrayList<>();
		for (String string : stringList) {
			String[] stringArr = string.split(",");
			int num0 = Integer.parseInt(stringArr[0].substring(4));
			int num1 = Integer.parseInt(stringArr[1].substring(0, stringArr[1].indexOf(")")));
			intList.add(num0 * num1);
		}
		return intList;
	}

	private static List<String> parseString(String string) {
		List<String> allMatches = new ArrayList<>();
		Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			allMatches.add(matcher.group());
		}

		return allMatches;
	}

	private static String filterInstructions(String input) {
		// This regex captures all enabled `mul(...)` sections
		Pattern pattern = Pattern.compile("(do\\(\\)|don't\\()|(mul\\(\\d{1,3},\\d{1,3}\\))");
		Matcher matcher = pattern.matcher(input);

		StringBuilder filtered = new StringBuilder();
		boolean mulEnabled = true;

		while (matcher.find()) {
			String match = matcher.group();

			if ("do()".equals(match)) {
				mulEnabled = true; // Enable `mul`
			} else if ("don't()".equals(match)) {
				mulEnabled = false; // Disable `mul`
			} else if (mulEnabled && match.startsWith("mul(")) {
				filtered.append(match).append(" "); // Append `mul(...)` if enabled
			}
		}

		return filtered.toString().trim();
	}

}
