/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mohammed
 */
public interface RelationalDatabaseManager {
    
    Connection connection() throws SQLException;
    
    Connection connection(String dataSource) throws SQLException;
    
}
