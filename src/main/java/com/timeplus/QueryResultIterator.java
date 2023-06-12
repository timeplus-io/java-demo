package com.timeplus;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;

public class QueryResultIterator implements Iterator<JSONArray> {
	
    private BufferedReader br = null;
    private JSONArray bufferedRows = null;
    private int bufferedRowIndex = -1;
    
	public QueryResultIterator(BufferedReader br) {
		this.br = br;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if (this.bufferedRows == null) {
			try {
				this.bufferedRows = this.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.bufferedRowIndex = 0;
			return this.bufferedRows != null;
		}
		
		return true;
	}

	@Override
	public JSONArray next() {
		if (!this.hasNext()) {
			return null;
		}
		
		JSONArray row = this.bufferedRows.getJSONArray(this.bufferedRowIndex);
		this.bufferedRowIndex++;
		
		if (bufferedRowIndex == this.bufferedRows.length()) {
			this.bufferedRows = null;
			this.bufferedRowIndex = -1;
		}
		
		return row;
		
	}
	
	private JSONArray read() throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
            if (line.length() == 0) {
                continue;
            }

            int colonIndex = line.indexOf(":");
            String eventField = line.substring(0, colonIndex).trim();
            String eventData = line.substring(colonIndex + 1).trim();

            if (eventField.equals("event")) {
            	// Skip the next line since this cannot be query result
            	// TODO: We may want to allow the user to add observer for those events
                br.readLine();
            } else if (eventField.equals("data")) {
                return new JSONArray(eventData);
            }
        }
		
		return null;
	}

}
