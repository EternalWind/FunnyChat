import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.funnyChat.event.Event;
import com.funnyChat.plugin.Plugin;
import com.funnyChat.plugin.PluginAdapter;
import com.funnyChat.event.RecordEvent;

public class HistoryManager extends PluginAdapter{
	private String basePath = "history/";

	@Override
	protected void onEnable() {
		
	}

	@Override
	protected void onDisable() {
		
	}

	@Override
	protected void execute() {
		if(mEvent instanceof RecordEvent){
			addHistory((RecordEvent)mEvent);
		}
	}

	@Override
	protected boolean isInterested(Event _event) {
		return false;
	}

	@Override
	public void onCreate() {
		
	}

	@Override
	protected void onDestroy() {
		
	}
	private void addHistory(RecordEvent record){
		Date date = new Date(Date.parse(record.getDate()));
	    String path = basePath + date.getMonth()+ "/" + date.getDay()+".record";
	    File file = new File(path);
	    try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
			byte[] bytes = record.getBytes();
			out.write(bytes, 0, bytes.length);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<RecordEvent> getRecords(int month, int day){
		 String path = basePath + month + "/" + day+".record";
		 return RecordEvent.readRecords(path);
	}

}
