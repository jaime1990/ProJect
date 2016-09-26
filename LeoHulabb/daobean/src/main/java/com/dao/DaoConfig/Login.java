package main.java.com.dao.DaoConfig;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * @author Rockey on 2015/8/25
 * @Description todo
 */
public class Login implements Inject {
    @Override
    public void inject(Schema schema) {
        Entity entity = schema.addEntity("User");
//        entity.setSkipGeneration(true);
        entity.setTableName("USER");
        entity.addIdProperty().primaryKey().autoincrement();
        entity.addStringProperty("UserName");
        entity.addStringProperty("Card");
        entity.addStringProperty("ImageUrl");
        entity.addStringProperty("address");
        entity.addStringProperty("Mb");
        entity.addStringProperty("Token");
        entity.addStringProperty("UserId");
        entity.addStringProperty("DEVICETYPE");
        entity.addStringProperty("DEVICEID");
        entity.addStringProperty("VERSION");
        entity.addStringProperty("AVATAR");
    }
}
