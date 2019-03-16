package P3;

import java.util.ArrayList;
import java.util.List;

public class FriendshipGraph {
	List<Person> nameList = new ArrayList<Person>();
	public int Size = 1000;
	//public static int step=0;
	int[][] w = new int[Size][Size];

	public int getDistance(Person name1, Person name2) {
		if (name1.getNum() == name2.getNum())
			return 0;
		int size = nameList.size();
		int distance = 1;
		boolean[] visited = new boolean[size];
		visited[name1.getNum()] = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (w[i][j] == 1 && !visited[j]) {
					if (j == name2.getNum())
						return distance;
					else {
						visited[j] = true;
						i = j;
						j = 0;
						distance++;
						continue;
					}
				}
				distance = i - name1.getNum() + 1;
			}
		}
		return -1;
	}

	public boolean addVertex(Person name) {
		if (!nameList.contains(name)) {
			nameList.add(name);
			name.setNum(nameList.indexOf(name));
			return true;
		} else
			return false;
	}

	public boolean addEdge(Person name1, Person name2) {
		if (nameList.contains(name1) && nameList.contains(name2)) {
			w[name1.getNum()][name2.getNum()] = 1;
			return true;
		} else
			return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		//System.out.println(rachel.getNum()+" "+ross.getNum()+" "+ben.getNum()+" "+kramer.getNum());
		System.out.println(graph.getDistance(rachel, ross));
		// should print 1
		System.out.println(graph.getDistance(rachel, ben));
		// should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		// should print -1

	}

}
