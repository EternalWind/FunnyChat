import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.funnyChat.event.ChatEvent;


public class HistoryViewer extends JFrame{
    HistoryManager historyManager = new HistoryManager();
	public JPanel loadRecord(ChatEvent recordEvent){
		JPanel panel=new JPanel();
		List<Image> images = new ArrayList<Image>();
		JLabel label=new JLabel(recordEvent.getSenderId()+"    "+recordEvent.getDate());
		panel.add(label);
		label=new JLabel(recordEvent.getContent());
		panel.add(label);
		for(int i=0;i<recordEvent.getPictures().size();i++){
			Image image = this.getToolkit().createImage(recordEvent.getPictures().get(i));
			images.add(image);
			label=new JLabel(new ImageIcon(images.get(i)));
			panel.add(label);
		}
		return panel;
	}
	private List<ChatEvent> getRecords(int month, int day){
		return historyManager.getRecords(month, day);
	}
	public static void showRecords(int month, int day){
		HistoryViewer historyViewer = new HistoryViewer();
		List<ChatEvent> records = historyViewer.getRecords(month, day);
		for(int i=0;i<records.size();i++){
			historyViewer.add(historyViewer.loadRecord(records.get(i)));
		}
		historyViewer.setTitle("¨¤¨²¨º¡¤????:"+month+"??"+day+"¨¨?");
		historyViewer.show();		
	}
}
