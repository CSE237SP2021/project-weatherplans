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
	
	public ParentBox(List<Box> boxes, int topBottomPadding, int leftRightPadding) {
		this.children = new ArrayList<Box>(boxes);
		this.topBottomPadding = topBottomPadding;
		this.leftRightPadding = leftRightPadding;
		
		int paddingWidth = 2 * this.leftRightPadding;
		this.width = paddingWidth + getLargestChildWidth();
		
		int paddingHeight = 2 * this.topBottomPadding;
		this.height = paddingHeight + getLargestChildHeight();
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
		
		int largestChildWidth = getLargestChildWidth();
		
		// Grab the content of every child. We will display them in a column.
		ArrayList<String[]> contentOfChildren = new ArrayList<String[]>();
		for (Box box : this.children) {
			String[] content = box.toString().split("\n");
				
			// Center align every single string
			for (int i = 0; i < content.length; i++) {
				String line = content[i];
				String centeredLine = StringUtils.center(line, largestChildWidth);
				content[i] = centeredLine;
			}
			
			contentOfChildren.add(content);
		}
		
		// Now rejoin the centered strings
		String joinDelimiter = "";
		for (int i = 0; i < this.innerPadding; i++) {
			joinDelimiter += "\n";
		}
		
		StringJoiner output = new StringJoiner(joinDelimiter);
		for (String[] content : contentOfChildren) {
			String contentString = String.join("\n", content);
			output.add(contentString);
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
