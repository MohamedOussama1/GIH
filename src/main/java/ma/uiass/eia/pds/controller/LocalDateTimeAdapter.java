package ma.uiass.eia.pds.controller;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s);
    }

    @Override
    public String marshal(LocalDateTime dateTime) throws Exception {
        return dateTime.toString();
    }
}
