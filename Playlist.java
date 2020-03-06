package HomeWork2;
import java.io.*;

public class Playlist {
	
	private Song first;
	
	public Playlist() {
		first = null;
	}
	
	public boolean isEmpty() {
		return first==null;
	}
	
	public void viewFirst() {
		System.out.println(first.getTitle() + "  " + first.getArtist());
	}
	
	public void addSong(String title, String artist) {
		
		Song newSong = new Song(title,artist);
		
		newSong.setNext(first);
		
		first = newSong;
		
	}
	
	public Song listenToSong() {
		
		Song theSong = first;
		
		if(isEmpty()) {
			System.out.println("The list is empty");
		} else {
			first = first.getNext();
		}
		return theSong;
	}
	
	public void printList(PrintWriter out) {
		
		Song theSong = first;
		
		while(theSong != null) {
			
			theSong.printSong(out);
			
			theSong = theSong.getNext();
			
		}
		
	}
}
