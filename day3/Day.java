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
		boolean mulEnabled = true; // Initially, mul is enabled
		StringBuilder filtered = new StringBuilder();

		// Regex patterns for instructions
		Pattern mulPattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");

		int i = 0;
		while (i < input.length()) {
			// Check for `do()` and `don't()`
			if (input.startsWith("do()", i)) {
				mulEnabled = true;
				i += "do()".length();
			} else if (input.startsWith("don't()", i)) {
				mulEnabled = false;
				i += "don't()".length();
			}
			// Append `mul(...)` only if enabled
			else if (input.startsWith("mul(", i)) {
				Matcher matcher = mulPattern.matcher(input.substring(i));
				if (matcher.find() && mulEnabled) {
					filtered.append(matcher.group()).append(" ");
					i += matcher.end();
				} else {
					i++; // Move forward if no match or not enabled
				}
			} else {
				i++; // Move forward for any other characters
			}
		}

		return filtered.toString();
	}

}
