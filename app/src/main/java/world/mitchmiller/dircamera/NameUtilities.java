package world.mitchmiller.dircamera;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Mitch Miller on 1/6/2018.
 */

public class NameUtilities {

    public static String getDateTimeString() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }


}
