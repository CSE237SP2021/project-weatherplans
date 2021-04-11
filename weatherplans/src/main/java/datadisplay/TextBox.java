package datadisplay;

import java.util.ArrayList;

public class TextBox implements Box{
	private static int borderSize = 1;
	private static TextContentFormatter tcf = new TextContentFormatter();
	private ArrayList<String> content;
	private int topBottomPadding, leftRightPadding;
	private int width, height;
	private boolean hasBorder;
	private TextAlignment alignment;
	
	// Border break center alignment currently :(
	public TextBox(ArrayList<String> textContent, int topBottomPadding, int leftRightPadding, boolean hasBorder) {
		this.content = new ArrayList<String>(textContent);
		this.topBottomPadding = topBottomPadding;
		this.leftRightPadding = leftRightPadding;
		this.hasBorder = hasBorder;
		this.alignment = TextAlignment.LEFT_ALIGNED;
		
		this.width = 2 * leftRightPadding + TextBox.getLongestStringLength(textContent);
		this.height = 2 * topBottomPadding + textContent.size();
		if (this.hasBorder) {
			this.width += 2 * borderSize;
			this.height += 2 * borderSize;
		}
	}
	
	private String[] getPaddedLeftAlignedBlock() {
		String[] block = TextBox.tcf.blockifyContent(this.content);
		String[] paddedBlock = TextBox.tcf.padBlock(block, topBottomPadding, leftRightPadding);
		return paddedBlock;
	}
	
	private String[] getPaddedCenterAlignedBlock() {
		String[] block = TextBox.tcf.centerAlignContent(this.content);
		String[] paddedBlock = TextBox.tcf.padBlock(block, topBottomPadding, leftRightPadding);
		return paddedBlock;
	}
	
	public String[] getDisplayBlock() {
		String[] block = null;
		if (this.alignment == TextAlignment.LEFT_ALIGNED) {
			block = getPaddedLeftAlignedBlock();
		} else if (this.alignment == TextAlignment.CENTER_ALIGNED) {
			block = getPaddedCenterAlignedBlock();
		} else {
			throw new RuntimeException("Unrecognized Text Alignment");
		}
		
		char[][] canvas = constructTextBox(block);
		
		String[] result = new String[canvas.length];
		for (int i = 0; i < canvas.length; i++) {
			char[] chars = canvas[i];
			result[i] = new String(chars);
		}
		return result;
	}
	
	private char[][] constructTextBox(String[] block) {
		int totalWidth = block[0].length();
		int totalHeight = block.length;
		
		if (this.hasBorder) {
			totalWidth += 2 * borderSize;
			totalHeight += 2 * borderSize;
		}
		
		char[][] textBox = new char[totalHeight][totalWidth];
		fillTextBoxWithSpaces(textBox);
		
		if (this.hasBorder) {
			fillInTextBoxBorder(textBox);
		}
		
		int startingRowIdx = this.topBottomPadding;
		int startingColIdx = this.leftRightPadding;
		
		if (this.hasBorder) {
			startingRowIdx += borderSize;
			startingColIdx += borderSize;
		}
		
		fillInTextBoxContent(textBox, startingRowIdx, startingColIdx);
		
		return textBox;
	}
	
	public static void fillTextBoxWithSpaces(char[][] textBox) {
		for (int i = 0; i < textBox.length; i++) {
			for (int j = 0; j < textBox[0].length; j++) {
				textBox[i][j] = ' ';
			}
		}
	}
	
	public static void fillInTextBoxBorder(char[][] textBox) {
		int numCols = textBox[0].length;
		int numRows = textBox.length;
		
		// Fill in corners clockwise, starting from the Top Left (TL).
		textBox[0][0] = '┌';
		textBox[0][numCols - 1] ='┐';
		textBox[numRows - 1][numCols - 1] = '┘';
		textBox[numRows - 1][0] = '└';
		
		// Fill in the top and bottom border with '-'
		final char horizontalChar = '─';
		for (int i = 1; i < numCols - 1; i++) {
			textBox[0][i] = horizontalChar;					// Top border
			textBox[numRows - 1][i] = horizontalChar;		// Bottom border
		}
		
		// Fill in the left and right border with '|'
		final char verticalChar = '│';
		for (int i = 1; i < numRows - 1; i++) {
			textBox[i][0] = verticalChar;					// Left border
			textBox[i][numCols - 1] = verticalChar;			// Right border
		}
	}
	
	private void fillInTextBoxContent(char[][] textBox, int startingRowIdx, int startingColIdx) {
		int rowIdx = startingRowIdx;
		int colIdx = startingColIdx;
		
		for (String line : this.content) {
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				textBox[rowIdx][colIdx + i] = c;
			}
			rowIdx++;
		}
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
	
	public String toString() {
		String[] block = this.getDisplayBlock();
		StringBuilder sb = new StringBuilder();
		for (String line : block) {
			sb.append(line);
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	public int getTopBottomPadding() {
		return topBottomPadding;
	}

	@Override
	public int getLeftRightPadding() {
		return leftRightPadding;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	public static void main(String[] args) {		
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("Hello");
		lines.add("bruh");
		lines.add("Yes sir!");
		
		ArrayList<String> lines2 = new ArrayList<String>();
		lines2.add("Hello");
		lines2.add("bruh");
		lines2.add("Yes sir!!!!!!!!!!!!");
		
		ArrayList<Box> children = new ArrayList<Box>();
		TextBox tb = new TextBox(lines, 1, 1, true);
		TextBox tb2 = new TextBox(lines2, 1, 1, true);
		children.add(tb);
		children.add(tb2);
		
		ParentBox parent = new ParentBox(children, 0, 0);
		System.out.println(parent);		
		
	}

}
