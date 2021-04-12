package datadisplay;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * How content is processed into a display block:
 * content (not block) -> add left/center alignment (block) -> add padding (block) -> add border (block)
 * 
 * A common term used is "block". 
 * Block just means rectangular; Every element in the string array has the same length.
 * 
 * 
 * @author evan
 *
 */
public class TextBox extends Box {
	private ArrayList<String> content;
	private TextAlignment alignment;
	
	public TextBox(ArrayList<String> content, int topBottomPadding, int leftRightPadding, boolean hasBorder) {
		super(topBottomPadding, leftRightPadding, hasBorder);
		this.content = new ArrayList<String>(content);	// Copy the passed list
		this.alignment = TextAlignment.LEFT_ALIGNED;
		
		super.width = calculateWidth();
		super.height = calculateHeight();
	}
	
	@Override
	protected int calculateWidth() {
		int totalWidth = 2 * leftRightPadding + this.getLongestLineLength();
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
	public String[] toAlignedBlock() {
		int longestLineLength = this.getLongestLineLength();
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
	 * Used only to initialize this.width
	 * @param strings
	 * @return
	 */
	private int getLongestLineLength() {
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
		
		ArrayList<Box> children = new ArrayList<Box>();
		TextBox tb = new TextBox(lines, 1, 1, true);
		TextBox tb2 = new TextBox(lines2, 1, 1, true);
		children.add(tb);
		children.add(tb2);
		
		ParentBox parent = new ParentBox(children, 0, 0);
		System.out.println(parent);		
		
	}

}
