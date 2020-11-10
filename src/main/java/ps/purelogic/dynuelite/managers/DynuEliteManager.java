/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite.managers;

import ps.purelogic.dynuelite.Elite;
import ps.purelogic.dynuelite.EliteEntity;
import ps.purelogic.dynuelite.interfaces.RelationalDatabaseManager;

/**
 *
 * @author Mohammed
 */
public class DynuEliteManager {

    private static DynuEliteManager instance;

    private final RelationalDatabaseManager dbManager;
    private final Elite elite;

    private DynuEliteManager(RelationalDatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.elite = new Elite();
    }

    public EliteEntity entity(String model) {
        return elite.entity(model);
    }

    public static void initialize(RelationalDatabaseManager relationalDBManager) {
        instance = new DynuEliteManager(relationalDBManager);
    }

    public static DynuEliteManager instance() {
        return instance;
    }

}
