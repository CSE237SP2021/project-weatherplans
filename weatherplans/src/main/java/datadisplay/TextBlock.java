package datadisplay;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

/**
 * A lone Block intended to display some text.
 * @author evan
 *
 */
public class TextBlock extends Block {
	private ArrayList<String> content;
	private TextAlignment alignment;
	
	/**
	 * 
	 * @param content - the actual text to be displayed
	 * @param topBottomPadding
	 * @param leftRightPadding
	 * @param hasBorder
	 */
	public TextBlock(ArrayList<String> content, int topBottomPadding, int leftRightPadding, boolean hasBorder) {
		super(topBottomPadding, leftRightPadding, hasBorder);
		this.content = new ArrayList<String>(content);	// Copy the passed list
		this.alignment = TextAlignment.LEFT_ALIGNED;
		
		super.width = calculateWidth();
		super.height = calculateHeight();
	}
	
	@Override
	protected int calculateWidth() {
		int totalWidth = 2 * leftRightPadding + this.getLongestContentLineLength();
		if (this.hasBorder) {
			totalWidth += 2 * BORDER_SIZE;
		}
		return totalWidth;
	}
	
	@Override
	protected int calculateHeight() {
		int totalHeight = 2 * topBottomPadding + this.content.size();
		if (this.hasBorder) {
			totalHeight += 2 * BORDER_SIZE;
		}
		return totalHeight;
	}
	
	@Override
	protected String[] toAlignedBlock() {
		int longestLineLength = this.getLongestContentLineLength();
		String[] alignedBlock = new String[this.content.size()];
		int alignedBlockIdx = 0;
		
		switch (this.alignment) {
		case LEFT_ALIGNED:
			StringBuilder sb = new StringBuilder();
			for (String line : this.content) {
				sb.append(line);
				while (sb.length() < longestLineLength) {
					sb.append(' ');
				}
				alignedBlock[alignedBlockIdx++] = sb.toString();
				sb.setLength(0);
			}
			break;
			
		case CENTER_ALIGNED:
			for (String line : content) {
				String centeredLine = StringUtils.center(line, longestLineLength);
				alignedBlock[alignedBlockIdx++] = centeredLine;
			}
			break;
			
		default:
			throw new RuntimeException("Unrecognized Text Alignment!");
		}
	
		return alignedBlock;
	}
	
	/**
	 * Get the longest length line of unprocessed text content.
	 * @param strings
	 * @return
	 */
	private int getLongestContentLineLength() {
		int max = this.content.get(0).length();
		for (String line : this.content) {
			if (line.length() > max) {
				max = line.length();
			}
		}
		return max;
	}
	
	@Override
	public String toString() {
		String[] block = this.getDisplayBlock();
		StringBuilder sb = new StringBuilder();
		for (String line : block) {
			sb.append(line);
			sb.append('\n');
		}
		return sb.toString();
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
		
		ArrayList<Block> children = new ArrayList<Block>();
		TextBlock tb = new TextBlock(lines, 1, 1, true);
		TextBlock tb2 = new TextBlock(lines2, 1, 1, true);
		children.add(tb);
		children.add(tb2);
		
		ParentBlock parent = new ParentBlock(children, 0, 0);
		System.out.println(parent);		
		
	}

}
