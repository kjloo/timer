import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;


public class ClockPanel extends JPanel{
	
	//Create Constants
	private int MAX_SECONDS = SecondsTextField.getMax();
	private int MAX_MINUTES = MinutesTextField.getMax();
	private int MAX_HOURS = HoursTextField.getMax();
	private int MAX_DAYS = DaysTextField.getMax();
	
	//Size Variables
	private int height = 7;
	private int width = 21;
	
	//Create Time Holders
	private int daysCount;
	private int hoursCount;
	private int minutesCount;
	private int secondsCount;
	
	//Create Displays
	private SevenSegmentDisplay secondsOneDisplay = new SevenSegmentDisplay(0, 0, width, height);
	private SevenSegmentDisplay secondsTenDisplay = new SevenSegmentDisplay(0, 0, width, height);
	private SevenSegmentDisplay minutesOneDisplay = new SevenSegmentDisplay(0, 0, width, height);
	private SevenSegmentDisplay minutesTenDisplay = new SevenSegmentDisplay(0, 0, width, height);
	private SevenSegmentDisplay hoursOneDisplay = new SevenSegmentDisplay(0, 0, width, height);
	private SevenSegmentDisplay hoursTenDisplay = new SevenSegmentDisplay(0, 0, width, height);
	private SevenSegmentDisplay daysOneDisplay = new SevenSegmentDisplay(0, 0, width, height);
	private SevenSegmentDisplay daysTenDisplay = new SevenSegmentDisplay(0, 0, width, height);
	private SevenSegmentDisplay daysHundredDisplay = new SevenSegmentDisplay(0, 0, width, height);
	
	public ClockPanel() {
		initComponents();
	}
	
	private void initComponents() {
		//Set Layout
		setLayout(new MigLayout("", "[grow] [grow] [grow] 20 [grow] [grow] 20 [grow] [grow] 20 [grow] [grow]", "[grow] 20 [grow]"));
		add(daysHundredDisplay, "cell 0 0 1 10, grow, wrap");
		add(daysTenDisplay, "cell 1 0 1 10, grow, wrap");
		add(daysOneDisplay, "cell 2 0 1 10, grow, wrap");
		add(hoursTenDisplay, "cell 3 0 1 10, grow, wrap");
		add(hoursOneDisplay, "cell 4 0 1 10, grow, wrap");
		add(minutesTenDisplay, "cell 5 0 1 10, grow, wrap");
		add(minutesOneDisplay, "cell 6 0 1 10, grow, wrap");
		add(secondsTenDisplay, "cell 7 0 1 10, grow, wrap");
		add(secondsOneDisplay, "cell 8 0 1 10, grow, wrap");
	}
	
	public void setClock(int secondsCount, int minutesCount, int hoursCount, int daysCount) {
		this.secondsCount = secondsCount;
		this.minutesCount = minutesCount;
		this.hoursCount = hoursCount;
		this.daysCount = daysCount;
	}
	
	public void setDisplay(int secondsCount, int minutesCount, int hoursCount, int daysCount) {
		secondsOneDisplay.changeNumber(secondsCount % 10);
		secondsTenDisplay.changeNumber(secondsCount / 10);
		minutesOneDisplay.changeNumber(minutesCount % 10);
		minutesTenDisplay.changeNumber(minutesCount / 10);
		hoursOneDisplay.changeNumber(hoursCount % 10);
		hoursTenDisplay.changeNumber(hoursCount / 10);
		daysOneDisplay.changeNumber(daysCount % 10);
		daysTenDisplay.changeNumber((daysCount / 10) % 10);
		daysHundredDisplay.changeNumber(daysCount / 100);
	}
	
	public void updateDisplay(int secondsCount, int minutesCount, int hoursCount, int daysCount) {
		secondsOneDisplay.changeNumber(secondsCount % 10);
		if(this.secondsCount % 10 != MAX_SECONDS % 10 && secondsCount % 10 == MAX_SECONDS % 10)
			secondsTenDisplay.changeNumber(secondsCount / 10);
		if(this.secondsCount != MAX_SECONDS && secondsCount == MAX_SECONDS)
			minutesOneDisplay.changeNumber(minutesCount % 10);
		if(this.minutesCount % 10 != MAX_MINUTES % 10 && minutesCount % 10 == MAX_MINUTES % 10)
			minutesTenDisplay.changeNumber(minutesCount / 10);
		if(this.minutesCount != MAX_MINUTES && minutesCount == MAX_MINUTES)
			hoursOneDisplay.changeNumber(hoursCount % 10);
		if(this.hoursCount % 10 != MAX_HOURS % 10 && hoursCount % 10 == MAX_HOURS % 10)
			hoursTenDisplay.changeNumber(hoursCount / 10);
		if(this.hoursCount != MAX_HOURS && hoursCount == MAX_HOURS)
			daysOneDisplay.changeNumber(daysCount % 10);
		if((this.daysCount % 10) % 10 != (MAX_DAYS / 10) % 10 && (daysCount % 10) % 10 == (MAX_DAYS / 10) % 10)
			daysTenDisplay.changeNumber((daysCount / 10) % 10);
		if(this.daysCount % 100 != MAX_DAYS % 100 && daysCount % 100 == MAX_DAYS % 100)
			daysHundredDisplay.changeNumber(daysCount / 100);
	}
	
	public void setPlay(boolean play) {
		secondsOneDisplay.setPlay(play);
		secondsTenDisplay.setPlay(play);
		minutesOneDisplay.setPlay(play);
		minutesTenDisplay.setPlay(play);
		hoursOneDisplay.setPlay(play);
		hoursTenDisplay.setPlay(play);
		daysOneDisplay.setPlay(play);
		daysTenDisplay.setPlay(play);
		daysHundredDisplay.setPlay(play);
	}
}

