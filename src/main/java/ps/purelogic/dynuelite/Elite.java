/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite;

/**
 *
 * @author Mohammed
 */
/*
elite('City').eq('id', 12)
	     .or(q => {
			q.eq('name', 'Gaza');
			q.like(elite.concat('name', 'description'), '%mohammed%');
                        q.eq('name', elite.raw('trim(name)'));
			q.like('name', '%Gaza%');
		}).exec();
*/
public class Elite {

    public EliteEntity entity(String model) {
        return new EliteEntity(model);
    }

    public EliteEntity entity(String model, String alias) {
        return new EliteEntity(model, alias);
    }

}
