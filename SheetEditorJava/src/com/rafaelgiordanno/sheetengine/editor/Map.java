package com.rafaelgiordanno.sheetengine.editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Map {
	private SegmentDefinition[] segDef;
	
	public Map() throws IOException {
		segDef = new SegmentDefinition[512];
		readSegmentDefinitions();
	}
	
	private void readSegmentDefinitions() throws IOException {
		BufferedReader s = new BufferedReader(new StringReader(Gdx.files.internal("Content/maps.zdx").readString()));
		String t = "";
		int n = 0;
		int currentTex = -1;
		int curDef = -1;
		Rectangle tRect = null;
		String[] split;
		
		t = s.readLine(); // we ignore the first line
		while ((t = s.readLine()) != null) {
			if (t.startsWith("#")) {
				if (t.startsWith("#src")) {
					split = t.split(" ");
					if (split.length > 1) {
						n = Integer.parseInt(split[1]);
						currentTex = n - 1;
					}
				}
			} else {
				curDef++;
				String name = t;
				
				t = s.readLine();
				split = t.split(" ");
				if (split.length > 3) {
					tRect = new Rectangle();
					tRect.x = Integer.parseInt(split[0]);
					tRect.y = Integer.parseInt(split[1]);
					tRect.width = Integer.parseInt(split[2]) - tRect.x;
					tRect.height = Integer.parseInt(split[3]) - tRect.y;
				} else {
					System.out.println("read fail: " + name);
				}
				
				int tex = currentTex;
				
				t = s.readLine();
				int flags = Integer.parseInt(t);
				
				segDef[curDef] = new SegmentDefinition(name, tex, tRect, flags);
				System.out.println("Adding new segment:\n\tindex: " + curDef + 
						" \n\tname: " + name + " \n\tsource index: " + tex + 
						" \n\trect: " + tRect.toString() + " \n\tflags: " + flags);
			}
		}
	}
	
	public SegmentDefinition[] getSegments() {
		return segDef;
	}
}

