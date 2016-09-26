package main.java.com.dao.DaoConfig;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by js on 15/11/26.
 */
public class InstallParam implements  Inject{


    @Override
    public void inject(Schema schema) {
        Entity entity = schema.addEntity("InstallParamEntity");
//        entity.setSkipGeneration(true);
        entity.setTableName("InstallParam");
        entity.addIdProperty().primaryKey().autoincrement();
        entity.addStringProperty("DeviceType");
        entity.addStringProperty("SourceType");
        entity.addStringProperty("OnlyNumber");
        entity.addLongProperty("CreateTime");
    }
}
