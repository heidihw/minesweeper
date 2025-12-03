import java.util.*; // Scanner, Random
import java.text.*; // DecimalFormat
// lines: step 28 stepone 74 minecheck 96 mineborderfill 131 display 149 takeinput 172 result 191 start 201
public class MinesweeperClean {

	public static int n = 9, m = 9, minetotal = 0; // rows, columns // total mines for 9x9: 10
	public static int mines = 0; // count mines planted
	public static long startTime = 0, endTime = 0; // timer
	public static Scanner console = new Scanner(System.in); // Scanner

	public static DecimalFormat df1 = new DecimalFormat("00"); // timer min, sec
	public static DecimalFormat df2 = new DecimalFormat("000"); // timer msec

	public static boolean[][] minepos = new boolean[n][m]; // mine planting // each cell spans 40 pixels, n*(x-1) to n*x
	public static int[][] mineborder = new int[n][m]; // store each number of adjacent mines
	public static int[][] field = new int[n][m]; // displayed to user

	public static int[] xy = new int[] {0,0}; // store coordinates to place in ArrayList
	public static ArrayList<int[]> current = new ArrayList<int[]>(); // store coords of all 0-bordered cells to check for displaying

	public static int cell = 1, x = 0, y = 0, xt = 0, yt = 0; // count selection, inputed xy, planting mines xy
	public static boolean playnew = true, safe = true, inprog = true, minecont = true; // play new round, detonated mine, not finished, keep expanding ArrayList

	public static void main(String[] args) {
		step();
	}

	public static void step() {

		start(); // ln 201
		if (playnew) {
        	cell = 1;

			do {
				if (cell == 1) stepone(); // plant mines; ln 102
				else takeinput(); // later selection; check mines
				if (x == -1 || y == -1) break;
				xy = new int[] {x,y};
				current = new ArrayList<int[]>();
				current.add(xy);
				minecheck(); // check and uncover mines; ln 124

				inprog = false;
				for (int i = 0; i < n; i++) { // check whether the current game is done
					for (int j = 0; j < m; j++) {
						if ((!minepos[i][j] && field[i][j] != 9) || (minepos[i][j] && field[i][j] == 9)) continue; // uncovered non-mine or covered mine
						else {
							inprog = true;
							break;
						}
					}
				}
				cell++;
				display(); // ln 177
			} while (safe && inprog);

			endTime = System.currentTimeMillis();
			result(); // ln 234
			step(); // recursion
		}

    }

	public static void stepone() {

		sizing();

		for (int i = 0; i < n; i++) { // initialize field
			for (int j = 0; j < m; j++) {
				field[i][j] = 9;
				minepos[i][j] = false;
				mineborder[i][j] = 0;
			}
		}

		System.out.println("Timer starts after first cell entered.");

        safe = true;
		display(); // ln 177
		takeinput();// ln 215
		startTime = System.currentTimeMillis();

		Random r = new Random(); // plant mines
		mines = 0;
		int xt = 0, yt = 0;
		while (mines <= minetotal) {
			xt = r.nextInt(n);
			yt = r.nextInt(m);
			if (xt == x && yt == y || minepos[xt][yt]) continue;
			minepos[xt][yt] = true;
			mines++;
		}

		mineborderfill(); // prepare adjacent mines count; ln 159

	}

	public static void minecheck() {
    	if (minepos[x][y]) { // if hit a mine, stop game
    		for (int k = 0; k < n; k++) {
    			for (int p = 0; p < m; p++) {
    				if (minepos[k][p]) field[k][p] = 10;
    			}
    		}
    		field[x][y] = 11;
    		safe = false;
    	}
    	else { // else uncover adjacent mine counts
    		minecont = true;
    		while (minecont) {
		    	minecont = false;
		    	for (int k = 0; k < current.size(); k++) {
		    		x = current.get(k)[0];
		    		y = current.get(k)[1];
			    	for (int i = x-1; i <= x+1; i++) {
			    		for (int j = y-1; j <= y+1; j++) {
				    		if (i == x && j == y) field[i][j] = mineborder[i][j];
			    			else if (i < 0 || j < 0 || i >= n || j >= m || field[i][j] != 9) continue;
				    		else if (mineborder[x][y] == 0) {
				    			field[i][j] = mineborder[i][j];
				    			minecont = true;
				    			xy = new int[] {i,j};
				    			current.add(xy);
				    		}
			    		}
			    	}
			    }
		    }
		}

    }

    public static void mineborderfill() {

    	for (int k = 0; k < n; k++) { // for all cells
    		for (int p = 0; p < m; p++) {
	    		if (minepos[k][p]) mineborder[k][p] = 9; // if mine, set 9 for visibility
	    		else {
			    	for (int i = k-1; i <= k+1; i++) { // for 8 adjacent cells, add to border count
			    		for (int j = p-1; j <= p+1; j++) {
			    			if (i < 0 || j < 0 || i >= n || j >= m || (i == k && j == p)) continue; // out of bounds, cell itself
				    		else if (minepos[i][j]) mineborder[k][p]++;
			    		}
			    	}
			    }
	    	}
	    }

    }

	public static void sizing() {
		System.out.println("Choose a field size. Enter \n1 for 9x9 with 10 mines, \n2 for 16x16 with 40 mines, and \n3 for 30x16 with 99 mines.");
		int sizingint = console.nextInt();

		if (sizingint == 1) {n = 9; m = 9; minetotal = 10;}
		else if (sizingint == 2) {n = 16; m = 16; minetotal = 40;}
		else if (sizingint == 3) {n = 16; m = 30; minetotal = 99;}
		else sizing();

		minepos = new boolean[n][m];
		mineborder = new int[n][m];
		field = new int[n][m];
	}

	public static void display() {

		System.out.println();
		System.out.print(". ");
		for (int i = 0; i < m; i++) {
			System.out.print(i%10 + " "); // y values
		}

		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print(i%10 + " "); // x values
			
			for (int j = 0; j < m; j++) { // field
				if (field[i][j] == 11) System.out.print("X ");
				else if (field[i][j] == 10) System.out.print("x ");
				else if (field[i][j] == 9) System.out.print(". ");
				else System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void takeinput() {
		System.out.print("Enter -1 to quit or cell " + cell + " row (0 index): ");
		x = console.nextInt();
		if (x != -1) {
			if (x >= n) {
				System.out.println("Enter -1 to quit or a valid covered cell row.");
				takeinput();
			}
			else {
				System.out.print("Enter -1 to quit or cell " + cell + " col (0 index): ");
				y = console.nextInt();
				if (y != -1 && (y >= m || field[x][y] != 9)) {
					System.out.println("Enter -1 to quit or a valid covered cell column.");
					takeinput();
				}
			}
		}
	}

    public static void result() {
    	if (safe && !inprog) System.out.println("You win!");
    	else if (!safe) System.out.println("You lose.");
		System.out.println("Time: ");
		long time = endTime - startTime;
		long min = time/60000;
		double sec = time%60000/1000;
		long msec = time%1000;
		System.out.println(df1.format(min) + ":" + df1.format(sec) + "." + df2.format(msec));
    }

    public static void start() {
    	System.out.print("Enter 1 to play a new round or -1 to quit: ");
    	int play = console.nextInt();
    	if (play == -1) playnew = false;
    	else if (play != 1) start();
    }

}
