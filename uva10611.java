package s14926PM10611;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static int modifiedBinarySearch(int luchusCurrHeight, ArrayList<Integer> ladiesHeights, int arraysLength) {
		int max = arraysLength - 1;
		int searched = luchusCurrHeight;
		int min = 0;
		int mid = 0;
		while (min < max) {
			mid = (max + min) / 2;
			if (ladiesHeights.get(mid) == searched)
				return mid;
			else if (ladiesHeights.get(mid) > searched) {
				max = mid - 1;
			} else if (ladiesHeights.get(mid) < searched) {
				min = mid + 1;
			}
		}
		return (min + max) / 2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int currHeight = Integer.parseInt(br.readLine());
		ArrayList<Integer> ladiesHeights = new ArrayList<>(currHeight);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < currHeight; i++) {
			int currentHeight = Integer.parseInt(st.nextToken());
			if (i == 0 || ladiesHeights.get(ladiesHeights.size() - 1) != currentHeight)
				ladiesHeights.add(currentHeight);
		}
		int currNumberOfLadiesHeights = ladiesHeights.size();//
		int numberofHeights = Integer.parseInt((br.readLine()));
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < numberofHeights; i++) {
			int luchusHeight = Integer.parseInt(st.nextToken());
			int position = modifiedBinarySearch(luchusHeight, ladiesHeights, currNumberOfLadiesHeights);//
			if (ladiesHeights.get(position) == luchusHeight) {
				if (position > 0 && position < currNumberOfLadiesHeights - 1) {//
					System.out.println(ladiesHeights.get(position - 1) + " " + ladiesHeights.get(position + 1));
				} else if (position == 0) {
					System.out.println("X " + ladiesHeights.get(1));
				} else
					System.out.println(ladiesHeights.get(position - 1) + " X");
			} else {
				if (ladiesHeights.get(position) > luchusHeight && position >= 1) {
					position--;
				}
				if (position == 0 && ladiesHeights.get(0) < luchusHeight)
					System.out.println(ladiesHeights.get(0) + " " + ladiesHeights.get(1));
				else if (position == 0 && ladiesHeights.get(0) > luchusHeight)
					System.out.println("X " + ladiesHeights.get(0));
				else if (position == currNumberOfLadiesHeights - 1) {//
					System.out.println(ladiesHeights.get(currNumberOfLadiesHeights - 1) + " X");//
				} else
					System.out.println(ladiesHeights.get(position) + " " + ladiesHeights.get(position + 1));
			}
		}
	}
}
