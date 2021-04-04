package datadisplay;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class TextContentFormatter {
	/**
	 * Creates a list of equal length strings by appending spaces to the shorter strings.
	 * Equivalent to a left-justify.
	 * @param content - The underlying text we are trying to display. (Meaning no padding)
	 * @return
	 */
	public String[] blockifyContent(ArrayList<String> content) {
		int longestStringLength = TextContentFormatter.getLongestStringLength(content);
		String[] blockedText = new String[content.size()];
		StringBuilder sb = new StringBuilder();
		
		// Construct each blocked line and add to blockedText
		int blockedTextIdx = 0;
		for (String line : content) {
			sb.append(line);
			while (sb.length() < longestStringLength) {
				sb.append(' ');
			}
			blockedText[blockedTextIdx++] = sb.toString();
			sb.setLength(0);
		}
		return blockedText;
	}
	
	public String[] centerAlignContent(ArrayList<String> content) {
		int longestStringLength = TextContentFormatter.getLongestStringLength(content);
		String[] centerAlignedText = new String[longestStringLength];
		int blockedTextIdx = 0;
		for (String line : content) {
			String centeredLine = StringUtils.center(line, longestStringLength);
			centerAlignedText[blockedTextIdx++] = centeredLine;
		}
		return centerAlignedText;
	}
	
	/**
	 * 
	 * @param block - an array of equal length strings, produced by blockifyContent() or centerAlignContent()
	 * @param topBottomPadding
	 * @param leftRightPadding
	 * @return
	 */
	public String[] padBlock(String[] block, int topBottomPadding, int leftRightPadding) {
		String[] paddedBlock = new String[block.length + 2 * topBottomPadding];
		
		// Vertical Padding - add blank lines to top and bottom
		String blankLine = "";
		for (int i = 0; i < leftRightPadding * 2 + block[0].length(); i++) {
			blankLine += " ";
		}
		for (int i = 0; i < topBottomPadding; i++) {
			paddedBlock[i] = blankLine;
			paddedBlock[paddedBlock.length - i - 1] = blankLine;
		}
		
		// Horizontal Padding - Add left and right padding to each string
		String horizontalPadding = "";
		for (int i = 0; i < leftRightPadding; i++) {
			horizontalPadding += " ";
		}
		int paddedContentRowIdx = topBottomPadding;
		for (String line : block) {
			line = horizontalPadding + line + horizontalPadding;
			paddedBlock[paddedContentRowIdx++] = line;
		}
		return paddedBlock;
	}
	
	private static int getLongestStringLength(ArrayList<String> strings) {
		int max = strings.get(0).length();
		for (String string : strings) {
			if (string.length() > max) {
				max = string.length();
			}
		}
		return max;
	}
	
	public static void printStrings(String[] block) {
		for (String string : block) {
			System.out.println(string);
		}
	}
	
	public static void main(String[] args) {

	}
}
