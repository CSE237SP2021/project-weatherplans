package datadisplay;


import org.apache.commons.lang3.StringUtils;

public abstract class Box {
	protected static final int BORDER_SIZE = 1;
	
	protected int topBottomPadding, leftRightPadding;
	protected boolean hasBorder;
	protected int width;		// The number of characters per line of the display block
	protected int height;		// The number of lines of the display block
	
	public Box(int topBottomPadding, int leftRightPadding, boolean hasBorder) {
		this.topBottomPadding = topBottomPadding;
		this.leftRightPadding = leftRightPadding;
		this.hasBorder = hasBorder;
		
		/**
		 * I do not use
		 * 		this.width = calculateWidth();
		 * 		this.height = calculateHeight();
		 * to allow subclasses to use their own fields when using calculateWidth()/calculateHeight().
		 */
	}
	
	protected abstract int calculateWidth();
	protected abstract int calculateHeight();
	protected abstract String[] toAlignedBlock();
	
	protected String[] toPaddedBlock(String[] block) {
		String[] paddedBlock = new String[block.length + 2 * topBottomPadding];
		
		// Vertical Padding - add blank lines to top and bottom
		int paddedLineLength = leftRightPadding * 2 + block[0].length();
		String blankLine = StringUtils.repeat(" ", paddedLineLength);
		for (int i = 0; i < topBottomPadding; i++) {
			paddedBlock[i] = blankLine;
			paddedBlock[paddedBlock.length - i - 1] = blankLine;
		}
		
		// Horizontal Padding - Add left and right padding to each line
		String horizontalPadding = StringUtils.repeat(" ", leftRightPadding);
		int paddedBlockLineNum = topBottomPadding;
		for (String line : block) {
			line = horizontalPadding + line + horizontalPadding;
			paddedBlock[paddedBlockLineNum++] = line;
		}
		
		return paddedBlock;
	}
	
	protected String[] toBorderedBlock(String[] block) {
		// Add the top and bottom borders
		String[] borderedBlock = new String[block.length + 2];
		borderedBlock[0] = createTopBorderString(this.width);
		borderedBlock[borderedBlock.length - 1] = createBottomBorderString(this.width);
		
		// Add the left and right borders
		for (int i = 0; i < block.length; i++) {
			String borderedLine = "│" + block[i] + "│";
			int borderedBlockLineNum = i + 1;
			borderedBlock[borderedBlockLineNum] = borderedLine;
		}
		return borderedBlock;
	}
	
	public String[] getDisplayBlock() {
		// Processing Order:
		// content -> aligned block -> padded aligned block -> bordered padded aligned block
		String[] block = this.toAlignedBlock();
		block = this.toPaddedBlock(block);
		
		if (this.hasBorder) {
			block = this.toBorderedBlock(block);
		}
		return block;
	}
	
	protected String createTopBorderString(int length) {
		return "┌" + StringUtils.repeat("─", length - 2) + "┐";
	}
	
	protected String createBottomBorderString(int length) {
		return "└" + StringUtils.repeat("─", length - 2) + "┘";
	}

	protected String createPaddingString(int length) {
		return StringUtils.repeat(" ", length);
	}
	
	public int getTopBottomPadding() {
		return topBottomPadding;
	}

	public int getLeftRightPadding() {
		return leftRightPadding;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
