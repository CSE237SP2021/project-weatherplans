package datadisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

/**
 * A column of multiple Blocks. 
 * Only center alignment is implemented.
 * @author evan
 *
 */
public class ParentBlock extends Block {
	private List<Block> children;
	private static int INNER_MARGIN = 1;	// The number of empty lines between child blocks
	
	public ParentBlock(List<Block> boxes, int topBottomPadding, int leftRightPadding) {
		super(topBottomPadding, leftRightPadding, true);
		this.children = new ArrayList<Block>(boxes);
		
		super.width = calculateWidth();
		super.height = calculateHeight();
	}
	

	@Override
	protected int calculateWidth() {
		int paddedWidth = 2 * this.leftRightPadding + getLargestChildWidth();
		int totalWidth = paddedWidth;
		
		if (this.hasBorder) {
			totalWidth += 2 * BORDER_SIZE;
		}
		return totalWidth;
	}
	
	@Override
	protected int calculateHeight() {
		int paddedHeight = 2 * this.topBottomPadding;
		int totalHeight = paddedHeight;
		for (Block box : this.children) {
			totalHeight += box.getHeight();
		}
		totalHeight += INNER_MARGIN * (this.children.size() - 1);
		
		if (this.hasBorder) {
			totalHeight += 2 * BORDER_SIZE;
		}
		return totalHeight;
	}
	
	/**
	 * Calculate aligned block height.
	 * In other words, calculate block height taking only the heights of children
	 * and and INNER_MARGIN.
	 */
	private int calculateAlignedBlockHeight() {
		int alignedHeight = 0;
		for (Block box : this.children) {
			alignedHeight += box.getHeight();
		}
		alignedHeight += INNER_MARGIN * (this.children.size() - 1);
		return alignedHeight;
	}
	
	
	@Override
	protected String[] toAlignedBlock() {
		// Center every single child block, with respect to the largest width child block
		int largestChildWidth = getLargestChildWidth();
		ArrayList<String[]> alignedChildBlocks = new ArrayList<String[]>();
		for (Block box : this.children) {
			String[] block = box.getDisplayBlock();
			for (int i = 0; i < block.length; i++) {
				String line = block[i];
				String centeredLine = StringUtils.center(line, largestChildWidth);
				block[i] = centeredLine;
			}
			alignedChildBlocks.add(block);
		}
		
		// Add inner padding between each child block and convert to string array
		int alignedBlockHeight = calculateAlignedBlockHeight();
		String[] alignedBlock = new String[alignedBlockHeight];
		int alignedBlockLineNum = 0;
		for (int i = 0; i < alignedChildBlocks.size() - 1; i++) {
			String[] block = alignedChildBlocks.get(i);
			for (String line : block) {
				alignedBlock[alignedBlockLineNum++] = line;
			}
			// Add Inner Padding
			for (int j = 0; j < ParentBlock.INNER_MARGIN; j++) {
				alignedBlock[alignedBlockLineNum++] = createPaddingString(largestChildWidth);
			}
		}
		
		// Add last block
		String[] finalBlock = alignedChildBlocks.get(alignedChildBlocks.size() - 1);
		for (String line : finalBlock) {
			alignedBlock[alignedBlockLineNum++] = line;
		}
		
		return alignedBlock;
	}
	
	/**
	 * Returns the width of the child block with the largest width.
	 */
	private int getLargestChildWidth() {
		int largestChildWidth = 0;
		for (Block box : this.children) {
			if (box.getWidth() > largestChildWidth) {
				largestChildWidth = box.getWidth();
			}
		}
		return largestChildWidth;
	}
	
	public String toString() {
		String[] displayBlock = getDisplayBlock();
		
		String joinDelimiter = "";
		for (int i = 0; i < ParentBlock.INNER_MARGIN; i++) {
			joinDelimiter += "\n";
		}
		
		StringJoiner output = new StringJoiner(joinDelimiter);
		for (String line: displayBlock) {
			output.add(line);
		}
		return output.toString();
	}
}
