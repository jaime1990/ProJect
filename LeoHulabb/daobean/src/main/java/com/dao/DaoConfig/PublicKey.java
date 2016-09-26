package main.java.com.dao.DaoConfig;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * @author Rockey on 2015/8/25
 * @Description todo
 */
public class PublicKey implements  Inject {
    @Override
    public void inject(Schema schema) {
        Entity entity = schema.addEntity("Key");
        entity.setTableName("KEY");
        entity.addIdProperty().primaryKey().autoincrement();
        entity.addStringProperty("AesKey");
        entity.addStringProperty("PublicKey");
    }
}
