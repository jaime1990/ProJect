package main.java.com.dao;/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.ArrayList;
import java.util.List;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;
import main.java.com.dao.DaoConfig.AddressProvinceCity;
import main.java.com.dao.DaoConfig.DownLoadManager;
import main.java.com.dao.DaoConfig.Inject;
import main.java.com.dao.DaoConfig.InstallParam;
import main.java.com.dao.DaoConfig.Login;
import main.java.com.dao.DaoConfig.PublicKey;

/**
 * s
 * Generates entities and DAOs for the example project  nfkj Dao.
 * Run it as a Java application (not Android).
 *
 * @author rockey
 */
public class NfDaoGenerator {

    public static List<Inject> dao =new ArrayList<Inject>(); ;
    private NfDaoGenerator nf;
    public static void main(String[] args) throws Exception {
        System.out.print(NfDaoGenerator.class.getResource("/").getPath());
        Schema schema = new Schema(1, "main.java.com.dao.gen");
        schema.enableKeepSectionsByDefault();
        NfDaoGenerator nf = new NfDaoGenerator();
        nf.createList();
        nf.generatorList(dao,schema);
        new DaoGenerator().generateAll(schema, NfDaoGenerator.class.getResource("/").getPath() + "../../../src");//rockeyprotocol/src-gen  :generator dao  in rockeyprotocol project
    }


    private  void generatorList(List<Inject>  dao,Schema schema)
    {
        for (Inject in : dao)
        {
            in.inject(schema);
        }
    }
    private  void createList() {
        dao.add(new DownLoadManager());
        dao.add(new PublicKey());
        dao.add(new Login());
        dao.add(new InstallParam());
        dao.add(new AddressProvinceCity());
    }
}
