package HomeWork2;
import java.util.Scanner;
import java.io.*;

public class MyQueue {
	
	private int front;
	private int rear; 
	private int numItems=0;
	private Song[] queueArray;
	private int qSize;
	
	public MyQueue (int size) {
		qSize = size;
		queueArray = new Song[qSize];
		front = 0;
		rear = 0;
		numItems = 0;
		
	}
	
	void insert(Song row) {
		
		if(numItems + 1 <= qSize) {
		
			queueArray[rear] = row;
			rear++;
			numItems++;
			
		//	System.out.println("INSERT " + row.getTitle() + " by " + row.getArtist() + " Was added");
		}
	}
	
	Song remove() {
		
		Song temp = queueArray[front++];
		if(numItems > 0) {
			System.out.println("REMOVE " + queueArray[front].getTitle());
			queueArray[front] = null;
			front++;
			numItems--;
		} else {
			System.out.println("THE QUEUE IS ALREADY EMPTY");
		}
		
		return temp;	
	}
	
	void peek() {
		System.out.println("THE FIRST ELEMENT IS " + queueArray[front].getTitle());
	}
	
	void printQ() {
		
		for (int x=0; x < numItems; x++ ) {
			System.out.printf("%-70s %25s \n\n" ,queueArray[x].getTitle(),queueArray[x].getArtist());
		}
		
	}
	
}
