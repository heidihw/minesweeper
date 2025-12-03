public class MinesweeperArchive {
	
	// public static boolean[][] done = new boolean[bw/n][bh/m];

	public static void go() {
		start();
		// if (playnew) stepcheck();
	}

	public static void stepcheck() {

		System.out.println("9x9 board with 25 mines.\nTimer starts after first cell entered.");

        // MouseListener start = new MouseListener(click);
        // panel.addActionListener(start);
        safe = true;

		do step();
		while (safe && inprog);

		endTime = System.currentTimeMillis();
		time = endTime - startTime;
		result();
		stepcheck();

    }

    public static void step() {
		if (click == 1) stepone();
		else takeinput();
		xy = new int[] {x,y};
		current.add(xy);

		safecheck();

		currenta = current.toArray(new int[0][0]);
		System.out.println(Arrays.deepToString(currenta));

		inprog = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (minefield[i][j] && board[i][j] == 9 || minefield[i][j] == false && board[i][j] != 9) {
					inprog = true;
				}
			}
		}
		click++;
		display();
	}

	public static void minecheck() {
		currenta = current.toArray(new int[0][0]);
		System.out.println(Arrays.deepToString(currenta));
	}

	public static void display() {

		System.out.println();
		System.out.print("x "); // y values
		for (int i = 0; i < n; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print(i + " "); // x values
			for (int j = 0; j < n; j++) { // minefield
				if (minefield[i][j]) System.out.print(1 + " ");
				else System.out.print(0 + " ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.print("x "); // y values
		for (int i = 0; i < n; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print(i + " "); // x values
			for (int j = 0; j < n; j++) { // mineborder
				System.out.print(mineborder[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.print("x "); // y values
		for (int i = 0; i < n; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print(i + " "); // x values
			for (int j = 0; j < n; j++) { // board
				if (board[i][j] == 10) System.out.print("x ");
				else if (board[i][j] == 9) System.out.print(". ");
				else System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void start() {
    	System.out.println(play + " " + playnew);
    }


    			if (minefield[k][p]) {
    				mineborder[k][p] = 9;
    				continue;	
    			}

	    for (int i = 0; i < n; i++) {
	    	for (int j = 0; j < m; j++) {
    			if (minefield[i][j]) {}// mineborder[i][j] = 9;
    		}
    	}