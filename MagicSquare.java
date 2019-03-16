package P1;

import java.io.*;
import java.util.Scanner;

public class MagicSquare {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		if (!generateMagicSquare(a))
			System.out.println("Your input isn't valid.");
		else {
			if (isLegalMagicSquare("src/P1/txt/6.txt"))
				System.out.println("It is Magic Square");
		}
		sc.close();
		for (int i = 1; i < 6; i++) {
			String fileName = i + ".txt";
			String filename = "src/P1/txt/" + fileName;
			if (isLegalMagicSquare(filename))
				System.out.println(i + " " + "It is Magic Square");
		}
	}

	public static boolean isLegalMagicSquare(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			int row = 0;
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			tempString = reader.readLine();// ���ļ��ĵ�һ��
			if (tempString.indexOf(" ") != -1) {
				System.out.println("The separator isn't '\t'.");
				reader.close();
				return false;
			}
			String[] a = tempString.split("\t");
			int n = a.length;
			int[][] square = new int[n][n];
			for (int i = 0; i < n; i++) {
				if (isNumer(a[i])) {
					square[row][i] = Integer.valueOf(a[i]);
				} else {
					reader.close();
					System.out.println("There are floating point numbers or negative numbers in the data.");
					return false;
				}
			}
			while ((tempString = reader.readLine()) != null) {// ���ζ�ȡ�ļ��Ľ������Ĳ���
				if (tempString.indexOf(" ") != -1) {
					System.out.println("The separator isn't Tab.");
					reader.close();
					return false;
				}
				row++;
				String[] b = tempString.split("\t");
				if (b.length != n) {
					reader.close();
					System.out.println("The numbers per row are different.");
					return false;
				}
				for (int i = 0; i < n; i++) {
					if (isNumer(b[i])) {
						square[row][i] = Integer.valueOf(b[i]);
					} else {
						reader.close();
						System.out.println("There are floating point numbers or negative numbers in the data.");
						return false;
					}
				}
			}
			reader.close();
			if (row != n - 1)// ������������������򲻿�����magic square
			{
				System.out.println("The number of rows is not equal to the number of columns.");
				return false;
			}
			if (!isEqualSquare(square, n)) {
				System.out.println("The sum of the numbers in each line is not consistent.");
				return false;
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public static boolean isNumer(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEqualSquare(int square[][], int n) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += square[0][i];
		}
		for (int i = 0; i < n; i++) {
			int s = 0;
			for (int j = 0; j < n; j++) {
				s += square[i][j];
			}
			if (s != sum)
				return false;
		}
		for (int i = 0; i < n; i++) {
			int s = 0;
			for (int j = 0; j < n; j++) {
				s += square[j][i];
			}
			if (s != sum)
				return false;
		}
		int s = 0;
		for (int i = 0; i < n; i++) {
			s += square[i][i];
		}
		if (s != sum)
			return false;
		int m = 0;
		for (int i = n - 1; i >= 0; i--) {
			m += square[i][n - i - 1];
		}
		if (m != sum)
			return false;
		return true;
	}

	public static boolean generateMagicSquare(int n) {

		if (n % 2 == 0 || n < 0)
			return false;
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;

		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}

		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");
			System.out.println();
		}
		try {
			File file = new File("src/P1/txt/6.txt");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++)
					bw.write(magic[i][j] + "\t");
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

}
