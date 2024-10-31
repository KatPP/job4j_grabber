package report.store;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public String marshal(Calendar c) throws Exception {
        Date d = c.getTime();
        synchronized (dateFormat) {
            return dateFormat.format(d);
        }
    }

    @Override
    public Calendar unmarshal(String c) throws Exception {
        synchronized (dateFormat) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(c));
            return calendar;
        }
    }
}