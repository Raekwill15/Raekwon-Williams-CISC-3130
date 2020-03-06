package HomeWork2;
import java.io.*;

public class Song {
	
	private String pos;
	private String title;
	private String artist;
	private String streams;
	private String url;
	private Song next;
	
	public Song(String pos,String title,String artist,String streams,String url) {
		
		this.pos = pos;
		this.title = title;
		this.artist = artist;
		this.streams = streams;
		this.url = url;
		
	}
	
	public Song(String title,String artist) {
		this.title = title;
		this.artist = artist;
		
	}
	
	public void printSong(PrintWriter out) {
		out.printf("%-70s %25s\n" , title,artist);
	}
	
	public String getPos() {return pos;}
	public String getTitle() {return title;}
	public String getArtist() {return artist;}
	public String getStreams() {return streams;}
	public String getUrl() {return url;}
	public Song getNext() {return next;}
	
	public void setArtist(String artist) {this.artist = artist;}
	public void setTitle(String title) {this.title = title;}
	public void setNext(Song nextSong) { next = nextSong;}
	
	
	
}
