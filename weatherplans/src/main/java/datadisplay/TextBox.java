package datadisplay;

import java.util.ArrayList;

public class TextBox implements Box{
	private static TextContentFormatter tcf = new TextContentFormatter();
	private ArrayList<String> content;
	private int topBottomPadding, leftRightPadding;
	private int width, height;
	
	private Character[][] box;
	
	public TextBox(ArrayList<String> textContent, int topBottomPadding, int leftRightPadding) {
		this.content = new ArrayList<String>(textContent);
		this.topBottomPadding = topBottomPadding;
		this.leftRightPadding = leftRightPadding;
		
		this.box = this.constructTextBox();
		this.width = 2 * leftRightPadding + TextBox.getLongestStringLength(textContent);
		this.height = 2 * topBottomPadding + textContent.size();	
	}
	
	public String[] renderTextLeftAligned() {
		String[] block = TextBox.tcf.blockifyContent(this.content);
		String[] paddedBlock = TextBox.tcf.padBlock(block, topBottomPadding, leftRightPadding);
		return paddedBlock;
	}
	
	public String[] renderTextCenterAligned() {
		String[] block = TextBox.tcf.centerAlignContent(this.content);
		String[] paddedBlock = TextBox.tcf.padBlock(block, topBottomPadding, leftRightPadding);
		return paddedBlock;
	}

	private Character[][] constructTextBox() {
		int longestStringLength = TextBox.getLongestStringLength(content);
		int totalWidth = 2 * this.leftRightPadding + longestStringLength;
		int totalHeight = 2 * this.topBottomPadding + this.content.size();
		
		Character[][] textBox = new Character[totalHeight][totalWidth];
		fillTextBoxWithSpaces(textBox);
		
		int startingRowIdx = topBottomPadding;
		int startingColIdx = leftRightPadding;
		fillInTextBoxContent(textBox, startingRowIdx, startingColIdx);
		
		return textBox;
	}
	
//	private Character[][] constructTextBox() {
//		int longestStringLength = TextBox.getLongestStringLength(textContent);
//		int borderSize = 1;
//		int totalWidth = 2 * this.leftRightPadding + longestStringLength + 2 * borderSize;
//		int totalHeight = 2 * this.topBottomPadding + this.textContent.size() + 2 * borderSize;
//		
//		Character[][] textBox = new Character[totalHeight][totalWidth];
//		fillTextBoxWithSpaces(textBox);
//		fillInTextBoxBorder(textBox);
//		
//		int startingRowIdx = borderSize + topBottomPadding;
//		int startingColIdx = borderSize + leftRightPadding;
//		fillInTextBoxContent(textBox, startingRowIdx, startingColIdx);
//		
//		return textBox;
//	}
	
	public static void fillTextBoxWithSpaces(Character[][] textBox) {
		for (int i = 0; i < textBox.length; i++) {
			for (int j = 0; j < textBox[0].length; j++) {
				textBox[i][j] = ' ';
			}
		}
	}
	
	public static void fillInTextBoxBorder(Character[][] textBox) {
		final char cornerChar = '+';
		int numCols = textBox[0].length;
		int numRows = textBox.length;
		
		// Fill in corners clockwise, starting from the Top Left (TL).
//		textBox[0][0] = cornerChar;
//		textBox[0][numCols - 1] = cornerChar;
//		textBox[numRows - 1][numCols - 1] = cornerChar;
//		textBox[numRows - 1][0] = cornerChar;		
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
	
	private void fillInTextBoxContent(Character[][] textBox, int startingRowIdx, int startingColIdx) {
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
		StringBuilder sb = new StringBuilder();
		for (Character[] characters : box) {
			for (Character character : characters) {
				sb.append(character);
			}
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
		TextBox tb = new TextBox(lines, 1, 1);
		TextBox tb2 = new TextBox(lines2, 1, 1);
		children.add(tb);
		children.add(tb2);
		
		ParentBox parent = new ParentBox(children, 0, 0);
//		System.out.println(parent);		
		
	}

}
