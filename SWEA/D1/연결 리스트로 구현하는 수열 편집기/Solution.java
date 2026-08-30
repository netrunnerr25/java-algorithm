import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, L;

	private static class Node {
		int value;
		Node prev;
		Node next;

		public Node() {

		}

		public Node(int value) {
			this.value = value;
		}
	}

	private static class LinkedList {
		int size;
		Node head;
		Node tail;

		public LinkedList() {
			size = 0;
			head = new Node();
			tail = head;
		}

		public void add(int value) {
			Node newNode = new Node(value);

			Node prevLastNode = tail;
			newNode.prev = prevLastNode;
			prevLastNode.next = newNode;

			tail = newNode;
			size++;
		}

		public void add(int index, int value) {
			Node target = getTargetNode(index);

			Node targetPrev = target.prev;
			Node newNode = new Node(value);

			targetPrev.next = newNode;
			newNode.prev = targetPrev;

			newNode.next = target;
			target.prev = newNode;
			size++;
		}

		public void remove(int index) {
			Node target = getTargetNode(index);

			Node targetPrev = target.prev;
			Node targetNext = target.next;

			targetPrev.next = targetNext;

			if (targetNext != null) {
				targetNext.prev = targetPrev;
			} else {
				tail = targetPrev;
			}
			size--;

		}

		public void change(int index, int value) {
			Node target = getTargetNode(index);
			target.value = value;
		}

		public int get(int index) {
			Node target = getTargetNode(index);

			if (target == null) {
				return -1;
			}
			return target.value;
		}

		public Node getTargetNode(int index) {
			Node node = null;

			if (index < (size / 2)) {
				node = head;

				for (int i = -1; i < index; i++) {
					node = node.next;
				}
			} else {
				node = tail;

				for (int i = size - 1; i > index; i--) {
					node = node.prev;
				}
			}

			return node;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			LinkedList linkedList = new LinkedList();

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());

				linkedList.add(num);

			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				char order = st.nextToken().charAt(0);

				if (order == 'I') {
					int index = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());

					insert(index, value, linkedList);

				} else if (order == 'D') {
					int index = Integer.parseInt(st.nextToken());

					delete(index, linkedList);

				} else if (order == 'C') {
					int index = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());

					changeValue(index, value, linkedList);
				}
			}
			sb.append("#").append(t).append(" ").append(getValue(L, linkedList)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void insert(int index, int value, LinkedList linkedList) {
		int size = linkedList.size;

		if (index >= size || index < 0) {
			return;
		}

		linkedList.add(index, value);
	}

	private static void delete(int index, LinkedList linkedList) {
		int size = linkedList.size;

		if (index >= size || index < 0) {
			return;
		}

		linkedList.remove(index);
	}

	private static void changeValue(int index, int value, LinkedList linkedList) {
		int size = linkedList.size;

		if (index >= size || index < 0) {
			return;
		}

		linkedList.change(index, value);
	}

	private static int getValue(int index, LinkedList linkedList) {
		int size = linkedList.size;

		if (index >= size || index < 0) {
			return -1;
		}

		return linkedList.get(index);
	}
}
