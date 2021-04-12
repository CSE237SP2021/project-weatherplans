package datadisplay;


import org.apache.commons.lang3.StringUtils;

/**
 * A block is a unit of display, somewhat similar to a block element in HTML.
 * We are printing to command line, so a block is a 2D array of characters, 
 * though in implementation the type String[] is used to let us abstract each
 * element as a single line of text. 
 * 
 * The only interaction that should be needed is specifying the content + formatting,
 * and then calling getDisplayBlock().
 * 
 * Blocks have alignment, width, height, padding, and an optional border.
 * 		alignment - which direction to align a block's content
 * 		width - how long every line of a block is, including padding and border
 * 		height - the number of lines in a block, including padding and border
 * 		padding - how much whitespace to add around a block
 * 		border - a single character rectangular border around a block and its padding
 * 		
 * 		
 * 
 * Implementation Details:
 * Anything that implements Block will have a getDisplayBlock() method.
 * This will output a block with alignment, padding, and optionally border.
 * 
 * The outputted display block will always follow these steps of processing:
 * content -> add alignment -> add padding -> (optional) add border
 * 
 * 
 * @author evan
 *
 */
public abstract class Block {
	protected static final int BORDER_SIZE = 1;
	
	protected int topBottomPadding, leftRightPadding;
	protected boolean hasBorder;
	protected int width;		// The number of characters per line of the display block
	protected int height;		// The number of lines of the display block
	
	public Block(int topBottomPadding, int leftRightPadding, boolean hasBorder) {
		this.topBottomPadding = topBottomPadding;
		this.leftRightPadding = leftRightPadding;
		this.hasBorder = hasBorder;
		
		/**
		 * I do not use
		 * 		this.width = calculateWidth();
		 * 		this.height = calculateHeight();
		 * to allow subclasses to use their own fields when using calculateWidth() and calculateHeight().
		 */
	}
	
	/**
	 * Spits out the content of a Block with the proper formatting.
	 * 
	 * Processing Order:
	 * content -> add alignment -> add padding -> add border
	 * 
	 * @return displayBlock - the final result with all of the specified formatting
	 */
	public String[] getDisplayBlock() {
		String[] displayBlock = this.toAlignedBlock();
		displayBlock = this.toPaddedBlock(displayBlock);
		
		if (this.hasBorder) {
			displayBlock = this.toBorderedBlock(displayBlock);
		}
		return displayBlock;
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
	
	/**
	 * Calculates the total width the final display block will have
	 */
	protected abstract int calculateWidth();
	
	/**
	 * Calculates the total height the final display block will have
	 */
	protected abstract int calculateHeight();
	
	/**
	 * Returns the non-rectangular content of a block as an aligned block
	 */
	protected abstract String[] toAlignedBlock();
	
	/**
	 * Returns a padded block
	 * @param alignedBlock - a block generated from toAlignedBlock()
	 * @return - a block with alignment, padding
	 */
	protected String[] toPaddedBlock(String[] alignedBlock) {
		String[] paddedBlock = new String[alignedBlock.length + 2 * topBottomPadding];
		
		// Vertical Padding - add blank lines to top and bottom
		int paddedLineLength = leftRightPadding * 2 + alignedBlock[0].length();
		String blankLine = StringUtils.repeat(" ", paddedLineLength);
		for (int i = 0; i < topBottomPadding; i++) {
			paddedBlock[i] = blankLine;
			paddedBlock[paddedBlock.length - i - 1] = blankLine;
		}
		
		// Horizontal Padding - Add left and right padding to each line
		String horizontalPadding = StringUtils.repeat(" ", leftRightPadding);
		int paddedBlockLineNum = topBottomPadding;
		for (String line : alignedBlock) {
			line = horizontalPadding + line + horizontalPadding;
			paddedBlock[paddedBlockLineNum++] = line;
		}
		
		return paddedBlock;
	}
	
	/**
	 * Returns a block with a border
	 * @param paddedBlock - a block generated from toPaddedBlock()
	 * @return - a block with alignment, padding, border
	 */
	protected String[] toBorderedBlock(String[] paddedBlock) {
		// Add the top and bottom borders
		String[] borderedBlock = new String[paddedBlock.length + 2];
		borderedBlock[0] = createTopBorderString(this.width);
		borderedBlock[borderedBlock.length - 1] = createBottomBorderString(this.width);
		
		// Add the left and right borders
		for (int i = 0; i < paddedBlock.length; i++) {
			String borderedLine = "│" + paddedBlock[i] + "│";
			int borderedBlockLineNum = i + 1;
			borderedBlock[borderedBlockLineNum] = borderedLine;
		}
		return borderedBlock;
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
}
