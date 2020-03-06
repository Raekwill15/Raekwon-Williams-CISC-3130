package HomeWork2;
//RAEKWON WILLIAMS
import java.util.*;
import java.io.*;

public class Hw2Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[][] songList = new String[201][10];
		ArrayList<String> artistSong = new ArrayList<String>();
		
		MyQueue firstWeek = new MyQueue(201);
		MyQueue secondWeek = new MyQueue(201);
		MyQueue thirdWeek = new MyQueue(201);
		MyQueue fourthWeek = new MyQueue(201);
		
		File file1 = new File("D:\\eclipse\\CISC3130Spring20\\src\\HomeWork2\\Top200_2-06.csv");
		File file2 = new File("D:\\eclipse\\CISC3130Spring20\\src\\HomeWork2\\Top200_2-13.csv");
		File file3 = new File("D:\\eclipse\\CISC3130Spring20\\src\\HomeWork2\\Top200_2-20.csv");
		File file4 = new File("D:\\eclipse\\CISC3130Spring20\\src\\HomeWork2\\Top200_2-27.csv");
		File outFile1 = new File("D:\\eclipse\\CISC3130Spring20\\src\\Homework2\\Week1SortedSongs.txt");
		File outFile2 = new File("D:\\eclipse\\CISC3130Spring20\\src\\Homework2\\Week2SortedSongs.txt");
		File outFile3 = new File("D:\\eclipse\\CISC3130Spring20\\src\\Homework2\\Week3SortedSongs.txt");
		File outFile4 = new File("D:\\eclipse\\CISC3130Spring20\\src\\Homework2\\Week4SortedSongs.txt");
		File outFile5 = new File("D:\\eclipse\\CISC3130Spring20\\src\\Homework2\\PlaylistandHistory.txt");
		
		PrintWriter out = new PrintWriter(outFile1);
		PrintWriter out2 = new PrintWriter(outFile2);
		PrintWriter out3 = new PrintWriter(outFile3);
		PrintWriter out4 = new PrintWriter(outFile4);
		PrintWriter out5 = new PrintWriter(outFile5);
		
		//Loads Csv file into String Arrays
		String[][] week1 = readWeek(file1);
		String[][] week2 = readWeek(file2);
		String[][] week3 = readWeek(file3);
		String[][] week4 = readWeek(file4);
		
		sortArray(week1);
		sortArray(week2);
		sortArray(week3);
		sortArray(week4);
			
		//Each week sorted into a different output File
		out.println("WEEK1");
		printArray(week1,out);
		out2.println("\nWEEK2");
		printArray(week2,out2);
		out3.println("\nWEEK3");
		printArray(week3,out3);
		out4.println("\nWEEK4");
		printArray(week4,out4);
		
		firstWeek = makeQueue(week1);
		secondWeek = makeQueue(week2);
		thirdWeek = makeQueue(week3);
		fourthWeek = makeQueue(week4);	
	
		//Queue Functions
		firstWeek.peek();
		firstWeek.remove();
		firstWeek.peek();
		secondWeek.peek();
		secondWeek.remove();
		secondWeek.peek();
		thirdWeek.peek();
		thirdWeek.remove();
		thirdWeek.remove();
		thirdWeek.peek();
		fourthWeek.peek();
		fourthWeek.remove();
		fourthWeek.peek();
		
		String[][] mixArr = mergeArray(week1,week2);
		String[][] mixArr2 = mergeArray(mixArr,week3);
		String[][] mixArr3 = mergeArray(mixArr2,week4);
		
		out.flush();
		out2.flush();
		out3.flush();
		out4.flush();
		out5.flush();
		
		//putting merged sorted arrays into an ArrayList
		sortArray(mixArr3);
		artistSong = clearCopy(mixArr3);
		
		// Making and printing Playlist
		Playlist songLL = new Playlist();
		arrayToList(week1,songLL);
		songLL.printList(out5);
		
		ArrayList<Song> listenHistory = new ArrayList<>();
		
		listen(songLL,listenHistory);
		listen(songLL,listenHistory);
		listen(songLL,listenHistory);
		listen(songLL,listenHistory);
		listen(songLL,listenHistory);
		listen(songLL,listenHistory);
		
		printArrList2(listenHistory,out5);
		
		out.close();
		out2.close();
		out3.close();
		out4.close();
		out5.close();
		System.out.println("DONE");
	}

	//Makes top 200 csv file into an array
	public static String[][] readWeek(File file) throws IOException {
		String row;
		int count,x,y=0;
		String[][] songList = new String[201][10];
		
		for (int z = 0;z<10;z++) {
			try {
				BufferedReader csvReader = new BufferedReader (new FileReader(file));

				while ((row = csvReader.readLine()) != null) {
					//System.out.println(row);
					StringTokenizer token = new StringTokenizer(row,",");
					count = token.countTokens();
					for	(x=0;x<count;x++) {
						songList[y][x] = token.nextToken();
					}
					y++;
				}
				csvReader.close();
				return songList;
		}
		catch(Exception e) {
			System.out.println("Null Pointer " + e);
		}
		
		}
		return songList;
	}
	
	//Prints Array to File
	public static void printArray(String[][] songArray , PrintWriter out) {
		int x=0,y=0;
		
		
		for (x=0;x<songArray.length-1;x++) {
			out.println("");
			for (y=0;y<3;y++) {
				out.println(songArray[x][y] + " ");
			}
		}
		
	}
	
	//Creates A Queue from a String Array
	public static MyQueue makeQueue(String[][] week) {
		int x;
		MyQueue q = new MyQueue(201);
		
		for (x=0;x<201;x++) {
			Song newSong = new Song(week[x][0],week[x][1],week[x][2],week[x][3],week[x][4]);
			q.insert(newSong);
		}
		
		return q;
	}
	
	// Sorts String Array by Artist name
	public static void sortArray(String[][] top200) {
		String pos,trackName,artist,streams,url;
		
		for (int z = 1; z < 201; z++) {
			for (int v = z+1; v < 201; v++) {
				if(top200[z][2].charAt(0) == '"') {
					top200[z][2] = top200[z][2].substring(1,top200[z][2].length()-1);
				}
				
				if(top200[z][1].charAt(0) != '"') {
					top200[z][1] = '"' + top200[z][1] + '"';
				}
				
				if (top200[z][2].compareToIgnoreCase(top200[v][2].toLowerCase()) > 0) {
					pos = top200[v][0];
					trackName = top200[v][1];
					artist = top200[v][2];
					streams = top200[v][3];
					url = top200[v][4];
					
					top200[v][0] = top200[z][0];
					top200[v][1] = top200[z][1];
					top200[v][2] = top200[z][2];
					top200[v][3] = top200[z][3];
					top200[v][4] = top200[z][4];
					
					top200[z][0] = pos;
					top200[z][1] = trackName;
					top200[z][2] = artist;
					top200[z][3] = streams;
					top200[z][4] = url;
				}
			}
		}
	}
	
	//Merges two String arrays and returns one with elements from both arrays
	public static String[][] mergeArray(String[][] q1, String[][] q2) {
		
		String[][] newArr = new String[q1.length+q2.length][4];
			System.arraycopy(q1, 0, newArr, 0, q1.length);
			System.arraycopy(q2, 0, newArr, q1.length, q2.length);
				
		return newArr;
	}
	
	//Deletes the copies in the String ArrayList
	public static ArrayList<String> clearCopy(String[][] arr) {
		int x,y;
		String title;
		ArrayList<String> allWeeks = new ArrayList<String>();

		for(x=0;x<arr.length;x++) {
			allWeeks.add(arr[x][1] +"  " + arr[x][2]);
		}
			for (x=0;x<allWeeks.size();x++) {
			title = allWeeks.get(x);
				for (y=x+1;y<allWeeks.size();y++) {
					if (title.equals(allWeeks.get(y))) {
						allWeeks.remove(y);
				} 
			} 
		}
		return allWeeks;
	}
	
	//Prints ArrayList
	public static void printArrList(ArrayList<String> arrList) {
		int x;
		
		for (x=0;x<arrList.size();x++) {
			System.out.println(arrList.get(x));
		}
	}
	
	//Makes a Linked List from the Song array
	public static void arrayToList(String[][] songs,Playlist songLL) {
		
		int x;
		
		for (x=0;x<songs.length;x++) {
			songLL.addSong(songs[x][1], songs[x][2]);
		}
	}
	
	//Listens to song on Playlist and adds song to history arrayList
	public static void listen(Playlist songsLL, ArrayList<Song> History) {
		Song newSong;
		
		newSong = songsLL.listenToSong();
		History.add(newSong);
	}
	
	//Prints ArrayList<Song> with song 
	public static void printArrList2(ArrayList<Song> arrList,PrintWriter out) {
		int x;
		out.println("");
		out.println("LISTEN HISTORY\n");
		for(x=0;x<arrList.size();x++) {
			out.printf("%-70s%5s\n\n" , arrList.get(x).getTitle(),arrList.get(x).getArtist());
		}
		
	}
}
