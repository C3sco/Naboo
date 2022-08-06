package application;

import java.net.URL;
import java.util.ArrayList;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RSSReader {
	public RSSReader() throws Exception{
	
		URL url = new URL("https://www.ansa.it/sito/ansait_rss.xml");
		XmlReader reader = null;
		ArrayList<Notizia> listaNotizie = new ArrayList<Notizia>();
		
		try {
			reader = new XmlReader(url);
			SyndFeedInput inp = new SyndFeedInput();
			SyndFeed feed = inp.build(reader);
			
		} finally {
			if(reader!=null) {
				reader.close();
			}
		}
	}
	/*
	final URL url;
	public RSSReader(String urlFeed) {
		try {
			this.url = new URL(urlFeed);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public SyndFeed 
		
	}*/

}