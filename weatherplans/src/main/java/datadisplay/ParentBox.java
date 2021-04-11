package datadisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

public class ParentBox implements Box {
	public List<Box> children;
	private int width, height;
	private int topBottomPadding, leftRightPadding;
	private int innerPadding = 1;
	private static int borderSize = 1;
	private boolean hasBorder;
	
	public ParentBox(List<Box> boxes, int topBottomPadding, int leftRightPadding) {
		this.children = new ArrayList<Box>(boxes);
		this.topBottomPadding = topBottomPadding;
		this.leftRightPadding = leftRightPadding;
		this.width = 2 * this.leftRightPadding + getLargestChildWidth();
		
		this.height = 2 * this.topBottomPadding;
		for (Box box : this.children) {
			this.height += box.getHeight();
		}
		this.height += innerPadding * (this.children.size() - 1);
		
		if (this.hasBorder) {
			this.width += 2 * borderSize;
			this.height += 2 * borderSize;
		}
	}
	
	@Override
	public String[] getDisplayBlock() {
		int largestChildWidth = getLargestChildWidth();
		ArrayList<String[]> blocks = new ArrayList<String[]>();
		for (Box box : this.children) {
			String[] block = box.getDisplayBlock();
			for (int i = 0; i < block.length; i++) {
				String line = block[i];
				String centeredLine = StringUtils.center(line, largestChildWidth);
				block[i] = centeredLine;
			}
			blocks.add(block);
		}
		
		ArrayList<String> result = new ArrayList<String>();
		for (String[] block : blocks) {
			for (String line : block) {
				result.add(line);
			}
			for (int i = 0; i < this.innerPadding; i++) {
				result.add(createPaddingString(largestChildWidth));
			}
		}
		result.remove(result.size() - 1);
		
		String[] actualResult = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			String s = result.get(i);
			actualResult[i] = s;
		}
		return actualResult;
	}
	
	private String createPaddingString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	private String createHorizontalBorderString(int length) {
		StringBuilder sb = new StringBuilder("+");
		for (int i = 1; i < length - 1; i++) {
			sb.append("-");
		}
		sb.append("+");
		return sb.toString();
	}
	
	private int getLargestChildWidth() {
		int largestChildWidth = 0;
		for (Box box : this.children) {
			if (box.getWidth() > largestChildWidth) {
				largestChildWidth = box.getWidth();
			}
		}
		return largestChildWidth;
	}
	
	private int getLargestChildHeight() {
		int largestChildHeight = 0;
		for (Box box : this.children) {
			if (box.getHeight() > largestChildHeight) {
				largestChildHeight = box.getHeight();
			}
		}
		return largestChildHeight;
	}
	
	public String toString() {
		String[] displayBlock = getDisplayBlock();
		
		String joinDelimiter = "";
		for (int i = 0; i < this.innerPadding; i++) {
			joinDelimiter += "\n";
		}
		
		StringJoiner output = new StringJoiner(joinDelimiter);
		for (String line: displayBlock) {
			output.add(line);
		}
		return output.toString();
	}

	@Override
	public int getTopBottomPadding() {
		return this.topBottomPadding;
	}

	@Override
	public int getLeftRightPadding() {
		return this.leftRightPadding;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
}
