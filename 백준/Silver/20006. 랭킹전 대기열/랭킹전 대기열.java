import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Player implements Comparable<Player> {
		int level;
		String name;

		public Player(int level, String name) {
			this.level = level;
			this.name = name;
		}

		@Override
		public int compareTo(Player o) {
			return this.name.compareTo(o.name);
		}
	}

	static class Room {
		int cnt;
		int minLev;
		int maxLev;
		List<Player> players;

		public Room(int cnt, int minLev, int maxLev, Player player) {
			this.cnt = cnt;
			this.minLev = minLev;
			this.maxLev = maxLev;
			this.players = new ArrayList<>();
			players.add(player);
		}

		public void addPlayer(Player player) {
			this.players.add(player);
			++this.cnt;
		}
	}

	static int playerCnt;
	static int limit;
	static List<Room> rooms;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		playerCnt = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());

		rooms = new ArrayList<>();
		for (int idx = 0; idx < playerCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int level = Integer.parseInt(st.nextToken());
			String name = st.nextToken();

			// 입장 가능한 방이 있는지 확인
			boolean check = false;
			for (Room room : rooms) {
				// 인원이 다 찬 경우, 불가능
				if (room.cnt >= limit) {
					continue;
				}

				// 범위 안에 속하는 경우, 해당 방에 입장 가능
				if (room.minLev <= level && level <= room.maxLev) {
					room.addPlayer(new Player(level, name));
					check = true;
					break;
				}
			}

			// 입장 가능한 방이 없는 경우, 새로운 방 생성
			if (!check) {
				rooms.add(new Room(1, level - 10, level + 10, new Player(level, name)));
			}
		}

		// 출력
		for (Room room : rooms) {
			// 인원이 다 찬 경우, Started! / 아니면 Waiting!
			sb.append((room.cnt == limit) ? "Started!" : "Waiting!").append("\n");

			// 닉네임 순으로 정렬
			Collections.sort(room.players);
			for (Player player : room.players) {
				sb.append(player.level).append(" ").append(player.name).append("\n");
			}
		}

		System.out.println(sb);
	}
}