package main;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SevenSegmentDisplay extends TimerPanel implements ActionListener {

	// Animation variables
	private Timer t = new Timer(125, this);
	private boolean play = true;
	private int SIZE = 7;
	private int x = 0;
	private int[] y = new int[SIZE];
	private int[] f = new int[SIZE];
	private int[] n = new int[SIZE];
	private int velocity = 30;

	// Create Segments
	private Rectangle segmentZero;
	private Rectangle segmentOne;
	private Rectangle segmentTwo;
	private Rectangle segmentThree;
	private Rectangle segmentFour;
	private Rectangle segmentFive;
	private Rectangle segmentSix;

	// Create Fill in Segments
	private Rectangle fillInSegmentZero;
	private Rectangle fillInSegmentOne;
	private Rectangle fillInSegmentTwo;
	private Rectangle fillInSegmentThree;
	private Rectangle fillInSegmentFour;
	private Rectangle fillInSegmentFive;
	private Rectangle fillInSegmentSix;

	// Create Fill in Segments
	private Rectangle newSegmentZero;
	private Rectangle newSegmentOne;
	private Rectangle newSegmentTwo;
	private Rectangle newSegmentThree;
	private Rectangle newSegmentFour;
	private Rectangle newSegmentFive;
	private Rectangle newSegmentSix;

	// Color Constants
	private int R;
	private int G;
	private int B;

	// Byte Representation on 7-Segment Display
	private int zero = 0x3F;
	private int one = 0x06;
	private int two = 0x5B;
	private int three = 0x4F;
	private int four = 0x66;
	private int five = 0x6D;
	private int six = 0x7D;
	private int seven = 0x07;
	private int eight = 0x7F;
	private int nine = 0x6F;

	// Setup Number on Display
	private int num = zero;
	private int fallInPiece = num;
	private int fallOutPiece = num;
	private int newPiece = num;

//	public SevenSegmentDisplay() {
//		this.t.setInitialDelay(0);
//	}

	public SevenSegmentDisplay(int x, int y, int width, int height) {
		this.t.setInitialDelay(0);
		this.x = x;
		for (int i = 0; i < this.y.length; i++)
			this.y[i] = y;
		this.width = width;
		this.height = height;
		this.spacing = 6 * width;
		createFeatures();
	}
	
	public void changeNumber(int num, int mod) {
		// Convert to Seven Segment Display Representation
		int prevNum = setNum(num + 1, mod);
		int nextNum = setNum(num - 1, mod);
		num = setNum(num, mod);

		// Change Display
		changeDisplay(prevNum);
		fallInSegments(num & ~prevNum);
		fallOutSegments(prevNum & ~num);
		newSegments(nextNum & ~num);
		oldSegments(num & ~nextNum);
	}

	private int setNum(int num, int mod) {
		if (num % mod == 0)
			return zero;
		else if (num == 1)
			return one;
		else if (num == 2)
			return two;
		else if (num == 3)
			return three;
		else if (num == 4)
			return four;
		else if (num == 5)
			return five;
		else if (num == 6)
			return six;
		else if (num == 7)
			return seven;
		else if (num == 8)
			return eight;
		else if (num == 9)
			return nine;
		else
			return setNum(mod - 1, 10);
	}

	private void changeDisplay(int num) {
		this.num = num;
		resetSegments();
		repaint();
	}

	private void fallInSegments(int piece) {
		this.fallInPiece = piece;
	}

	private void fallOutSegments(int piece) {
		this.fallOutPiece = piece;
	}

	private void newSegments(int piece) {
		this.newPiece = piece;
	}

	private void oldSegments(int piece) {
	}

	protected void createFeatures() {
		this.segmentZero = new Rectangle(x + height, y[0] + 2 * spacing, width,
				height);
		this.segmentOne = new Rectangle(x + width + height, y[1] + height + 2
				* spacing, height, width);
		this.segmentTwo = new Rectangle(x + width + height, y[2] + 2 * height
				+ width + 2 * spacing, height, width);
		this.segmentThree = new Rectangle(x + height, y[3] + 2
				* (height + width) + 2 * spacing, width, height);
		this.segmentFour = new Rectangle(x, y[4] + 2 * height + width + 2
				* spacing, height, width);
		this.segmentFive = new Rectangle(x, y[5] + height + 2 * spacing,
				height, width);
		this.segmentSix = new Rectangle(x + height, y[6] + height + width + 2
				* spacing, width, height);

		this.fillInSegmentZero = new Rectangle(x + height, f[0] + spacing,
				width, height);
		this.fillInSegmentOne = new Rectangle(x + width + height, f[1] + height
				+ spacing, height, width);
		this.fillInSegmentTwo = new Rectangle(x + width + height, f[2] + 2
				* height + width + spacing, height, width);
		this.fillInSegmentThree = new Rectangle(x + height, f[3] + 2
				* (height + width) + spacing, width, height);
		this.fillInSegmentFour = new Rectangle(x, f[4] + 2 * height + width
				+ spacing, height, width);
		this.fillInSegmentFive = new Rectangle(x, f[5] + height + spacing,
				height, width);
		this.fillInSegmentSix = new Rectangle(x + height, f[6] + height + width
				+ spacing, width, height);

		this.newSegmentZero = new Rectangle(x + height, n[0], width, height);
		this.newSegmentOne = new Rectangle(x + width + height, n[1] + height,
				height, width);
		this.newSegmentTwo = new Rectangle(x + width + height, n[2] + 2
				* height + width, height, width);
		this.newSegmentThree = new Rectangle(x + height, n[3] + 2
				* (height + width), width, height);
		this.newSegmentFour = new Rectangle(x, n[4] + 2 * height + width,
				height, width);
		this.newSegmentFive = new Rectangle(x, n[5] + height, height, width);
		this.newSegmentSix = new Rectangle(x + height, n[6] + height + width,
				width, height);

	}

	protected void drawFeatures(Graphics2D g2) {
		if ((this.num & (0x01 << 0)) != 0)
			g2.fillRect(segmentZero.x, segmentZero.y, segmentZero.width,
					segmentZero.height);
		if ((this.num & (0x01 << 1)) != 0)
			g2.fillRect(segmentOne.x, segmentOne.y, segmentOne.width,
					segmentOne.height);
		if ((this.num & (0x01 << 2)) != 0)
			g2.fillRect(segmentTwo.x, segmentTwo.y, segmentTwo.width,
					segmentTwo.height);
		if ((this.num & (0x01 << 3)) != 0)
			g2.fillRect(segmentThree.x, segmentThree.y, segmentThree.width,
					segmentThree.height);
		if ((this.num & (0x01 << 4)) != 0)
			g2.fillRect(segmentFour.x, segmentFour.y, segmentFour.width,
					segmentFour.height);
		if ((this.num & (0x01 << 5)) != 0)
			g2.fillRect(segmentFive.x, segmentFive.y, segmentFive.width,
					segmentFive.height);
		if ((this.num & (0x01 << 6)) != 0)
			g2.fillRect(segmentSix.x, segmentSix.y, segmentSix.width,
					segmentSix.height);

		if ((this.fallInPiece & (0x01 << 0)) != 0)
			g2.fillRect(fillInSegmentZero.x, fillInSegmentZero.y,
					fillInSegmentZero.width, fillInSegmentZero.height);
		if ((this.fallInPiece & (0x01 << 1)) != 0)
			g2.fillRect(fillInSegmentOne.x, fillInSegmentOne.y,
					fillInSegmentOne.width, fillInSegmentOne.height);
		if ((this.fallInPiece & (0x01 << 2)) != 0)
			g2.fillRect(fillInSegmentTwo.x, fillInSegmentTwo.y,
					fillInSegmentTwo.width, fillInSegmentTwo.height);
		if ((this.fallInPiece & (0x01 << 3)) != 0)
			g2.fillRect(fillInSegmentThree.x, fillInSegmentThree.y,
					fillInSegmentThree.width, fillInSegmentThree.height);
		if ((this.fallInPiece & (0x01 << 4)) != 0)
			g2.fillRect(fillInSegmentFour.x, fillInSegmentFour.y,
					fillInSegmentFour.width, fillInSegmentFour.height);
		if ((this.fallInPiece & (0x01 << 5)) != 0)
			g2.fillRect(fillInSegmentFive.x, fillInSegmentFive.y,
					fillInSegmentFive.width, fillInSegmentFive.height);
		if ((this.fallInPiece & (0x01 << 6)) != 0)
			g2.fillRect(fillInSegmentSix.x, fillInSegmentSix.y,
					fillInSegmentSix.width, fillInSegmentSix.height);

		if ((this.newPiece & (0x01 << 0)) != 0)
			g2.fillRect(newSegmentZero.x, newSegmentZero.y,
					newSegmentZero.width, newSegmentZero.height);
		if ((this.newPiece & (0x01 << 1)) != 0)
			g2.fillRect(newSegmentOne.x, newSegmentOne.y, newSegmentOne.width,
					newSegmentOne.height);
		if ((this.newPiece & (0x01 << 2)) != 0)
			g2.fillRect(newSegmentTwo.x, newSegmentTwo.y, newSegmentTwo.width,
					newSegmentTwo.height);
		if ((this.newPiece & (0x01 << 3)) != 0)
			g2.fillRect(newSegmentThree.x, newSegmentThree.y,
					newSegmentThree.width, newSegmentThree.height);
		if ((this.newPiece & (0x01 << 4)) != 0)
			g2.fillRect(newSegmentFour.x, newSegmentFour.y,
					newSegmentFour.width, newSegmentFour.height);
		if ((this.newPiece & (0x01 << 5)) != 0)
			g2.fillRect(newSegmentFive.x, newSegmentFive.y,
					newSegmentFive.width, newSegmentFive.height);
		if ((this.newPiece & (0x01 << 6)) != 0)
			g2.fillRect(newSegmentSix.x, newSegmentSix.y, newSegmentSix.width,
					newSegmentSix.height);
	}

	public void actionPerformed(ActionEvent e) {
		if (play) {
			for (int i = 0; i < y.length; i++) {
				y[i] += ((fallOutPiece & (0x01 << i)) >> i) * velocity;
				f[i] += ((fallInPiece & (0x01 << i)) >> i) * velocity;
				n[i] += ((newPiece & (0x01 << i)) >> i) * velocity;
				if (y[i] > spacing)
					y[i] = spacing;
				if (f[i] > spacing)
					f[i] = spacing;
				if (n[i] > spacing)
					n[i] = spacing;
			}
		}
		repaint();
	}

	private void resetSegments() {
		for (int i = 0; i < y.length; i++)
			this.y[i] = 0;
		for (int i = 0; i < f.length; i++)
			this.f[i] = 0;
		for (int i = 0; i < n.length; i++)
			this.n[i] = 0;
	}

	public void setPlay(boolean play) {
		this.play = play;
		if (play)
			t.start();
		else
			t.stop();
	}
}
