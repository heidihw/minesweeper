import java.util.*; // Scanner, Random
import java.awt.*; // Drawing Panel, Button

public class Minesweeper {
	public static final int w = 360, n = 10, m = 10;
	public static long time = 0;

	DrawingPanel panel = new DrawingPanel(w,w+30); // x,y
	Graphics g = panel.getGraphics();

	public static void setup(DrawingPanel panel, Graphics g) {
		boolean[][] field = new boolean[n][m]; // each cell spans 36 pixels, n*(x-1) to n*x
	}

	public static void play(Graphics g) {
		Scanner console = new Scanner(System.in);
        // MouseListener start = new MouseListener(click);
        // panel.addActionListener(start);
        boolean safe = true;
		int x = 0, y = 0;

		// first selection
		Random r = new Random();
		a = r.nextBoolean();					
		for ()
		

		while (safe) {
			x = console.nextInt();
			y = console.nextInt();


		}
    }

    public static void timeplay(Graphics g) {
		final long startTime = System.currentTimeMillis();
		play(g);
		final long   endTime = System.currentTimeMillis();
		time = endTime - startTime;
	}

    public static void result(Graphics g) {

    }

}
