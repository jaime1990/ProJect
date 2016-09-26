package main.java.com.dao.DaoConfig;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Rockey on 2015/8/25.
 */
public class DownLoadManager  implements Inject {
    @Override
    public void inject(Schema schema) {
            Entity entity = schema.addEntity("Download");
            entity.setTableName("Download");
            entity.addLongProperty("id").primaryKey().autoincrement();
            entity.addIntProperty("state").notNull();
            entity.addStringProperty("downloadUrl").notNull();
            entity.addStringProperty("fileName").notNull();
            entity.addStringProperty("fileSavePath").notNull();
            entity.addLongProperty("progress").notNull();
            entity.addLongProperty("fileLength").notNull();
            entity.addBooleanProperty("autoResume").notNull();
            entity.addBooleanProperty("autoRename").notNull();
        }


}
