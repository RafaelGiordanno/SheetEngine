package com.rafaelgiordanno.sheetengine.editor;

import com.badlogic.gdx.math.Rectangle;

public class SegmentDefinition {
	private String name;
	private int sourceIndex;
	private Rectangle srcRect;
	private int flags;
	
	public SegmentDefinition(String _name, 
			int _sourceIndex, Rectangle _srcRect, int _flags) {
		setName(_name);
		setSourceIndex(_sourceIndex);
		setSrcRect(_srcRect);
		setFlags(_flags);
	}

	public String getName() { return name; }
	private void setName(String name) { this.name = name; }

	public int getSourceIndex() { return sourceIndex; }
	private void setSourceIndex(int sourceIndex) { this.sourceIndex = sourceIndex; }
	
	public Rectangle getSrcRect() { return srcRect; }
	private void setSrcRect(Rectangle srcRect) { this.srcRect = srcRect; }

	public int getFlags() { return flags; }
	private void setFlags(int flags) { this.flags = flags; }
}
